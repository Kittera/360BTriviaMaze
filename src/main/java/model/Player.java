package model;

import controller.MazePlayer;

import java.util.Objects;

public class Player implements MazePlayer {
   
   private MazeRoom myRoom;
   
   Player(final MazeRoom theStartingRoom) {
      myRoom = theStartingRoom;
   }
   
   public void sendToRoom(final MazeRoom theNewRoom) {
      if (Objects.isNull(theNewRoom)) {
         throw new IllegalArgumentException("Null room object passed to player.");
      }
      myRoom = theNewRoom;
   }
   
   @Override
   public MazeRoom getCurrentRoom() {
      return myRoom;
   }
}
