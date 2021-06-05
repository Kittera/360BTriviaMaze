package gameui;

import model.Question;
import model.QuestionType;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionPanel extends JPanel {
    private final ButtonGroup mcButtonGroup = new ButtonGroup();
    private final ButtonGroup tfButtonGroup = new ButtonGroup();

    private JPanel mainPanel;

    private JPanel myQuestionButtons;

    private Question myQuestion;

    private JTextPane myQuestionPane;

    private ArrayList<JRadioButton> rdbtnsAnswers;

    private JTextPane myShortAnswerPane;

    public QuestionPanel() {

        //initialize fields
        rdbtnsAnswers = new ArrayList();
        mainPanel = new JPanel();

        // set Layouts
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(300, 700));

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
    public boolean isCorrectAnswer() {
        return mcButtonGroup.getSelection().getActionCommand() == myQuestion.getCorrectAnswer().get();
    }

    public void setPanelQuestion(Question theQuestion) {
        myShortAnswerPane.setVisible(false);
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
            rdbtnsAnswers.add(rdBtn);
            mcButtonGroup.add(rdBtn);
        }
        JRadioButton cAnswer = new JRadioButton(myQuestion.getCorrectAnswer().get());
        cAnswer.setActionCommand(myQuestion.getCorrectAnswer().get());
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
    }

    private void createTFButtons() {
        clearList();
        myQuestionButtons.setVisible(true);
        myQuestionPane.setText(myQuestion.getPrompt());

        JRadioButton trueButton = new JRadioButton("True");
        mcButtonGroup.add(trueButton);
        myQuestionButtons.add(trueButton, BorderLayout.SOUTH);
        rdbtnsAnswers.add(trueButton);

        JRadioButton falseButton = new JRadioButton("False");
        mcButtonGroup.add(falseButton);
        myQuestionButtons.add(falseButton, BorderLayout.SOUTH);
        rdbtnsAnswers.add(falseButton);

    }
    private void submitButton() {
        JButton subBtn = new JButton("Submit Answer");
        myQuestionButtons.add(subBtn);
    }

    private void clearList() {
        for (int i = 0;i <= rdbtnsAnswers.size()-1;i++){
            mcButtonGroup.remove(rdbtnsAnswers.get(i));
            rdbtnsAnswers.get(i).setVisible(false);
            myQuestionButtons.remove(rdbtnsAnswers.get(i));
        }
        rdbtnsAnswers.removeAll(rdbtnsAnswers);

    }
}
