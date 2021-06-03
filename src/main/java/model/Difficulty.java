package model;

import java.util.*;

public enum Difficulty {
   
   ANY("Any"),
   EASY("Easy"),
   MEDIUM("Medium"),
   HARD("Hard");
   
   /**
    * Human-friendly version of the string representing this Difficulty.
    */
   public final String difficulty;
   
   /**
    * Difficulty constructor.
    * @param theDifficulty human-friendly string to use for output
    */
   Difficulty(final String theDifficulty) {
      difficulty = theDifficulty;
   }
   
   /**
    * Given a name, finds an appropriate difficulty constant.
    * @param theName name of desired difficulty
    * @return desired difficulty constant if found, else Difficulty.ANY
    */
   public static Difficulty fromName(final String theName) {
      return switch (theName.toLowerCase(Locale.ROOT)) {
         case "easy" -> EASY;
         case "medium" -> MEDIUM;
         case "hard" -> HARD;
         default -> ANY;
      };
   }
   
   /**
    * Picks a random Difficulty.
    * @return a random Difficulty
    */
   public static Difficulty random() {
      List<Difficulty> hat = new ArrayList<>(Arrays.asList(values()));
      hat.remove(Difficulty.ANY);
      Collections.shuffle(hat);
      return hat.remove(0);
   }
}
