package gameui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InGamePanel extends JPanel {
    TriviaMaze myMaze;

    QuestionPanel myQuestionPanel;

    private GridBagConstraints gbc;


    public InGamePanel(Category theCategory, Difficulty theDifficulty) {
        createPanel();
        myMaze = new TriviaMaze(4, 4, theCategory, theDifficulty); //maybe change size based on difficulty?
        add(myMaze);

    }
    public InGamePanel() {
        createPanel();
    }


    private void createPanel() {



        //could pull other questions, just haven't used test questions yet

        ArrayList<Answer> tempc =  new ArrayList<Answer>();
        tempc.add(new Answer("Mic"));
        tempc.add(new Answer("Mike"));
        tempc.add(new Answer("Scott"));
        Question temp = new Question(Category.ANY, QuestionType.MULTI_CHOICE, Difficulty.EASY, "What is my Name?",new Answer("Steven"),tempc) ;

        //test of panels
        myQuestionPanel = new QuestionPanel();
        myQuestionPanel.setPanelQuestion(temp);


        gbc = new GridBagConstraints();



        setLayout(new BorderLayout());
        setLocation(0,0);
        setBackground(Color.BLACK);
        setSize(1000, 700);


        add(myQuestionPanel, BorderLayout.EAST);



    }
    //todo this will be the panel that houses the door panel/info(stat panel)/question panel

}
