package model;

import controller.MazePlayer;

import java.awt.*;
import java.util.*;

public class MockRoom implements MazeRoom {
   
   MockRoom() {  }
   
   @Override
   public Collection<MazeDoor> getDoors() {
      return new ArrayList<>();
   }
   
   @Override
   public Point getLocation() {
      return null;
   }
   
   @Override
   public boolean isVisited() {
      return false;
   }
   
   @Override
   public void markVisited() {
   
   }
   
   @Override
   public boolean discover(MazePlayer thePlayer) {
      return false;
   }
   
   @Override
   public Optional<MazeDoor> getDoor(Direction theDirection) {
      return Optional.empty();
   }
   
   @Override
   public boolean addDoor(final MazeDoor theDoor, final Direction theDirection) {
      return true;
   }
}
