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
        preferredSize = Dimension(300, 40)
        border = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED)
        isFocusable = false
        preferredSize = Dimension(250, 40)
    }
}
