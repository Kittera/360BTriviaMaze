package model;

/**
 * Represents an Answer to be used by
 */
public class Answer {
   
   private final String myString;
   
   public Answer(final String content) {
      myString = content;
   }
   
   public Answer(final boolean trueFalse) {
      this(trueFalse? "True" : "False");
   }
   
   /**
    * Provides the String of the answer choice
    * @return
    */
   public String get() {
      return myString;
   }
   
   public boolean equals(Answer other) {
      return other.get().equals(get());
   }
}
