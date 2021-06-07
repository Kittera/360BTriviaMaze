package gameui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class InGamePanel extends JPanel implements Serializable {
    private static final long serialVersionUID = 12561L;
    TriviaMaze myMaze;
    QuestionPanel myQuestionPanel;
    MazeRoom myRoom;
    Player myPlayer;
    Direction myDirection;

    private transient JPanel moveButtonPanel;
    private transient JButton north;
    private transient JButton south;
    private transient JButton east;
    private transient JButton west;
    private transient JButton submitBtn;
    private transient GridBagConstraints gbc;

    public InGamePanel(Category theCategory, Difficulty theDifficulty) {
        myMaze = new TriviaMaze(10, 7, theCategory, theDifficulty); //maybe change size based on difficulty?
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

    public Game InGamePanelSave(){
        return new Game(myMaze, myQuestionPanel, myRoom, myPlayer, myDirection);
    }

    public void InGamePanelLoad(Game load){
        this.myMaze = load.getMyMaze();
        this.myQuestionPanel = load.getMyQuestionPanel();
        this.myRoom = load.getMyRoom();
        this.myPlayer = load.getMyPlayer();
        this.myDirection = load.getMyDirection();

        createPanel();
        createMoveButtons();
        checkDoors();
        add(myMaze);
        add(myQuestionPanel, BorderLayout.EAST);
        add(moveButtonPanel, BorderLayout.SOUTH);
    }


    private void createPanel() {



       // could pull other questions, just haven't used test questions yet

        //test of panels
        myQuestionPanel = new QuestionPanel();
        moveButtonPanel = new JPanel(new GridBagLayout());

        setLayout(new BorderLayout());
        setLocation(0,0);
        setBackground(Color.BLACK);
        setSize(1000, 700);


    }
    //todo this will be the panel that houses the door panel/info(stat panel)/question panel

    private void createMoveButtons() {

        gbc = new GridBagConstraints();
        gbc.gridwidth = 5;
        gbc.gridheight = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
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

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        moveButtonPanel.add(north, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        moveButtonPanel.add(south, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        moveButtonPanel.add(east, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        moveButtonPanel.add(west, gbc);
        moveButtonPanel.setPreferredSize(new Dimension(500, 30));

        submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(100, 30));
        submitBtn.addActionListener(SubmitAnswer);

        gbc.gridx = 4;
        gbc.gridy = 0;
        moveButtonPanel.add(submitBtn);
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
        revalidate();
        repaint();
        submitBtn.setVisible(false);
    }

    private ActionListener MoveNorth = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.NORTH)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.NORTH).get().getQuestion());
            myDirection = Direction.NORTH;
        } else {
            myMaze.movePlayer(Direction.NORTH);
        }
        submitBtn.setVisible(true);
    };
    private ActionListener MoveSouth = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.SOUTH)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.SOUTH).get().getQuestion());
            myDirection = Direction.SOUTH;
        } else {
            myMaze.movePlayer(Direction.SOUTH);
        }
        submitBtn.setVisible(true);
    };
    private ActionListener MoveEast = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.EAST)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.EAST).get().getQuestion());
            myDirection = Direction.EAST;
        } else {
            myMaze.movePlayer(Direction.EAST);
        }
        submitBtn.setVisible(true);
    };
    private ActionListener MoveWest = event -> {
        if ((myPlayer.getCurrentRoom().getDoor(Direction.WEST)).get().isLocked()) {
            myQuestionPanel.setPanelQuestion(myPlayer.getCurrentRoom().getDoor(Direction.WEST).get().getQuestion());
            myDirection = Direction.WEST;
        } else {
            myMaze.movePlayer(Direction.WEST);
        }
        submitBtn.setVisible(true);
    };
    private ActionListener SubmitAnswer = event -> {

        if (myQuestionPanel.isCorrectAnswer()) {
            myMaze.getRoom(myPlayer.getLocation().x, myPlayer.getLocation().y ).getDoor(myDirection).get().tryAnswer(
                    myMaze.getRoom(myPlayer.getLocation().x, myPlayer.getLocation().y ).getDoor(myDirection).get().getQuestion().getCorrectAnswer());

            myMaze.movePlayer(myDirection);
            myRoom = myPlayer.getCurrentRoom();

        }
        System.out.println(myRoom.getDoor(myDirection).get().getQuestion().getCorrectAnswer().get());
        checkDoors();
        revalidate();
        repaint();
    };
}
