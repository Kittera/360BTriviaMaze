package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
   
   @Test
   void testCategoryFromFullName() {
      assertEquals(
            Category.ANY,
            Category.fromName("Nope"),
            "Should return the all-categories constant, ANY."
      );
      assertEquals(
            Category.MATH,
            Category.fromName("Science: Mathematics"),
            "Should return the math category constant, MATH."
      );
      assertEquals(
            Category.MATH.categoryID,
            Category.fromName("Science: Mathematics").categoryID,
            "Should return the math category constant, MATH."
      );
      
      
   }
}
