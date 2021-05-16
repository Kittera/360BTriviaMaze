package controller

import model.Answer
import model.QuestionType
import java.awt.Dimension
import java.lang.IllegalArgumentException
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.border.BevelBorder

class AnswerButton(myAnswer: Answer): JButton(myAnswer.get()) {
    init {
        size = when (myAnswer.type) {
            QuestionType.TRUE_FALSE -> Dimension(100, 40)
            QuestionType.MULTI_CHOICE -> Dimension(300, 40)
            QuestionType.SHORT_ANSWER -> Dimension(300, 40)
            null -> throw IllegalArgumentException("Code 1 AnswerButton")
        }
        border = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED)
        isFocusable = false
        preferredSize = Dimension(250, 40)
    }
}
