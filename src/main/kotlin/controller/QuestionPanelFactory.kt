package controller

import DarkLabel
import model.AbstractQuestion
import model.Answer
import model.QuestionType
import java.awt.*
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextArea

class QuestionPanelFactory {

    /**
     * Creates and returns a static panel
     */
    fun getEmptyQuestionPanel(): JPanel {
        val placeholderText by lazy { "No Question Selected" }
        val placeholderPanel = AbstractQuestionPanel(1)

        placeholderPanel.titlePanel.add(JLabel(placeholderText))

        placeholderPanel.contentPanel.add(JLabel(placeholderText))

        placeholderPanel.answerPanel.layout = GridLayout(1, 1)
        placeholderPanel.answerPanel.add(DarkLabel(placeholderText))

        return placeholderPanel
    }

    /**
     * Dynamically creates a display panel for a question
     */
    fun getQuestionPanel(theQuestion: AbstractQuestion) = when (theQuestion.myType) {
        QuestionType.TRUE_FALSE -> makeTrueFalsePanel(question = theQuestion)
        QuestionType.MULTI_CHOICE -> makeMultiChoicePanel(question = theQuestion)
        QuestionType.SHORT_ANSWER -> makeShortAnswerPanel(question = theQuestion)
        else -> makeErrorPanel()
    }

    /**
     * Specific procedure for building a panel out of a true false question
     */
    fun makeTrueFalsePanel(question: AbstractQuestion): JPanel {
        val booleanPanel = AbstractQuestionPanel(2)
        val falseButton = AnswerButton(Answer(false))
        val trueButton = AnswerButton(Answer(true))

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
    private fun makeMultiChoicePanel(question: AbstractQuestion): JPanel {
        val multiChoicePanel = AbstractQuestionPanel(question.choices.size)

        val buttons = question.choices
            .map { answer -> AnswerButton(answer) }


        return JPanel()
    }

    /**
     * Answer display panel for short answer questions
     */
    private fun makeShortAnswerPanel(question: AbstractQuestion): JPanel {
        val amtChoices = question.choices.size


        return JPanel()
    }

    /**
     * Basic template of the Swing panel that
     */
    private class AbstractQuestionPanel(totalChoices: Int) : JPanel() {
        init {
            layout = BorderLayout()
            border = BorderFactory.createEmptyBorder()
            background = Color.GREEN
        }

        val answerPanel = JPanel(GridLayout(totalChoices, 1))

        init {
            answerPanel.background = Color.BLUE
            add(answerPanel, BorderLayout.SOUTH)
        }

        val contentPanel = JPanel()

        init {
            contentPanel.background = Color.GREEN
            add(contentPanel, BorderLayout.CENTER)
        }

        val titlePanel = JPanel()

        init {
            titlePanel.background = Color.RED
            add(titlePanel, BorderLayout.NORTH)
        }
    }
}
