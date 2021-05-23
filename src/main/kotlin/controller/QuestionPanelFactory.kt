package controller

import model.Question
import model.Answer
import model.QuestionType
import java.awt.*
import javax.swing.*

class QuestionPanelFactory {

    /**
     * Creates and returns a static panel
     */
    fun getEmptyQuestionPanel(): JPanel {
        val placeholderText  = "No Question Selected"
        val placeholderPanel = AbstractQuestionPanel(1)
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
        QuestionType.TRUE_FALSE   -> makeTrueFalsePanel(question = theQuestion)
        QuestionType.MULTI_CHOICE -> makeMultiChoicePanel(question = theQuestion)
        QuestionType.SHORT_ANSWER -> makeShortAnswerPanel(question = theQuestion)
        else -> makeErrorPanel()
    }

    /**
     * Specific procedure for building a panel out of a true false question
     */
    fun makeTrueFalsePanel(question: Question): JPanel {
        val booleanPanel = AbstractQuestionPanel(2)
        val falseButton = AnswerButton(Answer("False"))
        val trueButton = AnswerButton(Answer("True"))

        booleanPanel.contentPanel.add(JTextArea(question.prompt), JLabel.CENTER)

        booleanPanel.answerPanel.add(falseButton)
        booleanPanel.answerPanel.add(trueButton)

        return booleanPanel
    }

//    /**
//     * Create the lower answer panel for the overall panel here
//     */
//    private fun makeAnswerPanel(choices: List<Answer>): JPanel {
//        val answerPanel = JPanel(GridLayout(choices.size, 1))
//        for (candidate in choices) {
//            answerPanel.add(AnswerButton(myAnswer = candidate))
//        }
//        return answerPanel
//    }

    /**
     * Default panel to make if something went wrong with question panel generation.
     */
    private fun makeErrorPanel(): JPanel {
        val result = JPanel()
        result.size = Dimension(200, 100)
        result.add(JLabel("This is bad.", JLabel.CENTER), BorderLayout.CENTER)
        return result
    }

    /**
     * Question display panel factory method for multiple choice questions.
     */
    private fun makeMultiChoicePanel(question: Question): JPanel {
        val multiChoicePanel = AbstractQuestionPanel(question.incorrectAnswers.size)

        val buttons = question.incorrectAnswers
            .map { answer -> AnswerButton(answer) }


        return JPanel()
    }

    /**
     * Answer display panel for short answer questions
     */
    private fun makeShortAnswerPanel(question: Question): JPanel {
        val amtChoices = question.incorrectAnswers.size


        return JPanel()
    }

    /**
     * Basic template of the Swing panel that
     */
    private class AbstractQuestionPanel(totalChoices: Int) : JPanel() {

        val qPanelHeight = 400
        val qPanelWidth = 250
        val tPanelHeight = 50

        init {
            background = Color.GREEN
            border = BorderFactory.createEmptyBorder()
            layout = BorderLayout()
            preferredSize = Dimension(qPanelWidth, qPanelHeight)
        }

        val answerPanel = JPanel(GridLayout(totalChoices, 1))

        init {
            answerPanel.background = Color.BLUE
            add(answerPanel, BorderLayout.SOUTH)
        }

        val contentPanel = JPanel(GridLayout(1,1))
        val content = JLabel()

        init {
            content.horizontalAlignment = JLabel.CENTER
            content.verticalAlignment = JLabel.CENTER
            contentPanel.add(content)
            contentPanel.background = Color.GREEN
            add(contentPanel, BorderLayout.CENTER)
        }

        private val titlePanel = JPanel(GridLayout(1,1))
        val title = JLabel()

        init {
            title.verticalAlignment = JLabel.CENTER
            title.horizontalAlignment = JLabel.CENTER
            titlePanel.background = Color.RED
            titlePanel.preferredSize = Dimension(qPanelWidth, tPanelHeight)
            titlePanel.add(title)
            add(titlePanel, BorderLayout.NORTH)
        }
    }
}
