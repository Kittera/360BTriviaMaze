package controller

import model.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Random
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import java.util.stream.Collectors
import controller.QuestionDB.*


/**
 * This class provides Question objects on demand.
 */
class QuestionFactory {

    val pickedList: MutableList<Int>

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
                Category.ANY -> Questions.selectAll()
                    .orderBy(Random())
                    .filter { !pickedList.contains(it[Questions.id].value) }
                    .first {
                        it[Questions.difficulty].lowercase() == theDifficulty.difficulty
                    }
                else -> Questions.selectAll()
                    .orderBy(Random())
                    .filter { !pickedList.contains(it[Questions.id].value) }
                    .first {
                        it[Questions.category].lowercase() == theCategory.name &&
                                it[Questions.difficulty].lowercase() == theDifficulty.difficulty
                    }
            }
        }
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
            resultRow[Questions.incorrect_answers].split(",")
                .stream()
//                .map { it.replace("[", "") } //remove brackets
//                .map { it.replace("]", "") } //remove brackets
                .map { Answer(it) } //now working with stream of newly constructed Answers
                .collect(Collectors.toList()) //collect stream into a list of Answer
        )
        //"it": a Kotlin keyword that shrinks lambdas similar to Java's static references
    }


    fun reset() = pickedList.clear()
}
