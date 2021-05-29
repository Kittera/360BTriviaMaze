package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {
   
   @Test
   void get() {
      Answer test = new Answer("test");
      assertEquals("test", test.get());
   }
   
   @Test
   void equals() {
      Answer test1 = new Answer("test1");
      Answer test2 = new Answer("test2");
      Answer test3 = new Answer("test1");
      
      assertTrue(
            test1.equals(test3),
            "Equality is based on answer text, should be true here"
      );
      assertFalse(
            test2.equals(test3),
            "Equality is based on answer text, should be false here"
            );
   }
}
