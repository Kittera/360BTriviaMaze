package model;

import view.InGameMenuBar;
import view.InGamePanel;
import view.QuestionPanel;

import java.io.Serial;
import java.io.Serializable;

public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 12532321L;
    public Game(TriviaMaze myMaze, QuestionPanel myQuestionPanel, MazeRoom myRoom, Player myPlayer, Direction myDirection, int myGuesses) {
        this.myMaze = myMaze;
        this.myQuestionPanel = myQuestionPanel;
        this.myRoom = myRoom;
        this.myPlayer = myPlayer;
        this.myDirection = myDirection;
        this.myGuesses = myGuesses;
    }

    public TriviaMaze getMyMaze() {
        return myMaze;
    }

    public QuestionPanel getMyQuestionPanel() {
        return myQuestionPanel;
    }

    public MazeRoom getMyRoom() {
        return myRoom;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public Direction getMyDirection() {
        return myDirection;
    }

    public int getMyGuesses() {
        return myGuesses;
    }

    TriviaMaze myMaze;
    QuestionPanel myQuestionPanel;
    MazeRoom myRoom;
    Player myPlayer;
    Direction myDirection;
    int myGuesses;

    public InGamePanel getCurrentState() {
        return currentState;
    }

    public void setCurrentState(InGamePanel currentState) {
        this.currentState = currentState;
    }

    public InGameMenuBar getBar() {
        return bar;
    }

    public void setBar(InGameMenuBar bar) {
        this.bar = bar;
    }

    transient InGamePanel currentState;
    transient InGameMenuBar bar;

}