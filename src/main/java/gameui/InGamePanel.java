package gameui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


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

        submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(100, 30));
        submitBtn.addActionListener(SubmitAnswer);

        north.addActionListener(MoveNorth);
        south.addActionListener(MoveSouth);
        east.addActionListener(MoveEast);
        west.addActionListener(MoveWest);

        moveButtonPanel.add(north);
        moveButtonPanel.add(south);
        moveButtonPanel.add(east);
        moveButtonPanel.add(west);

        moveButtonPanel.add(submitBtn);
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
        myPlayer.getCurrentRoom().getDoor(Direction.NORTH).ifPresent(
                door -> {
                    if (door.isLocked()) {
                        myQuestionPanel.setPanelQuestion(door.getQuestion());
                        myDirection = Direction.NORTH;
                    } else {
                        myQuestionPanel.createBlank();
                        myMaze.movePlayer(Direction.NORTH);
                    }
                }
        );
    };

    private ActionListener MoveSouth = event -> {
        myPlayer.getCurrentRoom().getDoor(Direction.SOUTH).ifPresent(
                door -> {
                    if (door.isLocked()) {
                        myQuestionPanel.setPanelQuestion(door.getQuestion());
                        myDirection = Direction.SOUTH;
                    } else {
                        myQuestionPanel.createBlank();
                        myMaze.movePlayer(Direction.SOUTH);
                    }
                }
        );
    };

    private ActionListener MoveEast = event -> {
        myPlayer.getCurrentRoom().getDoor(Direction.EAST).ifPresent(
                door -> {
                    if (door.isLocked()) {
                        myQuestionPanel.setPanelQuestion(door.getQuestion());
                        myDirection = Direction.EAST;
                    } else {
                        myQuestionPanel.createBlank();
                        myMaze.movePlayer(Direction.EAST);
                    }
                }
        );
    };

    private ActionListener MoveWest = event -> {
        myPlayer.getCurrentRoom().getDoor(Direction.WEST).ifPresent(
                door -> {
                    if (door.isLocked()) {
                        myQuestionPanel.setPanelQuestion(door.getQuestion());
                        myDirection = Direction.WEST;
                    } else {
                        myQuestionPanel.createBlank();
                        myMaze.movePlayer(Direction.WEST);
                    }
                }
        );
    };

    private ActionListener SubmitAnswer = event -> {
        if (myQuestionPanel.isCorrectAnswer()) {
            myRoom.getDoor(myDirection).get().tryAnswer(myRoom.getDoor(myDirection).get().getQuestion().getCorrectAnswer());
            myMaze.movePlayer(myDirection);
            myRoom = myPlayer.getCurrentRoom();
        }
        myQuestionPanel.createBlank();
        checkDoors();
        revalidate();
    };
}
