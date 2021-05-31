package model;

import controller.MazePlayer;

public class Player implements MazePlayer {
   
   private MazeRoom myRoom;
   
   Player(final MazeRoom theStartingRoom) {
      myRoom = theStartingRoom;
   }
   
   public void sendToRoom(final MazeRoom theNewRoom) {
      if (theNewRoom == null) {
         throw new IllegalArgumentException("Null room object passed to player.");
      }
      myRoom = theNewRoom;
   }
   
   @Override
   public MazeRoom getCurrentRoom() {
      return myRoom;
   }
}
