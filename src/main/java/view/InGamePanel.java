package view;

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
    private int myGuesses;

    private static final int MOVE_BUTTON_PANEL_HEIGHT = 45;

    private JPanel moveButtonPanel;
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    private JButton submitBtn;



    public InGamePanel(Category theCategory, Difficulty theDifficulty) {
        myMaze = new TriviaMaze(5, 8, theCategory, theDifficulty);
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

    }

    private void createPanel() {
        myQuestionPanel = new QuestionPanel();

        moveButtonPanel = new JPanel();
        moveButtonPanel.setPreferredSize(
                new Dimension(500, MOVE_BUTTON_PANEL_HEIGHT)
        );

        final Dimension mazeDims = myMaze.getPreferredSize();

        setLayout(new BorderLayout());
        setLocation(0,0);
        setBackground(Color.BLACK);

        int finalHeight = mazeDims.height + moveButtonPanel.getPreferredSize().height;
        int finalWidth = mazeDims.width + myQuestionPanel.getPreferredSize().width;

        setPreferredSize(new Dimension(finalWidth, finalHeight));
    }


   private void createMoveButtons() {
        Dimension buttonSize = new Dimension(100, 30);
        north = new JButton("North");
        north.setPreferredSize(buttonSize);
        south = new JButton("South");
        south.setPreferredSize(buttonSize);
        east = new JButton("East");
        east.setPreferredSize(buttonSize);
        west = new JButton("West");
        west.setPreferredSize(buttonSize);

        submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(buttonSize);
        submitBtn.addActionListener(SubmitAnswer);

        north.addActionListener(MoveNorth);
        south.addActionListener(MoveSouth);
        east.addActionListener(MoveEast);
        west.addActionListener(MoveWest);

        moveButtonPanel.add(west);
        moveButtonPanel.add(north);
        moveButtonPanel.add(south);
        moveButtonPanel.add(east);
        moveButtonPanel.add(submitBtn);

        moveButtonPanel.revalidate();
    }

    private void checkDoors() {
        submitBtn.setEnabled(false);

        if (myRoom.getLocation().equals(myMaze.getEndingRoom().getLocation())) {
            JOptionPane.showMessageDialog(
                    null,
                    "You Have Won",
                    "Victory",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        north.setEnabled(myPlayer.getCurrentRoom().getDoor(Direction.NORTH).isPresent());
        south.setEnabled(myPlayer.getCurrentRoom().getDoor(Direction.SOUTH).isPresent());
        east.setEnabled(myPlayer.getCurrentRoom().getDoor(Direction.EAST).isPresent());
        west.setEnabled(myPlayer.getCurrentRoom().getDoor(Direction.WEST).isPresent());
        revalidate();
        repaint();
    }


    private void handleMove(Direction theDirection) {
        myPlayer.getCurrentRoom().getDoor(theDirection).ifPresent(
                door -> {
                    if (door.isLocked()) {
                        myQuestionPanel.setPanelQuestion(door.getQuestion());
                        myDirection = theDirection;
                        submitBtn.setEnabled(true);
                    } else {
                        myQuestionPanel.createBlank();
                        myMaze.movePlayer(theDirection);
                        setRoomsVisible(true);
                        checkDoors();
                        submitBtn.setEnabled(false);
                    }
                }
        );
    }
    private final ActionListener MoveNorth = event -> handleMove(Direction.NORTH);
    private final ActionListener MoveSouth = event -> handleMove(Direction.SOUTH);
    private final ActionListener MoveEast = event -> handleMove(Direction.EAST);
    private final ActionListener MoveWest = event -> handleMove(Direction.WEST);

    private final ActionListener SubmitAnswer = event -> {
        if (myQuestionPanel.isCorrectAnswer()) {
            myRoom.getDoor(myDirection).get().tryAnswer(myRoom.getDoor(myDirection).get().getQuestion().getCorrectAnswer());
            myMaze.movePlayer(myDirection);
            myRoom = myPlayer.getCurrentRoom();

        }
        myQuestionPanel.createBlank();
        setRoomsVisible(true);
        checkDoors();
        revalidate();
    };

    private void setRoomsVisible(boolean theFlag) {
        north.setEnabled(theFlag);
        south.setEnabled(theFlag);
        east.setEnabled(theFlag);
        west.setEnabled(theFlag);
    }
}


