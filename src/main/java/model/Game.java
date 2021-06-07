package model;
import gameui.QuestionPanel;

import java.io.Serializable;

public class Game implements Serializable {
    private static final long serialVersionUID = 12532321L;
    public Game(TriviaMaze myMaze, QuestionPanel myQuestionPanel, MazeRoom myRoom, Player myPlayer, Direction myDirection) {
        this.myMaze = myMaze;
        this.myQuestionPanel = myQuestionPanel;
        this.myRoom = myRoom;
        this.myPlayer = myPlayer;
        this.myDirection = myDirection;
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

    TriviaMaze myMaze;
    QuestionPanel myQuestionPanel;
    MazeRoom myRoom;
    Player myPlayer;
    Direction myDirection;

}