package controller

import model.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Random
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import controller.QuestionDB.*


/**
 * This class provides Question objects on demand.
 */
class QuestionFactory {
    companion object {
        private val fact = QuestionFactory()

        @JvmStatic
        fun get() =
            fact.getQuestion(category = Category.random(), difficulty = Difficulty.EASY)

        @JvmStatic
        fun get(theCategory: Category, theDifficulty: Difficulty) =
            fact.getQuestion(category = theCategory, difficulty = theDifficulty)
    }

    private val pickedList: MutableList<Int>

    init {
        pickedList = ArrayList() // TODO Figure out logic to reset() this
        Database.connect(PATH, DRIVER)
    }

    /**
     * Selects a random question from the database, with optional parameters
     * for category and difficulty.
     */
    fun getQuestion(
        category: Category,
        difficulty: Difficulty,
    ): Question {

        val resultRow = try {
            fetch(category, difficulty)
        } catch (fourOhFour: NoSuchElementException) {
            reset()
            fetch(category, difficulty)
        }

        //use this to ensure no duplicate picks
        pickedList.add(resultRow[Questions.id].value)

        return Question(
            //this is a constructor call for the Java class Question in model
            /* theCategory = */ Category.fromName(resultRow[Questions.category]),
            /* theType = */ QuestionType.fromKey(resultRow[Questions.format]),
            /* theDifficulty = */ Difficulty.fromName(resultRow[Questions.difficulty]),
            /* theQuestion = */ resultRow[Questions.question],
            /* theCorrectAnswer = */ Answer(resultRow[Questions.correct_answer]),
            /* theIncorrectAnswers = */ resultRow[Questions.incorrect_answers]
                .split(",")
                .map { Answer(it) }
        )
    }

    private fun fetch(category: Category, difficulty: Difficulty) = transaction {
        Questions.selectAll()
            .orderBy(Random())
            .first {
                it[Questions.category].lowercase() == category.name.lowercase()
                        && it[Questions.difficulty].lowercase() == difficulty.difficulty.lowercase()
                        && !pickedList.contains(it[Questions.id].value)
            }
        //"it": a Kotlin keyword that shrinks lambdas
        // similar to Java's static references
    }


    fun reset() = pickedList.clear()
}
