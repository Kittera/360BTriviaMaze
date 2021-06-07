package gameui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;



public class InGamePanel extends JPanel {

    private final static String WRONGANSWER = "Choose another direction.";
    public InGameMenuBar menuBar;
    private TriviaMaze myMaze;
    private QuestionPanel myQuestionPanel;
    private MazeRoom myRoom;
    private Player myPlayer;
    private Direction myDirection;
    private int myGuesses;

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
        menuBar.setCurrentGame(InGamePanelSave());
        InGameMenuBar myMenu = new InGameMenuBar();
        myMenu.setCurrentGame(InGamePanelSave());
        //setJMenuBar(myMenu.getBar());



    }

    public InGamePanel() {
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
    public Game InGamePanelSave(){
        return new Game(myMaze, myQuestionPanel, myRoom, myPlayer, myDirection);
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
        submitBtn.setVisible(false);
        revalidate();
        repaint();
    }


    private void handleMove(Direction theDirection) {
        myPlayer.getCurrentRoom().getDoor(theDirection).ifPresent(
                door -> {
                    if (door.isLocked()) {
                        myQuestionPanel.setPanelQuestion(door.getQuestion());
                        myDirection = theDirection;
                        submitBtn.setVisible(true);
                    } else {
                        myQuestionPanel.createBlank();
                        myMaze.movePlayer(theDirection);
                        submitBtn.setVisible(false);
                    }
                }
        );
    }

    private final ActionListener MoveNorth = event -> handleMove(Direction.NORTH);
    private final ActionListener MoveSouth = event -> handleMove(Direction.SOUTH);
    private final ActionListener MoveEast = event -> handleMove(Direction.EAST);
    private final ActionListener MoveWest = event -> handleMove(Direction.WEST);


    private ActionListener SubmitAnswer = event -> {
        if (myQuestionPanel.isCorrectAnswer()) {
            myRoom.getDoor(myDirection).get().tryAnswer(myRoom.getDoor(myDirection).get().getQuestion().getCorrectAnswer());
            myMaze.movePlayer(myDirection);
            myRoom = myPlayer.getCurrentRoom();
        }
        myQuestionPanel.createBlank();
        checkDoors();
        revalidate();
        //menuBar.setCurrentGame(InGamePanelSave());
        //setJMenuBar(menuBar.getBar());
    };
}

