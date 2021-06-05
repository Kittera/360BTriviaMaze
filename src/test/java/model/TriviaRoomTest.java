package model;

import controller.MazePlayer;
import controller.QuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriviaRoomTest {
   
   private MazeRoom testRoom;
   
   @BeforeEach
   void setUp() {
      testRoom = new TriviaRoom(1, 1);
   }
   
   @Test
   void addDoor() {
      assertTrue(
            testRoom.addDoor(new MockDoor(QuestionFactory.get()),Direction.EAST)
      );
      assertFalse(
            testRoom.addDoor(new MockDoor(QuestionFactory.get()),Direction.EAST)
      );
   }
   
   @Test
   void getDoor() {
      MazeDoor mockDoor = new MockDoor(QuestionFactory.get());
      testRoom.addDoor(mockDoor, Direction.EAST);
      assertSame(
            mockDoor,
            testRoom.getDoor(Direction.EAST).get()
      );
      assertTrue(testRoom.getDoor(Direction.SOUTH).isEmpty());
      assertFalse(testRoom.getDoor(Direction.EAST).isEmpty());
   }
   
   @Test
   void getLocation() {
      assertEquals(
            1,
            testRoom.getLocation().x
      );
      assertEquals(
            1,
            testRoom.getLocation().y
      );
      assertNotEquals(0, testRoom.getLocation().x);
      assertNotEquals(0, testRoom.getLocation().y);
   }
   
   @Test
   void isVisited() {
      assertFalse(testRoom.isVisited());
      testRoom.markVisited();
      assertTrue(testRoom.isVisited());
   }
   
//   @Test
//   void enter() {
//      MazePlayer testPlayer = new Player(testRoom);
//      assertFalse(testRoom.);
//   }
//
//   @Test
//   void leave() {
//      fail();
//   }
}
