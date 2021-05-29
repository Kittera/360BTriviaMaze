package model;

import java.util.*;

public class MockRoom implements MazeRoom {
   
//   private List<MazeDoor> testDoors;
   
   private Map<Direction, Optional<MazeDoor>> testDoors;
   
   MockRoom() {
      testDoors = new EnumMap<>(Direction.class);
   }
   
   
   @Override
   public List<MazeDoor> getDoors() {
      return null;
   }
   
   @Override
   public Optional<MazeDoor> getDoor(Direction theDirection) {
      return Optional.empty();
   }
   
   @Override
   public void update(Observable o, Object arg) {
   
   }
}
