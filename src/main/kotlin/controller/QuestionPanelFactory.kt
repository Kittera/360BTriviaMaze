package controller

import model.AbstractQuestion
import model.Answer
import model.QuestionType
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
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
    val booleanPanel = JPanel()
    val trueButton = AnswerButton(Answer(true))
    val falseButton = AnswerButton(Answer(true))
    booleanPanel.add(JLabel(question.prompt, JLabel.CENTER), BorderLayout.CENTER)
    booleanPanel.add(makeAnswerPanel(question.choices))
    return booleanPanel
}

fun makeAnswerPanel(choices: List<Answer>): JPanel {
    val answerPanel = JPanel(FlowLayout(FlowLayout.CENTER))

    return answerPanel
}

/**
 *
 */
private fun makeMultiChoicePanel(question: AbstractQuestion): JPanel {
    val multiChoicePanel = JPanel()
    for (qText in question.choices) {

}
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
    val result = JPanel()
    result.size = Dimension(200, 100)
    result.add(JLabel("This is bad.", JLabel.CENTER), BorderLayout.CENTER)
    return result
}
