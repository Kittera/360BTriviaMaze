package model;

/**
 * Represents an Answer to be used by
 */
public class Answer {
   
   /**
    * What type of question this Answer is associated with.
    */
   public final QuestionType type;
   
   /**
    * The Answer's content.
    */
   public final String myString;
   
   public Answer(final String content, final QuestionType format) {
      myString = content;
      type = format;
   }
   
   public Answer(final boolean trueFalse) {
      this(trueFalse ? "True" : "False", QuestionType.TRUE_FALSE);
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
