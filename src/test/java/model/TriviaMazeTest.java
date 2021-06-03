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
      fail();
   }
   
   @Test
   void getRoom() {
      fail();
   }
   
   @Test
   void movePlayer() {
      fail();
   }
}
