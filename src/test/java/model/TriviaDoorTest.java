package model;

import controller.QuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriviaDoorTest {
   
   private MazeDoor testDoor;
   private MazeDoor otherDoor;
   private MazeRoom mockRoom;
   private Question testQuestion;
   
   @BeforeEach
   void setUp() {
      testQuestion = QuestionFactory.get();
      mockRoom = new MockRoom();
      testDoor = new TriviaDoor(testQuestion, mockRoom);
      otherDoor = new TriviaDoor(QuestionFactory.get(), new MockRoom());
      testDoor.linkOtherSide(otherDoor);
   }
   
   @Test
   void getQuestion() {
      assertSame(testQuestion, testDoor.getQuestion());
      assertSame(
            testQuestion.getCategory(),
            testDoor.getQuestion().getCategory()
      );
   }
   
   @Test
   void isLocked() {
      assertTrue(testDoor.isLocked(), "Before trying correct answer");
      testDoor.tryAnswer(testQuestion.getCorrectAnswer());
      assertFalse(testDoor.isLocked(), "After trying correct answer");
   }
   
   @Test
   void isJammed() {
      assertFalse(testDoor.isJammed(), "After door construction");
      assertFalse(testDoor.tryAnswer(
            new Answer("jdkfgetrjyhdfaeuhyjrtjrytjsehrhe;loai"))
      );
      assertFalse(testDoor.tryAnswer(
            new Answer("iulwehgrfvjrtjsyrjrjtrjsryjrysdbggehgtrhrthrhrsthhkj "))
      );
      assertFalse(testDoor.tryAnswer(
            new Answer("35y08ah egrtuibjrjhrsjrsjysryjsryjsaae3rwt6q324rwyjv "))
      );
      assertFalse(testDoor.tryAnswer(
            new Answer("24qy35huwsdgreeghj56yu46yg4srthsrthrtyjjet"))
      );
      assertFalse(testDoor.tryAnswer(
            new Answer("ykutjyrhuj56yrhnsgaersw5wyhttrjrtjrsjtrthy53"))
      );
      assertTrue(testDoor.isJammed(), "After trying a bunch of bad answers");
   }
   
   @Test
   void roomBehind() {
      assertSame(mockRoom, testDoor.roomBehind());
   }
   
   @Test
   void linkOtherSide() {
      testDoor.tryAnswer(testQuestion.getCorrectAnswer());
      assertTrue(otherDoor.isLocked());
      otherDoor.linkOtherSide(testDoor);
      testDoor.tryAnswer(testQuestion.getCorrectAnswer());
      assertFalse(otherDoor.isLocked());
   }
}
