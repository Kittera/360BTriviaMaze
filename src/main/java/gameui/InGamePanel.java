package gameui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InGamePanel extends JPanel {
    TriviaMaze myMaze;
    QuestionPanel myQuestionPanel;
    MazeRoom myRoom;
    Player myPlayer;

    private JPanel moveButtonPanel;
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;


    public InGamePanel(Category theCategory, Difficulty theDifficulty) {
        myMaze = new TriviaMaze(4, 4, theCategory, theDifficulty); //maybe change size based on difficulty?
        myRoom = myMaze.getRoom(1, 1);
        myPlayer = new Player(myRoom);
        myMaze.addPlayer(myPlayer);
        createPanel();
        createMoveButtons();
        checkDoors();
        add(myMaze);
        add(myQuestionPanel, BorderLayout.EAST);
        add(moveButtonPanel, BorderLayout.SOUTH);

    }
    public InGamePanel() {
        createPanel();
    }


    private void createPanel() {



       // could pull other questions, just haven't used test questions yet

        ArrayList<Answer> tempc =  new ArrayList<Answer>();
        tempc.add(new Answer("Mic"));
        tempc.add(new Answer("Mike"));
        tempc.add(new Answer("Scott"));
        Question temp = new Question(Category.ANY, QuestionType.MULTI_CHOICE, Difficulty.EASY, "What is my Name?",new Answer("Steven"),tempc) ;

        //test of panels
        myQuestionPanel = new QuestionPanel();
        myQuestionPanel.setPanelQuestion(temp);
        moveButtonPanel = new JPanel();

        setLayout(new BorderLayout());
        setLocation(0,0);
        setBackground(Color.BLACK);
        setSize(1000, 700);


    }
    //todo this will be the panel that houses the door panel/info(stat panel)/question panel

    private void createMoveButtons() {
        north = new JButton("North");
        north.setPreferredSize(new Dimension(100, 30));
        south = new JButton("South");
        south.setPreferredSize(new Dimension(100, 30));
        east = new JButton("East");
        east.setPreferredSize(new Dimension(100, 30));
        west = new JButton("West");
        west.setPreferredSize(new Dimension(100, 30));
        north.addActionListener(MoveNorth);
        south.addActionListener(MoveSouth);
        east.addActionListener(MoveEast);
        west.addActionListener(MoveWest);

        moveButtonPanel.add(north);
        moveButtonPanel.add(south);
        moveButtonPanel.add(east);
        moveButtonPanel.add(west);
        moveButtonPanel.setPreferredSize(new Dimension(500, 30));
    }

    private void checkDoors() {
        if (!myRoom.getDoor(Direction.NORTH).isPresent()) {
            north.setVisible(false);
        }
        if (!myRoom.getDoor(Direction.EAST).isPresent()) {
            east.setVisible(false);
        }
        if (!myRoom.getDoor(Direction.SOUTH).isPresent()) {
            south.setVisible(false);
        }
        if (!myRoom.getDoor(Direction.WEST).isPresent()) {
            west.setVisible(false);
        }
    }

    private ActionListener MoveNorth = event -> {
        myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.NORTH).get().getQuestion());
    };

    private ActionListener MoveSouth = event -> {
        myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.SOUTH).get().getQuestion());
    };

    private ActionListener MoveEast = event -> {
        myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.EAST).get().getQuestion());
    };

    private ActionListener MoveWest = event -> {
        myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.WEST).get().getQuestion());
    };
}
