package resources

import org.jetbrains.exposed.sql.*
import controller.SQLITE_DRIVER
import controller.QuestionDB
import controller.QuestionDB.Questions
import model.Category
import okhttp3.OkHttpClient
import controller.OpenTriviaDBSchema.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureTimeMillis

val openTriviaDBMaxPerQuery = 50
val importerPath = "jdbc:sqlite:./Trivia.sqlite"
val myClient = OkHttpClient()

val token = TokenHttp.get(client = myClient)

fun main() {
    val overAllList = mutableListOf<QuestionPackage>()
    for /* every */ (category in Category.values()) /* import all questions */ {
        /* but */ if (category == Category.ANY) continue // because no endpoint for "any"
        else {
            // first ping API for the total count
            var remaining =
                CategoryCountHttp.get(category = category, client = myClient)

            // loop requests using token until category has fewer than 50 questions left
            val allResults = mutableListOf<QuestionPackage>()
            while (remaining > 0) {
                doFetch(min(remaining, openTriviaDBMaxPerQuery), category)
                    .forEach { allResults.add(it) }
                remaining = max(remaining - openTriviaDBMaxPerQuery, 0)
            }
            overAllList.addAll(allResults)
        }
    }

    Database.connect(importerPath, SQLITE_DRIVER)
    transaction(Connection.TRANSACTION_SERIALIZABLE, 3) {
        println()
        if (!Questions.exists()) {
            SchemaUtils.create(Questions)
            println("##### Created Questions Table")
        } else {
            SchemaUtils.drop(Questions)
            SchemaUtils.create(Questions)
            println("##### Cleared Questions Table")
        }
    }

    println("\n-----> Beginning Import\n")
    var countImports = 0
    val total = overAllList.size
    overAllList.forEach {
        QuestionDB.importGson(it)
        print("\r${progBar(countImports, total)} ${++countImports}/${total}")
    }
}

fun doFetch(theRemaining: Int, theCategory: Category) =
    QuestionFetchHttp.get(
        category = theCategory,
        client = myClient,
        token = token,
        count = theRemaining,
    ).results

println(" in ${measureTimeMillis { main() } / 1000.0} seconds")

fun progBar(countImports: Int, total: Int): String {
    val barWidth = 30
    val segments = ((countImports.toFloat() / total) * barWidth).toInt()
    return "#".repeat(segments + 1).padEnd(barWidth, '_')
}
