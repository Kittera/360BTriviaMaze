package view

import controller.AnswerButton
import model.Question
import model.Answer
import model.QuestionType.*
import java.awt.*
import javax.swing.*

class QuestionPanelFactory {

    /**
     * Creates and returns a static panel
     */
    fun getEmptyQuestionPanel(): JPanel {
        val placeholderText  = "No Question Selected"
        val placeholderPanel = AbstractQuestionPanel()
        val tAnswer = Answer(placeholderText)

        placeholderPanel.title.text = placeholderText
        placeholderPanel.content.text = placeholderText
        placeholderPanel.answerPanel.add(AnswerButton(tAnswer))

        return placeholderPanel
    }

    /**
     * Dynamically creates a display panel for a question
     */
    fun getQuestionPanel(theQuestion: Question) = when (theQuestion.myType) {
        TRUE_FALSE, MULTI_CHOICE -> makeMultiChoicePanel(question = theQuestion)
//        SHORT_ANSWER -> makeShortAnswerPanel(question = theQuestion)
        else -> makeErrorPanel()
    }

        /**
     * Create the lower answer panel for the overall panel here
     */
    private fun makeAnswerPanel(choices: List<Answer>): JPanel {
        val answerPanel = JPanel(GridLayout(choices.size, 1))
        for (candidate in choices) {
            answerPanel.add(AnswerButton(myAnswer = candidate))
        }
        return answerPanel
    }

    /**
     * Question display panel factory method for multiple choice and true/false questions.
     */
    private fun makeMultiChoicePanel(question: Question): JPanel {
        val multiChoicePanel = AbstractQuestionPanel()
        val choices = (question.incorrectAnswers + question.correctAnswer).shuffled()

        multiChoicePanel.answerPanel.add(makeAnswerPanel(choices))
        multiChoicePanel.title.text = question.category.name
        multiChoicePanel.content.text = question.prompt

        return multiChoicePanel
    }

    /**
     * Default panel to make if something went wrong with question panel generation.
     */
    private fun makeErrorPanel(): JPanel {
        val result = JPanel()
        result.size = Dimension(200, 100)
        result.add(JLabel("This is bad.", JLabel.CENTER), BorderLayout.CENTER)
        return result
    }

//    /**
//     * Answer display panel for short answer questions
//     */
//    private fun makeShortAnswerPanel(question: Question): JPanel {
//        val amtChoices = question.incorrectAnswers.size
//
//
//        return JPanel()
//    }

    /**
     * Basic template of the Swing panel that
     */
    private class AbstractQuestionPanel : JPanel() {

        val qPanelHeight = 400
        val qPanelWidth = 250
        val tPanelHeight = 50

        init {
            background = Color.GREEN
            border = BorderFactory.createEmptyBorder()
            layout = BorderLayout()
            preferredSize = Dimension(qPanelWidth, qPanelHeight)
        }

        val answerPanel = JPanel(GridLayout(1, 1))

        init {
            answerPanel.background = Color.BLUE
            add(answerPanel, BorderLayout.SOUTH)
        }

        val contentPanel = JPanel(GridLayout(1,1))
        val content = JTextArea()

        init {
            content.alignmentX = JTextArea.CENTER_ALIGNMENT
            content.alignmentY = JTextArea.CENTER_ALIGNMENT
            content.isEditable = false
            content.background = Color.DARK_GRAY
            content.foreground = Color.WHITE
            content.lineWrap = true
            content.font = Font("Consolas", Font.BOLD, 16)
            contentPanel.add(content)
            contentPanel.background = content.background
            contentPanel.border = BorderFactory.createEmptyBorder(5, 5, 5, 5)
            add(contentPanel, BorderLayout.CENTER)
        }

        private val titlePanel = JPanel(GridLayout(1,1))
        val title = JLabel()

        init {
            title.verticalAlignment = JLabel.CENTER
            title.horizontalAlignment = JLabel.CENTER
            title.font = Font("Consolas", Font.BOLD, 16)
            title.foreground = Color.WHITE
            titlePanel.background = Color.darkGray
            titlePanel.preferredSize = Dimension(qPanelWidth, tPanelHeight)
            titlePanel.add(title)
            titlePanel.border = BorderFactory.createEmptyBorder(5, 5, 5, 5)
            add(titlePanel, BorderLayout.NORTH)
        }
    }
}
