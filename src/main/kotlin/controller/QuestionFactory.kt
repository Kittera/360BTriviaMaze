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

    private val pickedList: MutableList<Int>

    init {
        pickedList = ArrayList()
    }

    /**
     * Selects a random question from the database, with optional parameters
     * for category and difficulty.
     */
    fun getQuestion(
        theCategory: Category = Category.ANY,
        theDifficulty: Difficulty = Difficulty.EASY,
    ): Question {

        Database.connect(PATH, DRIVER)

        val resultRow = transaction {
            when (theCategory) {
                //...is any, don't provide a category filter
                Category.ANY -> Questions.selectAll()
                    .orderBy(Random())
                    .first {
                        !pickedList.contains(it[Questions.id].value) &&
                        it[Questions.difficulty].lowercase() ==
                                theDifficulty.difficulty.lowercase()
                    }

                //...is anything but "any", we need a specific filter for it
                else -> Questions.selectAll()
                    .orderBy(Random())
                    .first {
                        !pickedList.contains(it[Questions.id].value)
                                &&
                        it[Questions.category].lowercase() == theCategory.title
                                &&
                        it[Questions.difficulty].lowercase() == theDifficulty.difficulty
                    }
            }
        }
        //"it": a Kotlin keyword that shrinks lambdas
        // similar to Java's static references

        //use this to ensure
        pickedList.add(resultRow[Questions.id].value)

        return Question(
            //this is a constructor call for the Java class Question in model
            //theCategory =
            Category.fromName(resultRow[Questions.category]),
            //theType =
            QuestionType.fromKey(resultRow[Questions.format]),
            //thDifficulty =
            Difficulty.fromName(resultRow[Questions.difficulty]),
            //theQuestion =
            resultRow[Questions.question],
            //theCorrectAnswer =
            Answer(resultRow[Questions.correct_answer]),
            //theIncorrectAnswers =
            resultRow[Questions.incorrect_answers].split(",").map { Answer(it) }
        )
    }


    fun reset() = pickedList.clear()
}
