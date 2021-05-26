package resources

import org.jetbrains.exposed.sql.*
import controller.DRIVER
import controller.QuestionDB
import controller.QuestionDB.Questions
import model.Category
import okhttp3.OkHttpClient
import controller.OpenTriviaDBSchema.*
import org.jetbrains.exposed.sql.transactions.transaction

val importerPath = "jdbc:sqlite:./Trivia.sqlite"
val myClient = OkHttpClient()
val myDB = QuestionDB()
val myTokenGetter = TokenHttp()
val myCounter = CategoryCountHttp()
val myFetcher = QuestionFetchHttp()

val token = myTokenGetter.get(client = myClient)

fun main() {
    for /* every */ (category in Category.values()) /* import all questions */ {
        /* but */ if (category == Category.ANY) continue /* because no endpoint for "any" */
        else {
            //"connect" to database file
            Database.connect(importerPath, DRIVER)

            //create Questions table if it's not already present
            transaction {
                if (!Questions.exists()) {
                    SchemaUtils.create(Questions)
                }
            }

            //first ping API for the total count
            var remaining = myCounter.get(category = category, client = myClient)

            //loop requests using token until category has fewer than 50 questions left.
            while (remaining > 50) {
                myFetcher.get(category = category, client = myClient, token = token)
                    .results
                    .forEach { myDB.importFromGson(it) }
                remaining -= 50
            }

            //get remaining questions
            myFetcher.get(
                category = category,
                client = myClient,
                token = token,
                count = remaining - 1,
            )
                .results
                .forEach { myDB.importFromGson(it) }
        }
    }
}

main()
