import controller.QuestionFactory
import controller.QuestionPanelFactory
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel


fun main() {

    val canvas = JPanel(FlowLayout(FlowLayout.CENTER))
    canvas.preferredSize = Dimension(600, 600)
    canvas.background = Color.BLACK
    canvas.add(QuestionPanelFactory().getQuestionPanel(QuestionFactory().getQuestion()))

    val testFrame = JFrame()
    testFrame.add(canvas)
    testFrame.pack()
    testFrame.setLocationRelativeTo(null)
    testFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    testFrame.isResizable = false
    testFrame.isVisible = true
    testFrame.title = "Test Question Panel"
}
