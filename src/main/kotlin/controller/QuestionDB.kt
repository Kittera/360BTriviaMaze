package controller

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.apache.commons.text.StringEscapeUtils
import java.sql.Connection

const val PATH = "jdbc:sqlite:./src/main/resources/Trivia.sqlite"
const val SQLITE_DRIVER = "org.sqlite.JDBC"

/**
 * Library class that provides an interface to the Trivia Maze Questions Database.
 * @author Kittera Ashleigh McCloud
 * @version 2021.05.24.18.35
 */
class QuestionDB {

    companion object {
        private val mySingleton = QuestionDB()
        fun importGson(qPackage: OpenTriviaDBSchema.QuestionPackage) =
            mySingleton.importGson(qPackage = qPackage)
    }

    /**
     * Defines the structure of the Questions table under the JetBrains Exposed DSL.
     */
    object Questions : IntIdTable() {
        val category = text(name = "Category")
        val format = text(name = "Format")
        val difficulty = text(name = "Difficulty")
        val question = text(name = "Question")
        val correct_answer = text(name = "Correct Answer")
        val incorrect_answers = text(name = "Incorrect Answers")
    }

    /**
     * Provide a QuestionPackage to this method to import it into the question database.
     */
    fun importGson(qPackage: OpenTriviaDBSchema.QuestionPackage) {
        // format incorrect answer list
        var incorrectStrings = unHTML(qPackage.incorrect_answers[0])
        if (qPackage.incorrect_answers.size > 1) {
            val subList = qPackage.incorrect_answers.subList(1, qPackage.incorrect_answers.size)
            for (text in subList) {
                incorrectStrings += "@@${unHTML(text)}"
            }
        }

        transaction(Connection.TRANSACTION_SERIALIZABLE, 5) {
            Questions.insert {
                it[category] = unHTML(qPackage.category)
                it[format] = unHTML(qPackage.type)
                it[difficulty] = unHTML(qPackage.difficulty)
                it[question] = unHTML(qPackage.question)
                it[correct_answer] = unHTML(qPackage.correct_answer)
                it[incorrect_answers] = incorrectStrings
            }
        }
    }

    private fun unHTML(incoming: String) = StringEscapeUtils.unescapeHtml4(incoming)
}
