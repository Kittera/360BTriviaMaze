package controller

import com.google.gson.GsonBuilder
import model.Category
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * This class wraps up a bunch of API interface code for using OpenTriviaDB.
 */
class OpenTriviaDBSchema {


    //Wrappers for HTTP request code on various specific tasks


    /**
     * Gets a token from OpenTriviaDB. These tokens support an optional service on
     * their end which uses the key to ensure only questions that haven't been
     * returned before are sent back to us. There will be an error is we run out of
     * questions, which is what the "remaining" variable in the import script is all
     * about.
     */
    class TokenHttp {
        fun get(client: OkHttpClient): String {
            val tokenRequest = Request.Builder()
                .url("https://opentdb.com/api_token.php?command=request")
                .get()
                .build()

            val response = client.newCall(tokenRequest).execute()

            val body: String?
            response.use { body = it.body?.string() }
            val tokenPackage =
                GsonBuilder().create().fromJson(body, TokenResponse::class.java)

            return tokenPackage.token
        }
    }

    /**
     * Wrapper for some Http code to get a JSON from OpenTriviaDB containing the number
     * of questions in a category.
     */
    class CategoryCountHttp {
        fun get(client: OkHttpClient, category: Category): Int {
            val countURL =
                "https://opentdb.com/api_count.php?category=${category.categoryID}"

            val totalRequest = Request.Builder()
                .url(countURL)
                .get()
                .build()

            val totalResponse = client.newCall(totalRequest)
                .execute()

            var body: String?
            totalResponse.use { body = it.body?.string() }

            return GsonBuilder().create()
                .fromJson(body, CategoryCountResponse::class.java)
                .category_question_count
                .total_question_count
        }
    }

    /**
     * Wrapper for the HTTP code needed to get a questions JSON from OpenTriviaDB.
     */
    class QuestionFetchHttp {

        /**
         * Retrieves questions from OpenTriviaDB. Optional parameters: Category and amount.
         * Defaults to no specified category, and 50 questions per batch.
         */
        fun get(
            category: Category = Category.ANY,
            client: OkHttpClient,
            count: Int = 50,
            token: String,
        ): FetchResponse {
            val urlsb = StringBuilder("https://opentdb.com/api.php?")
            urlsb.append("amount=$count&")
            urlsb.append("token=$token&")

            if (category != Category.ANY) {
                urlsb.append("category=${category.categoryID}")
            }

            val getRequest = Request.Builder()
                .url(urlsb.toString())
                .get()

            val httpParams = getRequest.build()

            val getResponse = client.newCall(httpParams).execute()

            val body = getResponse.body?.string()
            println("GET from OpenTDB.")
            getResponse.close()

            return GsonBuilder().create()
                .fromJson(body, FetchResponse::class.java)
        }
    }


    //JSON object models for GSON to use in parsing
    //important: val names must match the names of the keys found in the incoming JSON


    /**
     * This class is an object representing the structure of the JSON responses we get
     * from OpenTriviaDB. It is used by Gson, which parses the JSON into an object of
     * this class.
     */
    class FetchResponse(
        val response_code: Int,
        val results: List<QuestionPackage>,
    )

    /**
     * GSON supports nested JSON objects. Each one we get from the question request
     * endpoint has an array of objects with the structure modelled by this class.
     */
    class QuestionPackage(
        val category: String,
        val type: String,
        val difficulty: String,
        val question: String,
        val correct_answer: String,
        val incorrect_answers: List<String>,
    )

    /**
     * This object class captures only the information we need from the question count
     * endpoint; that being total questions in a given category.
     */
    class CategoryCountResponse(val category_question_count: CategoryCounts) {
        /**
         * Nested inner class for the JSON object containing various counts, of which we
         * only need one.
         */
        class CategoryCounts(val total_question_count: Int)
    }

    /**
     * Models the needed fields from the JSON response at the token request endpoint.
     */
    class TokenResponse(val token: String)
}
