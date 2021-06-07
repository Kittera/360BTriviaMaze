package model;

import controller.MazePlayer;

import java.io.Serializable;


public interface Maze extends Serializable {
   
   /**
    * Get a specific room from the model.
    *
    * @param theRow row of room to get
    * @param theCol column of room to get
    * @return the room if found
    */
   MazeRoom getRoom(int theRow, int theCol);
   
   /**
    * Attempts to move the player to an adjacent room
    *
    * @param theDirection direction to attempt move
    * @return whether move could be made.
    */
   boolean movePlayer(Direction theDirection);
   
   /**
    * Set a Player into this Maze.
    *
    * @param thePlayer Player to be interred into the maze at the stating point.
    */
   boolean addPlayer(MazePlayer thePlayer);
}
