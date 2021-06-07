package controller;

import model.MazeRoom;

import java.awt.*;
import java.io.Serializable;

public interface MazePlayer extends Serializable {
   
   /**
    * Get the room the player is currently in
    * @return player's current room
    */
   MazeRoom getCurrentRoom();
   
   /**
    * Updates the room reference in this Player object in order to "move" it.
    * @param theRoom Maze Room to move to
    */
   void sendToRoom(MazeRoom theRoom);
   
   /**
    * Finds the player's map coordinates.
    * @return Point where x is row and y is column with (0, 0) at top left
    */
   Point getLocation();
}
