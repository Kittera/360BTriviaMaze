package controller

import com.google.gson.GsonBuilder
import model.Category
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * This class wraps up a bunch of API interface code for using OpenTriviaDB.
 */
class OpenTriviaDBSchema {


    // Wrappers for HTTP request code on various specific tasks
    // companion objects allow us to refer to a class as if it were a static Java class


    /**
     * Gets a token from OpenTriviaDB. These tokens support an optional service on
     * their end which uses the key to ensure only questions that haven't been
     * returned before are sent back to us. There will be an error is we run out of
     * questions, which is what the "remaining" variable in the import script is all
     * about.
     */
    class TokenHttp {

        companion object {
            private val mySingleton = TokenHttp()

            fun get(client: OkHttpClient) = mySingleton.get(client)
        }

        fun get(client: OkHttpClient): String {
            // build OkHttpRequest
            val tokenRequest = Request.Builder()
                .url("https://opentdb.com/api_token.php?command=request")
                .get()
                .build()

            //make the call
            val response = client.newCall(tokenRequest).execute()

            //declare possibility of string, get gson to parse the response
            val body: String?
            response.use { body = it.body?.string() }
            val tokenPackage =
                GsonBuilder().create().fromJson(body, TokenResponse::class.java)

            //return the count from the parsed json
            return tokenPackage.token
        }
    }

    /**
     * Wrapper for some Http code to get a JSON from OpenTriviaDB containing the number
     * of questions in a category.
     */
    class CategoryCountHttp {
        companion object {
            private val mySingleton = CategoryCountHttp()

            fun get(client: OkHttpClient, category: Category) =
                mySingleton.get(client, category)
        }


        fun get(client: OkHttpClient, category: Category): Int {
            val countURL =
                "https://opentdb.com/api_count.php?category=${category.categoryID}"

            // Build the OkHttpRequest
            val totalRequest = Request.Builder()
                .url(countURL)
                .get()
                .build()

            //make the call
            val totalResponse = client.newCall(totalRequest)
                .execute()

            // declare a string, but it might be null
            var body: String?
            totalResponse.use { body = it.body?.string()?: "Oops" }

            //return parsing result
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

        companion object {
            private val mySingleton = QuestionFetchHttp()

            fun get(
                client: OkHttpClient,
                category: Category = Category.ANY,
                count: Int = 50,
                token: String,
            ) = mySingleton.get(
                client = client,
                category = category,
                count = count,
                token = token,
            )
        }


        /**
         * Retrieves questions from OpenTriviaDB. Optional parameters: Category and amount.
         * Defaults to no specified category, and 50 questions per batch.
         */
        fun get(
            category: Category,
            client: OkHttpClient,
            count: Int,
            token: String,
        ): FetchResponse {
            // build url for OkHttpRequest
            val urlsb = StringBuilder("https://opentdb.com/api.php?")
            urlsb.append("amount=$count&")
            urlsb.append("token=$token&")

            // add this parameter to the url if filter given, keep out otherwise
            if (category != Category.ANY) {
                urlsb.append("category=${category.categoryID}")
            }

            // build actual OkHttpRequest
            val getRequest = Request.Builder()
                .url(urlsb.toString())
                .get()
                .build()

            // make call
            val getResponse = client.newCall(getRequest).execute()

            // navigate json body
            val body = getResponse.body?.string()
            println("GET from OpenTDB.")
            getResponse.close()

            // return FetchResponse gson object
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
