package controller

import model.Category
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.apache.commons.text.StringEscapeUtils

const val PATH = "jdbc:sqlite:./src/main/resources/Trivia.sqlite"
const val DRIVER = "org.sqlite.JDBC"

/**
 * Library class that provides an interface to the Trivia Maze Questions Database.
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
        println(unHTML(qPackage.question))
        transaction {
            Questions.insert {
                it[category] = unHTML(qPackage.category)
                it[format] = unHTML(qPackage.type)
                it[difficulty] = unHTML(qPackage.difficulty)
                it[question] = unHTML(qPackage.question)
                it[correct_answer] = unHTML(qPackage.correct_answer)
                it[incorrect_answers] = unHTML(qPackage.incorrect_answers.toString())
                    .replace("[", "")
                    .replace("]", "")
            }
        }
    }

    fun categoryCount(theCategory: Category) = transaction {
        Database.connect(PATH, DRIVER)
        when (theCategory) {
            Category.ANY -> Questions.selectAll().count()
            else -> transaction {
                Questions.select {
                    Questions.category.lowerCase() eq theCategory.name.lowercase()
                }.count()
            }
        }
    }

    private fun unHTML(incoming: String) = StringEscapeUtils.unescapeHtml4(incoming)
}
