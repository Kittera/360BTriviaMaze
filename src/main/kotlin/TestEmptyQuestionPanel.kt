import controller.QuestionPanelFactory
import javax.swing.JFrame

fun main() {
    val testFrame = JFrame()
    val factory = QuestionPanelFactory()

    testFrame.add(factory.getEmptyQuestionPanel())
    testFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    testFrame.pack()
    testFrame.setLocationRelativeTo(null)
    testFrame.isVisible = true
}
