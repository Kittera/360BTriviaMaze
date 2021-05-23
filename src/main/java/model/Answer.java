package model;

/**
 * Represents an Answer to be used by
 */
public class Answer {
   /**
    * The Answer's content.
    */
   public final String myString;
   
   public Answer(final String content) {
      myString = content;
   }
   
   /**
    * Provides the String of the answer choice
    * @return answer choice as string
    */
   public String get() {
      return myString;
   }
   
   public boolean equals(Answer other) {
      return other.get().equals(get());
   }
}
