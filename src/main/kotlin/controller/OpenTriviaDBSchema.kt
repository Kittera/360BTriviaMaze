package controller

import com.google.gson.GsonBuilder
import model.Category
import okhttp3.OkHttpClient
import okhttp3.Request

class OpenTriviaDBSchema {

    class TokenHttp {

        /**
         * Models the needed fields from the JSON response at the token request endpoint.
         */
        class TokenResponse(val response_message: String, val token: String)

        fun token(client: OkHttpClient): String {
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

    class CategoryCountHttp {
        class CategoryCount(val category_question_count: CategoryCounts) {
            class CategoryCounts(val total_question_count: Int)
        }

        fun getCount(client: OkHttpClient, category: Category): Int {
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
                .fromJson(body, CategoryCount::class.java)
                .category_question_count
                .total_question_count
        }
    }

    class QuestionFetchHttp {

        /**
         * Given a category, set the api query and retrieve data for import
         */
        fun get(
            category: Category = Category.ANY,
            client: OkHttpClient,
            count: Int = 50,
            token: String,
        ): FetchResult {
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
                .fromJson(body, FetchResult::class.java)
        }
    }

    class FetchResult(
        val response_code: Int,
        val results: List<QuestionPackage>,
    )

    class QuestionPackage(
        val category: String,
        val type: String,
        val difficulty: String,
        val question: String,
        val correct_answer: String,
        val incorrect_answers: List<String>,
    )
}

