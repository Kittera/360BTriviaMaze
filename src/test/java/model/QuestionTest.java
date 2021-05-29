package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
   
   private final String testPrompt =
         "What is the name of the thing that makes your code executable?";
   private Question testQuestion;
   private Answer ans1;
   private Answer ans2;
   private Answer ans3;
   private Answer ans4;
   
   @BeforeEach
   void setUp() {
      ans1 = new Answer("Compiler");
      ans2 = new Answer("Interpreter");
      ans3 = new Answer("Stereo");
      ans4 = new Answer("Linker");
      testQuestion = new Question(
            Category.GENERAL,
            QuestionType.MULTI_CHOICE,
            Difficulty.EASY,
            testPrompt,
            ans1,
            List.of(ans2, ans3, ans4)
      );
   }
   
   @Test
   void getAttemptCount() {
      testQuestion.tryAnswer(ans2);
      assertEquals(
            1,
            testQuestion.getAttemptCount(),
            "First attempt, counter should now be 1"
      );
      testQuestion.tryAnswer(ans1);
      assertEquals(
            2,
            testQuestion.getAttemptCount(),
            "Second attempt, also with correct answer, counter should now be 2"
      );
      testQuestion.tryAnswer(ans3);
      assertEquals(
            3,
            testQuestion.getAttemptCount(),
            "Third attempt, counter should now be 3"
      );
   }
   
   @Test
   void getIncorrectAnswers() {
      List<Answer> testAnswers = testQuestion.getIncorrectAnswers();
      assertNotNull(testAnswers);
      assertFalse(
            testAnswers.contains(testQuestion.getCorrectAnswer()),
            "incorrect answer list should not contain correct answer."
      );
      assertTrue(testAnswers.contains(ans2));
      assertTrue(testAnswers.contains(ans3));
      assertTrue(testAnswers.contains(ans4));
   }
   
   @Test
   void getCategory() {
      assertSame(testQuestion.getCategory(), Category.GENERAL);
   }
   
   @Test
   void getCorrectAnswer() {
      assertEquals(
            ans1,
            testQuestion.getCorrectAnswer(),
            "ans1 is the Answer that was given for the correct answer"
      );
   }
   
   @Test
   void getDifficulty() {
      assertSame(testQuestion.getDifficulty(), Difficulty.EASY);
   }
   
   @Test
   void getPrompt() {
      assertEquals(
            testPrompt,
            testQuestion.getPrompt(),
            "Makes sure no text alteration happens during construction"
      );
   }
   
   @Test
   void tryAnswer() {
      assertFalse(testQuestion.tryAnswer(ans2));
      assertFalse(testQuestion.tryAnswer(ans3));
      assertFalse(testQuestion.tryAnswer(ans4));
      assertFalse(testQuestion.hasCorrectAnswer());
      assertTrue(testQuestion.tryAnswer(ans1));
      assertTrue(testQuestion.hasCorrectAnswer());
   }
}
