package model;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Optional;

public class TriviaRoom implements MazeRoom {
   
   private final EnumMap<Direction, TriviaDoor> myDoors;
   
   public TriviaRoom(EnumMap<Direction, TriviaDoor> myDoors) {
      this.myDoors = myDoors;
   }
   
   @Override
   public Collection<? extends MazeDoor> getDoors() {
      return myDoors.values();
   }
   
   @Override
   public Optional<MazeDoor> getDoor(Direction theDirection) {
      return Optional.empty(); //TODO
   }
   
   @Override
   public boolean addDoor(MazeDoor theDoor) {
      return false; //TODO
   }
}
