package controller

//import org.jetbrains.exposed.dao.Entity
//import org.jetbrains.exposed.dao.EntityClass
//import org.jetbrains.exposed.dao.id.EntityID
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

    // /** Data access object for an instance of a question, may be needed for accessing  */
//    class DBQuestion(id: EntityID<Int>) : Entity<Int>(id) {
//        companion object : EntityClass<Int, DBQuestion>(Questions)
//
//        var category by Questions.category
//        var format by Questions.format
//        var difficulty by Questions.difficulty
//        var question by Questions.question
//        var correct_answer by Questions.correct_answer
//        var incorrect_answers by Questions.incorrect_answers
//    }

    /**
     * Provide a QuestionPackage to this method to import it into the question database.
     */
    fun importFromGson(qPackage: OpenTriviaDBSchema.QuestionPackage) {
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

    private fun unHTML(incoming: String) = StringEscapeUtils.unescapeHtml4(incoming)
}
