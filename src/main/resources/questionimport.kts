package resources

import com.google.gson.GsonBuilder
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
val gsonBuilder = GsonBuilder().create()

val token = myTokenGetter.token(client = myClient)
main()


fun main() {
    for /* every */ (category in Category.values()) /* import all questions */ {
        /* but */ if (category == Category.ANY) continue /* because no endpoint for "any" */
        else {
            Database.connect(importerPath, DRIVER)

            //create table if it's not made yet
            transaction {
                if (!Questions.exists()) {
                    SchemaUtils.create(Questions)
                }
            }
            //first ping API for the total count
            var remaining = myCounter.getCount(category = category, client = myClient)

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

//get token - CHECK
//determine category maximum - CHECK
//make requests 50 at a time until <remaining> is < 50 then <remaining>
//get json as string
//send string to importJSON ----- QuestionDB().importFromJSON()
