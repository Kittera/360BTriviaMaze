package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTypeTest {
   
   @Test
   void testTrueFalseFromKey() {
      assertEquals(
            QuestionType.TRUE_FALSE,
            QuestionType.fromKey("boolean"),
            "Trying to get QuestionType.TRUE_FALSE"
      );
   }
   
   @Test
   void testMultiChoiceFromKey() {
      assertEquals(
            QuestionType.MULTI_CHOICE,
            QuestionType.fromKey("multiple"),
            "Trying to get QuestionType.MULTI_CHOICE"
      );
   }
   
   @Test
   void testShortAnswerFromKey() {
      assertEquals(
            QuestionType.SHORT_ANSWER,
            QuestionType.fromKey("short"),
            "Trying to get QuestionType.SHORT_ANSWER"
      );
   }
}
