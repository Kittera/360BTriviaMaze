package controller

import model.AbstractQuestion
import model.QuestionType
import javax.swing.JPanel

fun getQuestionPanel(theQuestion: AbstractQuestion) = when (theQuestion.myType) {
    QuestionType.TRUE_FALSE -> makeTrueFalsePanel(question = theQuestion)
    QuestionType.MULTI_CHOICE -> makeMultiChoicePanel(question = theQuestion)
    QuestionType.SHORT_ANSWER -> makeShortAnswerPanel(question = theQuestion)
    else -> makeErrorPanel()
}

/**
 *
 */
fun makeTrueFalsePanel(question: AbstractQuestion): JPanel {
    return JPanel()
}

/**
 *
 */
private fun makeMultiChoicePanel(question: AbstractQuestion): JPanel {
    return JPanel()
}

/**
 *
 */
private fun makeShortAnswerPanel(question: AbstractQuestion): JPanel {
    return JPanel()
}

/**
 *
 */
private fun makeErrorPanel(): JPanel {
    return JPanel()
}
