package model;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum Difficulty {
   
   EASY(0, "Easy"),
   MEDIUM(1, "Medium"),
   HARD(2, "Hard");
   
   /**
    *
    */
   public final int ordinal;
   
   /**
    *
    */
   public final String difficulty;
   
   
   Difficulty(final int theKey, final String theDiff) {
      ordinal = theKey;
      difficulty = theDiff;
   }
   
   public static Difficulty fromName(final String theName) {
      return switch (theName.toLowerCase(Locale.ROOT)) {
         case "easy" -> EASY;
         case "medium" -> MEDIUM;
         case "hard" -> HARD;
         default -> null;
      };
   }
}
