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
            fact.getQuestion(
                category = Category.random(),
                difficulty = Difficulty.random()
            )

        @JvmStatic
        fun get(theCategory: Category, theDifficulty: Difficulty) =
            fact.getQuestion(
                category = theCategory,
                difficulty = theDifficulty
            )
    }

    private val pickedList: MutableList<Int>

    init {
        pickedList = ArrayList()
        Database.connect(PATH, SQLITE_DRIVER)
    }

    /**
     * Selects a random question from the database, with optional parameters
     * for category and difficulty.
     */
    fun getQuestion(
        category: Category,
        difficulty: Difficulty,
    ): Question {
        //guard for case of ANY
        val finalCategory = when (category) {
            Category.ANY -> Category.random()
            else -> category
        }
        val finalDifficulty = when (difficulty) {
            Difficulty.ANY -> Difficulty.random()
            else -> difficulty
        }

        val resultRow = try {
            fetch(finalCategory, finalDifficulty)
        } catch (fourOhFour: NoSuchElementException) {
            reset()
            fetch(finalCategory, finalDifficulty)
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
                .split("@@")
                .map { Answer(it) }
        )
    }

    private fun fetch(category: Category, difficulty: Difficulty) = transaction {
        Questions.selectAll()
            .orderBy(Random())
            .first {
                it[Questions.category].equals(category.title, ignoreCase = true)
                        && it[Questions.difficulty].equals(difficulty.difficulty, ignoreCase = true)
                        && !pickedList.contains(it[Questions.id].value)
            }
        //"it": a Kotlin keyword that shrinks lambdas
        // similar to Java's static references
    }


    private fun reset() = pickedList.clear()
}
