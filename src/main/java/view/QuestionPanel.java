package view;

import model.Question;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionPanel extends JPanel {
    private final ButtonGroup mcButtonGroup = new ButtonGroup();

    private final JPanel myQuestionButtons;

    public static final int QUESTION_PANEL_WIDTH = 300;

    private Question myQuestion;

    private final JTextPane myQuestionPane;

    private final ArrayList<JRadioButton> myAnswers;

    private final JTextPane myShortAnswerPane;

    public QuestionPanel() {

        //initialize fields
        myAnswers = new ArrayList<>();
        JPanel mainPanel = new JPanel();

        // set Layouts
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(QUESTION_PANEL_WIDTH, 700));

        myQuestionPane = new JTextPane();
        myQuestionPane.setPreferredSize(new Dimension(300, 100));
        myQuestionPane.setEditable(false);
        myQuestionPane.setBackground((Color.LIGHT_GRAY));

        mainPanel.add(myQuestionPane);

        myShortAnswerPane = new JTextPane();
        myShortAnswerPane.setEditable(true);
        myShortAnswerPane.setText("Enter Answer Here.");
        myShortAnswerPane.setPreferredSize(new Dimension(100, 30));
        myShortAnswerPane.setVisible(false);

        myQuestionButtons = new JPanel();
        myQuestionButtons.setLayout(new BoxLayout(myQuestionButtons, BoxLayout.PAGE_AXIS));
        myQuestionButtons.setPreferredSize(new Dimension(300, 200));

        add(mainPanel, BorderLayout.CENTER);
        add(myShortAnswerPane, BorderLayout.SOUTH);
        add(myQuestionButtons, BorderLayout.SOUTH);

        setBackground(Color.BLUE);
        setVisible(true);
    }

    /**
     * Checks if the answer for the question is correct
     * @return boolean
     */

    public boolean isCorrectAnswer() {
        boolean temp = false;
        if (mcButtonGroup.getSelection() != null) {
            temp = mcButtonGroup.getSelection().getActionCommand().equals(myQuestion.getCorrectAnswer().get());
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Please Choose an answer",
                    "No Answer",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (!temp && mcButtonGroup.getSelection() != null) {
            JOptionPane.showMessageDialog(null, "The door did not unlock.", "Incorrect Answer" , JOptionPane.ERROR_MESSAGE);
        }
        return temp;
    }

    /**
     * Takes in a question and creates the buttons based off question Type
     * @param theQuestion question to be displayed
     */
    public void setPanelQuestion(Question theQuestion) {
        myShortAnswerPane.setVisible(false);
        myQuestion = theQuestion;
        switch (myQuestion.getType()) {
            case TRUE_FALSE -> createTFButtons();
            case MULTI_CHOICE -> createMCButtons();
            case SHORT_ANSWER -> createSAButtons();
        }

    }

    /**
     * creates a blank panel
     */
    public void createBlank() {
        clearList();
        myQuestionPane.setText("");
    }
    private void createSAButtons() {
        clearList();
        myQuestionButtons.setVisible(false);
        myShortAnswerPane.setVisible(true);
    }

    private void createMCButtons() {
        clearList();
        myQuestionButtons.setVisible(true);
        myQuestionPane.setText(myQuestion.getPrompt());

        for (int i = 0; i <= myQuestion.getIncorrectAnswers().size()-1;i ++) {
            JRadioButton rdBtn = new JRadioButton((myQuestion.getIncorrectAnswers().get(i)).get());
            rdBtn.setActionCommand((myQuestion.getIncorrectAnswers().get(i)).get());
            myAnswers.add(rdBtn);
            mcButtonGroup.add(rdBtn);
        }

        JRadioButton cAnswer = new JRadioButton(myQuestion.getCorrectAnswer().get());
        cAnswer.setActionCommand(myQuestion.getCorrectAnswer().get());
        myAnswers.add(cAnswer);
        mcButtonGroup.add(cAnswer);

        Random r = new Random();

        int rand;
        for (int i = 0; i <= myQuestion.getIncorrectAnswers().size();i ++) {
            rand = r.nextInt(myAnswers.size());
            if (rand != i) {
                Collections.swap(myAnswers, i, rand);
            }
        }

        for (int i = 0; i <= myQuestion.getIncorrectAnswers().size();i ++) {
            myQuestionButtons.add(myAnswers.get(i));
        }
        add(myQuestionButtons, BorderLayout.SOUTH);
    }

    private void createTFButtons() {
        clearList();
        myQuestionPane.setText(myQuestion.getPrompt());

        JRadioButton trueButton = new JRadioButton("True");
        mcButtonGroup.add(trueButton);
        trueButton.setActionCommand(trueButton.getText());
        myQuestionButtons.add(trueButton, BorderLayout.SOUTH);
        myAnswers.add(trueButton);

        JRadioButton falseButton = new JRadioButton("False");
        mcButtonGroup.add(falseButton);
        falseButton.setActionCommand(falseButton.getText());
        myQuestionButtons.add(falseButton, BorderLayout.SOUTH);
        myAnswers.add(falseButton);
        add(myQuestionButtons, BorderLayout.SOUTH);
    }

    private void clearList() {
        for (JRadioButton answer : myAnswers) {
            mcButtonGroup.remove(answer);
            answer.setVisible(false);
            myQuestionButtons.remove(answer);
        }
        myAnswers.clear();
    }

}
