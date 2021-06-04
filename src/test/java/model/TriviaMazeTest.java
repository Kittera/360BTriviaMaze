package model;

import controller.MazePlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TriviaMazeTest {
   
   private Maze testMaze;
   private MazePlayer testPlayer;
   
   @BeforeEach
   void setUp() {
      testMaze = new TriviaMaze(4, 4);
      testPlayer = new Player(testMaze.getRoom(1, 1));
   }
   
   @Test
   void addPlayer() {
      testMaze.addPlayer(testPlayer);
      assertSame(
            testMaze.getRoom(1, 1),
            testPlayer.getCurrentRoom()
      );
   }
   
   @Test
   void getRoom() {
      assertSame(
            testMaze.getRoom(1, 1),
            testPlayer.getCurrentRoom()
      );
      assertThrows(
            TriviaMaze.MazeIndexOutOfBoundsError.class,
            () -> testMaze.getRoom(10000,10000)
      );
      assertThrows(
            TriviaMaze.MazeIndexOutOfBoundsError.class,
            () -> testMaze.getRoom(-1,-1)
      );
      assertThrows(
            TriviaMaze.MazeIndexOutOfBoundsError.class,
            () -> testMaze.getRoom(0,0)
      );
   }
   
   @Test
   void movePlayer() {
      assertThrows(
            IllegalStateException.class,
            () -> testMaze.movePlayer(Direction.SOUTH),
            "Should throw IllegalState when no player is present"
      );
      
      testMaze.addPlayer(testPlayer);
      assertFalse(
            testMaze.movePlayer(Direction.SOUTH) || testMaze.movePlayer(Direction.EAST),
            "Doors not yet attempted, shouldn't be able to go anywhere"
      );
      
      Optional<MazeDoor> door1 = testPlayer.getCurrentRoom().getDoor(Direction.EAST);
      Optional<MazeDoor> door2 = testPlayer.getCurrentRoom().getDoor(Direction.SOUTH);
      
      door1.ifPresent(door -> door.tryAnswer(door.getQuestion().getCorrectAnswer()));
      door2.ifPresent(door -> door.tryAnswer(door.getQuestion().getCorrectAnswer()));
      assertTrue(
            testMaze.movePlayer(Direction.SOUTH) || testMaze.movePlayer(Direction.EAST),
            "By now at least one of the doors should be unlocked"
      );
   }
}
