package gameui;

import model.Answer;
import model.Question;
import model.QuestionType;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class QuestionPanel extends JPanel {
    private final ButtonGroup mcButtonGroup = new ButtonGroup();
    private final ButtonGroup tfButtonGroup = new ButtonGroup();

    private JPanel mainPanel;

    JPanel myQuestionButtons;

    Question myQuestion;

    String myCorrectAnswer;

    JTextPane myShortAnswerPane;

    ArrayList<JRadioButton> rdbtnsAnswers;

    public QuestionPanel() {

        //initialize fields
        rdbtnsAnswers = new ArrayList();
        mainPanel = new JPanel();

        // set Layouts
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(300, 700));

        myShortAnswerPane = new JTextPane();
        myShortAnswerPane.setPreferredSize(new Dimension(300, 100));
        myShortAnswerPane.setEditable(false);
        myShortAnswerPane.setBackground((Color.LIGHT_GRAY));
        mainPanel.add(myShortAnswerPane);

        myQuestionButtons = new JPanel();
        myQuestionButtons.setLayout(new BoxLayout(myQuestionButtons, BoxLayout.PAGE_AXIS));
        myQuestionButtons.setPreferredSize(new Dimension(300, 200));

        add(mainPanel, BorderLayout.CENTER);
        add(myQuestionButtons, BorderLayout.SOUTH);
        //setupPanel();
        setBackground(Color.BLUE);
        setVisible(true);
    }

    public void setPanelQuestion(Question theQuestion) {
        myQuestion = theQuestion;
        if (myQuestion.getType() == QuestionType.TRUE_FALSE) {
            createTFButtons();
        } else if (myQuestion.getType() == QuestionType.MULTI_CHOICE) {
            createMCButtons();
        } else if (myQuestion.getType() == QuestionType.SHORT_ANSWER) {
            createSAButtons();
        }

    }

    private void createSAButtons() {
    }

    private void createMCButtons() {
        //cleanUpMC();
        myShortAnswerPane.setText(myQuestion.getPrompt());
        for (int i = 0; i <= myQuestion.getIncorrectAnswers().size()-1;i ++) {
            JRadioButton rdBtn = new JRadioButton((myQuestion.getIncorrectAnswers().get(i)).get());
            rdbtnsAnswers.add(rdBtn);
            mcButtonGroup.add(rdBtn);
        }
        JRadioButton cAnswer = new JRadioButton(myQuestion.getCorrectAnswer().get());
        rdbtnsAnswers.add(cAnswer);
        mcButtonGroup.add(cAnswer);

        Random r = new Random();
        int rand;
        for (int i = 0; i <= myQuestion.getIncorrectAnswers().size();i ++) {
            rand = r.nextInt(rdbtnsAnswers.size());
            if (rand != i) {
                Collections.swap(rdbtnsAnswers, i, rand);
            }
        }
        for (int i = 0; i <= myQuestion.getIncorrectAnswers().size();i ++) {
            myQuestionButtons.add(rdbtnsAnswers.get(i));
        }
        add(myQuestionButtons, BorderLayout.SOUTH);
        submitButton();
    }

    private void createTFButtons() {
        //cleanUpPanelTF();
        myShortAnswerPane.setText(myQuestion.getPrompt());

        JRadioButton trueButton = new JRadioButton("True");
        tfButtonGroup.add(trueButton);
        myQuestionButtons.add(trueButton, BorderLayout.SOUTH);

        JRadioButton falseButton = new JRadioButton("False");
        tfButtonGroup.add(falseButton);
        myQuestionButtons.add(falseButton, BorderLayout.SOUTH);
        submitButton();
    }
    private void submitButton() {
        JButton subBtn = new JButton("Submit Answer");
        myQuestionButtons.add(subBtn);
    }

    private void clearList() {
        for (int i = 0;i <= rdbtnsAnswers.size()-1;i++){
            mainPanel.remove(rdbtnsAnswers.remove(i));
        }

    }
}
