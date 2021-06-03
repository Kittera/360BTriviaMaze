package model;

import controller.MazePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Container for up to four doors or walls. Can me added to Java Swing containers.
 *
 * @author Kittera Ashleigh McCloud
 */
public class TriviaRoom extends JPanel implements MazeRoom {
   private static final Color BG_COLOR = new Color(0x090909);
   
   //////////  SWING FIELDS  //////////
   
   private final JPanel myPlayerIndicator;
   
   private final EnumMap<Direction, MazeDoor> myDoors;
   
   private final int myRowLocation;
   private final int myColLocation;
   
   private boolean myVisited;
   private boolean myDiscovered;
   
   public TriviaRoom(
         final EnumMap<Direction, MazeDoor> theDoors,
         final int theRow,
         final int theCol
   ) {
      myDoors = theDoors;
      myRowLocation = theRow;
      myColLocation = theCol;
      myVisited = false;
      myDiscovered = false;
      myPlayerIndicator = new JPanel();
      initSwingGraphics();
   }
   
   public TriviaRoom(final int theRow, final int theCol) {
      this(new EnumMap<>(Direction.class), theRow, theCol);
   }
   
   @Override
   public boolean addDoor(final MazeDoor theDoor, final Direction theDirection) {
      // putIfAbsent returns null if the given key wasn't already mapped to a door
      boolean result = Objects.isNull(myDoors.putIfAbsent(theDirection, theDoor));
      if (result) {
         addDoorIndicator(theDirection);
      }
      return result;
   }
   
   @Override
   public Optional<MazeDoor> getDoor(Direction theDirection) {
      return Optional.ofNullable(myDoors.get(theDirection));
   }
   
   @Override
   public Collection<? extends MazeDoor> getDoors() {
      return myDoors.values();
   }
   
   @Override
   public Point getLocation() {
      return new Point(myColLocation, myRowLocation);
   }
   
   @Override
   public boolean isVisited() {
      return myVisited;
   }
   
   @Override
   public void markVisited() {
      if (!myVisited) {
         myVisited = true;
      }
   }
   
   public boolean discover(MazePlayer thePlayer) {
      myDiscovered = thePlayer.getCurrentRoom() == this;
      myPlayerIndicator.setVisible(true);
      return myDiscovered;
   }
   
   //////////////////  SWING CODE  //////////////////
   
   private void initSwingGraphics() {
      setLayout(new BorderLayout());
      setBackground(BG_COLOR);
      setPreferredSize(new Dimension(30, 30));
   
      initialBorder(BorderLayout.NORTH);
      initialBorder(BorderLayout.EAST);
      initialBorder(BorderLayout.SOUTH);
      initialBorder(BorderLayout.WEST);
   
      myPlayerIndicator.setBackground(Color.MAGENTA);
      myPlayerIndicator.setVisible(false);
      add(myPlayerIndicator, BorderLayout.CENTER);
      //TODO blackout based on discovered status
   }
   
   private void initialBorder(final String theDirection) {
      JPanel wallBorder = new JPanel();
      wallBorder.setPreferredSize(new Dimension(5, 5));
      wallBorder.setBackground(Color.BLUE);
      add(wallBorder, theDirection);
   }
   
   private void addDoorIndicator(final Direction theDirection) {
      JPanel doorIndicator = new JPanel();
      doorIndicator.setBackground(BG_COLOR);
      doorIndicator.setPreferredSize(new Dimension(5, 5));
      switch (theDirection) {
         case NORTH -> add(doorIndicator, BorderLayout.NORTH);
         case EAST  -> add(doorIndicator, BorderLayout.EAST);
         case SOUTH -> add(doorIndicator, BorderLayout.SOUTH);
         case WEST  -> add(doorIndicator, BorderLayout.WEST);
         case CENTER ->
               System.out.printf(
                     "Detected Direction.CENTER in addDoorIndicator()" +
                           "cell: %d, %d%n",
                     getLocation().x,
                     getLocation().y
               );
      }
   }
   
   //leave() player calls this to set the player location indicator invisible
}
