package model;

import controller.MazePlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriviaMazeTest {
   
   private Maze testMaze;
   private MazePlayer testPlayer;
   
   @BeforeEach
   void setUp() {
      testMaze = new TriviaMaze(6, 6);
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
   }
   
   @Test
   void movePlayer() {
      assertThrows(
            IllegalStateException.class,
            () -> testMaze.movePlayer(Direction.SOUTH)
      );
      testMaze.addPlayer(testPlayer);
      assertTrue(
            testMaze.movePlayer(Direction.SOUTH) || testMaze.movePlayer(Direction.EAST)
      );
   }
}
