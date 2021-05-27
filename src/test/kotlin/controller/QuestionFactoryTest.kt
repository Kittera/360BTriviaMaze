package controller

import model.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuestionFactoryTest {

    @Test
    fun getQuestion() {
        val sentinel1 = "What happened?"
        val sentinel2 = "You Died."

        val testQuestion: Question = try {
            QuestionFactory().getQuestion()
        } catch (e: Exception) {
            val dummyAnswers =
                mutableListOf(
                    "This panel was fetched.",
                    "This test was successful.",
                    "Victory.",
                ).map { Answer(it) }
            Question(
                Category.COMPSCI,
                QuestionType.MULTI_CHOICE,
                Difficulty.EASY,
                sentinel1,
                Answer(sentinel2),
                dummyAnswers
            )
        }

        testQuestion.incorrectAnswers.forEach { assertFalse(it.get().contains("[")) }
        testQuestion.incorrectAnswers.forEach { assertFalse(it.get().contains("]")) }
        assertEquals(testQuestion.difficulty::class.java, Difficulty::class.java)
        assertEquals(testQuestion.category::class.java, Category::class.java)
        assertTrue(testQuestion.tryAnswer(testQuestion.correctAnswer))
        assertFalse(
            testQuestion.correctAnswer.get() == sentinel2 ||
                    testQuestion.prompt == sentinel1,
            "Checks for the failure sentinels in the fallback Question"
        )
    }
}
