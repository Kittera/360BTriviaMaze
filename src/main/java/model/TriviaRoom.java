package model;

import java.awt.*;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;

public class TriviaRoom implements MazeRoom {
   
   private final EnumMap<Direction, MazeDoor> myDoors;
   
   private final int myRowLocation;
   private final int myColLocation;
   
   private boolean myVisited;
   
   public TriviaRoom(
         final EnumMap<Direction, MazeDoor> theDoors,
         final int theRow,
         final int theCol
   ) {
      this.myDoors = theDoors;
      myRowLocation = theRow;
      myColLocation = theCol;
      myVisited = false;
   }
   
   public TriviaRoom(final int theRow, final int theCol) {
      this(new EnumMap<>(Direction.class), theRow, theCol);
   }
   
   @Override
   public boolean addDoor(final MazeDoor theDoor, final Direction theDirection) {
      return Objects.nonNull(myDoors.putIfAbsent(theDirection, theDoor));
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
      return new Point(myRowLocation, myColLocation);
   }
   
   @Override
   public boolean isVisited() {
      return myVisited;
   }
   
   @Override
   public void visit() {
      if (!myVisited) {
         myVisited = true;
      }
   }
}
