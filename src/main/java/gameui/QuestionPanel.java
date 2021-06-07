package gameui;

import model.Answer;
import model.Question;
import model.QuestionType;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionPanel extends JPanel {
    private final ButtonGroup mcButtonGroup = new ButtonGroup();

    private JOptionPane myPopUp;

    private JPanel mainPanel;

    private JPanel myQuestionButtons;

    private Question myQuestion;

    private JTextPane myQuestionPane;

    private ArrayList<JRadioButton> myAnswers;

    private JTextPane myShortAnswerPane;

    public QuestionPanel() {

        //initialize fields
        myAnswers = new ArrayList();
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

    /**
     * Checks if the answer for the question is correct
     * @return boolean
     */

    public boolean isCorrectAnswer() {
        boolean temp = false;
        if (mcButtonGroup.getSelection() != null) {
            temp = mcButtonGroup.getSelection().getActionCommand().equals(myQuestion.getCorrectAnswer().get());
        } else {
            myPopUp.showMessageDialog(null, "Please Choose an answer", "No Answer" , JOptionPane.OK_OPTION);
        }
        if (!temp && mcButtonGroup.getSelection() != null) {
            myPopUp.showMessageDialog(null, "", "Incorrect Answer" , JOptionPane.OK_OPTION);
        }
        return temp;
    }


//    public boolean isCorrectAnswer() {
//        Answer temp;
//        boolean myAnswer = false;
//        if (mcButtonGroup.getSelection() != null) {
//            System.out.println("we got here");
//            temp = new Answer(mcButtonGroup.getSelection().getActionCommand());
//        } else {
//            temp = new Answer("Wrong");
//            myPopUp.showMessageDialog(null, "Please Choose an answer", "No Answer" , JOptionPane.OK_OPTION);
//        }
//        System.out.println(temp.get());
//        System.out.println(myQuestion.getCorrectAnswer().get());
//        System.out.println(myAnswer);
//        myAnswer = myQuestion.tryAnswer(temp);
//        if (!myAnswer && mcButtonGroup.getSelection() != null) {
//            myPopUp.showMessageDialog(null, "", "Incorrect Answer" , JOptionPane.OK_OPTION);
//        }
//        return myAnswer;
//    }

    /**
     * Takes in a question and creates the buttons based off question Type
     * @param theQuestion
     */
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
        for (int i = 0;i <= myAnswers.size()-1;i++){
            mcButtonGroup.remove(myAnswers.get(i));
            myAnswers.get(i).setVisible(false);
            myQuestionButtons.remove(myAnswers.get(i));
        }
        myAnswers.removeAll(myAnswers);
    }

}
