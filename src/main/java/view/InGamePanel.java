package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class creates a JPanel obect that containes the other panels needed for
 * the trivia game to run.
 */

public class InGamePanel extends JPanel {

    /**
     * Fields for guess limits
     */
    private static final int TF_GUESS_LIMIT = 1;
    private static final int MC_GUESS_LIMIT = 3;
    private static final int SA_GUESS_LIMIT = 3;
    /**
     * Panel Height
     */
    private static final int MOVE_BUTTON_PANEL_HEIGHT = 45;

    /**
     * Maze object/panel to display
     */
    private TriviaMaze myMaze;
    /**
     * Question panel that displays different question types
     */
    private QuestionPanel myQuestionPanel;
    /**
     * The Room the player is currently in
     */
    private MazeRoom myRoom;
    /**
     * Current player in the maze
     */
    private Player myPlayer;
    /**
     * The chosen direction the player wishes to move
     */
    private Direction myDirection;
    /**
     * The menubar for the frame.
     */
    private InGameMenuBar bar;

    /**
     * The current game
     */
    private Game currentGame;

    /**
     * The number of guesses
     */
    private int myGuesses;

    /**
     * Button Panel
     */
    private JPanel moveButtonPanel;
    /**
     * movement directions
     */
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    /**
     * Submit Button
     */
    private JButton submitBtn;


    /**
     * Parametarized constructor for the class
     * @param theCategory category Enum type
     * @param theDifficulty Difficulty Enum
     */
    public InGamePanel(Category theCategory, Difficulty theDifficulty) {

        myMaze = new TriviaMaze(7, 10, theCategory, theDifficulty);
        myRoom = myMaze.getRoom(1, 1);
        myPlayer = new Player(myRoom);
        myMaze.addPlayer(myPlayer);
        myGuesses = 0;
        createPanel();
        createMoveButtons();
        checkDoors();

        add(myMaze);
        add(myQuestionPanel, BorderLayout.EAST);
        add(moveButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * empty constructor
     */
    public InGamePanel() {

    }

    /**
     * This is the method to save a game from the panel
     * @return a new game.
     */
    public Game InGamePanelSave(){
        return new Game(myMaze, myQuestionPanel, myRoom, myPlayer, myDirection, myGuesses);
    }

    /**
     * This method loads a game from a load file
     * @param load the game be passed in
     */
    public void InGamePanelLoad(Game load){
        this.myMaze = load.getMyMaze();
        this.myQuestionPanel = load.getMyQuestionPanel();
        this.myRoom = load.getMyRoom();
        this.myPlayer = load.getMyPlayer();
        this.myDirection = load.getMyDirection();
        this.myGuesses = load.getMyGuesses();

        createPanel();
        createMoveButtons();
        checkDoors();
        add(myMaze);
        add(myQuestionPanel, BorderLayout.EAST);
        add(moveButtonPanel, BorderLayout.SOUTH);

    }

    /**
     * Creates panel to be displayed for the trivia maze.
     */
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

    /**
     * creates movement buttons
     */
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
                        myDirection = theDirection;
                        checkGuessesRem();
                        myQuestionPanel.setPanelQuestion(door.getQuestion());
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

    /**
     * Movements Action listeners
     */
    private final ActionListener MoveNorth = event -> handleMove(Direction.NORTH);
    private final ActionListener MoveSouth = event -> handleMove(Direction.SOUTH);
    private final ActionListener MoveEast = event -> handleMove(Direction.EAST);
    private final ActionListener MoveWest = event -> handleMove(Direction.WEST);


    /**
     * Submit Button Action listener
     */
    private final ActionListener SubmitAnswer = event -> {
        if (!myQuestionPanel.isCorrectAnswer().get().equalsIgnoreCase("Wrong")) {
            if (myRoom.getDoor(myDirection).get().tryAnswer(myQuestionPanel.isCorrectAnswer())) {
                myMaze.movePlayer(myDirection);
                myRoom = myPlayer.getCurrentRoom();
            } else {
                JOptionPane.showMessageDialog(null,
                        "The door did not unlock. Guesses Remaining: " + myGuesses,
                        "Incorrect Answer" ,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        myQuestionPanel.createBlank();
        setRoomsVisible(true);
        checkDoors();
        revalidate();
        currentGame = InGamePanelSave();
        bar = new InGameMenuBar();
        bar.setCurrentGame(currentGame);
        this.getRootPane().setJMenuBar(bar.getBar());
    };

    private void checkGuessesRem() {
        switch (myRoom.getDoor(myDirection).get().getQuestion().getType()) {
            case TRUE_FALSE -> {
                checkForLoss((TF_GUESS_LIMIT -
                        myRoom.getDoor(myDirection)
                                .get()
                                .getQuestion()
                                .getAttemptCount()));
            }
            case MULTI_CHOICE -> {
                checkForLoss((MC_GUESS_LIMIT -
                        myRoom.getDoor(myDirection)
                                .get()
                                .getQuestion()
                                .getAttemptCount()));
            }
            case SHORT_ANSWER -> {
                checkForLoss((SA_GUESS_LIMIT -
                        myRoom.getDoor(myDirection)
                                .get()
                                .getQuestion()
                                .getAttemptCount()));
            }
        }
    }
    private void checkForLoss(final int theInt) {
        myGuesses = theInt;
        if(theInt < 0 ) {
            JOptionPane.showMessageDialog(null,
                    "You have ran out of attempts! Game Over!",
                    "Incorrect Answer" ,
                    JOptionPane.ERROR_MESSAGE);
            //getRootPane().removeAll();
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            remove(myMaze);
            //topFrame.remove(this);
            topFrame.setContentPane(new MainMenu());
            topFrame.pack();
            topFrame.setLocationRelativeTo(null);
        }
    }

    private void setRoomsVisible(boolean theFlag) {
        north.setEnabled(theFlag);
        south.setEnabled(theFlag);
        east.setEnabled(theFlag);
        west.setEnabled(theFlag);
    }
}


