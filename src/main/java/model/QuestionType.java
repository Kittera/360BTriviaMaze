package model;

import java.util.Locale;

public enum QuestionType {
   TRUE_FALSE, MULTI_CHOICE, SHORT_ANSWER;
   
   public static QuestionType fromKey(final String theKey) {
      return switch (theKey.toLowerCase(Locale.ROOT)) {
         case "boolean" -> TRUE_FALSE;
         case "multiple" -> MULTI_CHOICE;
         case "short" -> SHORT_ANSWER;
         default -> null;
      };
   }
}
