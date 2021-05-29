package model;

public class Player {
   private MazeRoom myRoom;
   
   Player(final MazeRoom theStartingRoom) {
      myRoom = theStartingRoom;
   }
   
   public MazeRoom getRoom() {
      return myRoom;
   }
   
   public void moveToRoom(final MazeRoom theNewRoom) {
      if (theNewRoom == null) {
         throw new IllegalArgumentException("Null room object passed to player.");
      }
      
      myRoom = theNewRoom;
   }
}
