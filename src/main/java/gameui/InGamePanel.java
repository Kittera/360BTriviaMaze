package gameui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InGamePanel extends JPanel {

    private TriviaMaze myMaze;
    private QuestionPanel myQuestionPanel;
    private MazeRoom myRoom;
    private Player myPlayer;
    private Direction myDirection;

    private JPanel moveButtonPanel;
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    private JButton submitBtn;
    private GridBagConstraints gbc;


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
    private void createPanel() {

        myQuestionPanel = new QuestionPanel();

        moveButtonPanel = new JPanel();
        moveButtonPanel.setPreferredSize(new Dimension(500, 30));

        setLayout(new BorderLayout());
        setLocation(0,0);
        setBackground(Color.BLACK);
        setSize(1000, 700);


    }

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

        submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(100, 30));
        submitBtn.addActionListener(SubmitAnswer);

        moveButtonPanel.add(submitBtn, gbc);
        moveButtonPanel.revalidate();
    }

    private void checkDoors() {

//        if (!myRoom.getDoor(Direction.NORTH).isPresent()) {
//            north.setVisible(false);
//        }
//        if (!myRoom.getDoor(Direction.EAST).isPresent()) {
//            east.setVisible(false);
//        }
//        if (!myRoom.getDoor(Direction.SOUTH).isPresent()) {
//            south.setVisible(false);
//        }
//        if (!myRoom.getDoor(Direction.WEST).isPresent()) {
//            west.setVisible(false);
//        }
//        submitBtn.setVisible(false);
        revalidate();
        repaint();
    }

    private ActionListener MoveNorth = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.NORTH)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.NORTH).get().getQuestion());
            myDirection = Direction.NORTH;
        } else {
            myMaze.movePlayer(Direction.NORTH);
        }
    };

    private ActionListener MoveSouth = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.SOUTH)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.SOUTH).get().getQuestion());
            myDirection = Direction.SOUTH;
        } else {
            myMaze.movePlayer(Direction.SOUTH);
        }
    };

    private ActionListener MoveEast = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.EAST)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.EAST).get().getQuestion());
            myDirection = Direction.EAST;
        } else {
            myMaze.movePlayer(Direction.EAST);
        }
    };
    private ActionListener MoveWest = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.WEST)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.WEST).get().getQuestion());
            myDirection = Direction.WEST;
        } else {
            myMaze.movePlayer(Direction.WEST);
        }
    };

    private ActionListener SubmitAnswer = event -> {
        if (myQuestionPanel.isCorrectAnswer()) {
            myRoom.getDoor(myDirection).get().tryAnswer(myRoom.getDoor(myDirection).get().getQuestion().getCorrectAnswer());
            myMaze.movePlayer(myDirection);
            myRoom = myPlayer.getCurrentRoom();
        }
        checkDoors();
        revalidate();
    };
}
