package model;

import java.util.*;

public class MockRoom implements MazeRoom {
   
   MockRoom() {  }
   
   @Override
   public Collection<MazeDoor> getDoors() {
      return new ArrayList<>();
   }
   
   @Override
   public Optional<MazeDoor> getDoor(Direction theDirection) {
      return Optional.empty();
   }
   
   @Override
   public boolean addDoor(final MazeDoor theDoor) {
      return false;
   }
}
