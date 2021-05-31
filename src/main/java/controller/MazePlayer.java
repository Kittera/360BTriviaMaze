package controller;

import model.MazeRoom;

public interface MazePlayer {
   
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
}
