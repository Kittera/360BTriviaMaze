package gameui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InGamePanel extends JPanel {

    private JPanel myPanel;
    QuestionPanel myQuestionPanel;
    private JPanel myRoomPanel;
    private GridBagConstraints gbc;

    public InGamePanel(ButtonGroup catButtonGroup) {
        createPanel();
    }

    public InGamePanel(ButtonModel selection, ButtonModel selection1) { createPanel();
        //startGame method with selection difficulty and selection1 question categories
    }
    public InGamePanel() {
        createPanel();
    }
    private void createPanel() {

        ArrayList<Answer> tempc =  new ArrayList<Answer>();
        tempc.add(new Answer("Mic"));
        tempc.add(new Answer("Mike"));
        tempc.add(new Answer("Scott"));
        Question temp = new Question(Category.ANY, QuestionType.MULTI_CHOICE, Difficulty.EASY, "What is my Name?",new Answer("Steven"),tempc) ;

        myQuestionPanel = new QuestionPanel();
        myQuestionPanel.setPanelQuestion(temp);
        gbc = new GridBagConstraints();
        setLayout(new BorderLayout());
        setLocation(0,0);
        setBackground(Color.BLACK);
        setSize(1000, 700);
        myRoomPanel = new RoomPanel(); // this should call start of maze
//        gbc.gridwidth = 4;
//        gbc.gridheight = 2;
//        gbc.gridx = 4;
//        gbc.gridy = 2;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(myQuestionPanel, BorderLayout.EAST);



    }
    //todo this will be the panel that houses the door panel/info(stat panel)/question panel

}
