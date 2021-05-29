package resources

import org.jetbrains.exposed.sql.*
import controller.DRIVER
import controller.QuestionDB
import controller.QuestionDB.Questions
import model.Category
import okhttp3.OkHttpClient
import controller.OpenTriviaDBSchema.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

val openTriviaDBMaxPerQuery = 50
val importerPath = "jdbc:sqlite:./Trivia.sqlite"
val myClient = OkHttpClient()
val myDB = QuestionDB()

val token = TokenHttp.get(client = myClient)

fun main() {
    for /* every */ (category in Category.values()) /* import all questions */ {
        /* but */ if (category == Category.ANY) continue // because no endpoint for "any"
        else {
            // "connect" to database file
            Database.connect(importerPath, DRIVER)

            // create Questions table if it's not already present
            transaction(Connection.TRANSACTION_SERIALIZABLE, 2) {
                if (!Questions.exists()) {
                    SchemaUtils.create(Questions)
                }
            }

            // first ping API for the total count
            var remaining = CategoryCountHttp.get(category = category, client = myClient)

            // loop requests using token until category has fewer than 50 questions left
            while (remaining > openTriviaDBMaxPerQuery) {
                QuestionFetchHttp.get(
                    category = category,
                    client = myClient,
                    token = token,
                )
                    .results
                    .forEach { myDB.importGson(it) }
                remaining -= openTriviaDBMaxPerQuery
            }

            // get remaining questions
            QuestionFetchHttp.get(
                category = category,
                client = myClient,
                token = token,
                count = remaining - 1, // - 1 to make extra sure we don't hit the limit
            )
                .results
                .forEach { QuestionDB.importGson(it) } //import each question from result
        }
    }
}

main()
