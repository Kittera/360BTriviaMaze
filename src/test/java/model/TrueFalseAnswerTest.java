package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseAnswerTest {
   
   private Answer testAns1;
   private Answer testAns2;
   private Answer testAns3;
   
   @BeforeEach
   void setUp() {
      testAns1 = new TrueFalseAnswer(true);
      testAns2 = new TrueFalseAnswer(true);
      testAns3 = new TrueFalseAnswer(false);
   }
   
   @Test
   void get() {
      assertEquals(
            "True",
            testAns1.get(),
            "Testing get on a true, should be \"True\"");
      assertEquals(
            "True",
            testAns2.get(),
            "Testing get on a true, should be \"True\"");
      assertEquals(
            "False",
            testAns3.get(),
            "Testing get on a false, should be \"False\"");
   }
   
   @Test
   void equals() {
      assertTrue(
            testAns1.equals(testAns2),
            "If values from get() match, should be true"
      );
      assertFalse(
            testAns2.equals(testAns3),
            "If values from get() do NOT match, should be false"
      );
   }
}