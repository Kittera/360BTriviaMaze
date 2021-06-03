package model;

import controller.MazePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Player class is something of a view pointer. It knows what room it's in, and can
 * be sent to other rooms.
 */
public class Player extends JPanel implements MazePlayer {
   
   private MazeRoom myRoom;
   
   public Player(final MazeRoom theStartingRoom) {
      sendToRoom(theStartingRoom);
   }
   
   public void sendToRoom(final MazeRoom theNewRoom) {
      if (Objects.isNull(theNewRoom)) {
         throw new IllegalArgumentException("Null room object passed to player.");
      }
      myRoom = theNewRoom;
      myRoom.discover(this);
   }
   
   @Override
   public MazeRoom getCurrentRoom() {
      return myRoom;
   }
   
   @Override
   public Point getLocation() {
      return myRoom.getLocation();
   }
}
