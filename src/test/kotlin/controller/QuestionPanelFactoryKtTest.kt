package controller

import model.TrueFalseQuestion
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.*

import kotlin.test.fail

internal class QuestionPanelFactoryKtTest {

    @Test
    fun getQuestionPanel() {
        fail("Not yet implemented")
    }

    @Test
    fun makeTrueFalsePanel() {
        val testPrompt = "A container of all containers contains itself."
        val testQuestion = TrueFalseQuestion(testPrompt, true)
        val testPanel = QuestionPanelFactory().makeTrueFalsePanel(testQuestion)
        assertNotNull(testPanel)
    }

    @Test
    fun makeAnswerPanel() {
        fail("Not yet implemented")
    }

    @Test
    fun makeMultiChoicePanel() {
        fail("Not yet implemented")
    }

    @Test
    fun makeWordAnswerPanel() {
        fail("Not yet implemented")
    }
}
