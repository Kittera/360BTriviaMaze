package model;

public class TrueFalseQuestion extends AbstractQuestion {
   
   private TrueFalseQuestion(final String thePrompt,
                             final Answer... theChoices) {
      super(thePrompt, theChoices, QuestionType.TRUE_FALSE);
   }
   
   public TrueFalseQuestion(final String thePrompt, boolean theAnswer) {
      this(thePrompt, createAnswers(theAnswer));
   }
   
   private static Answer[] createAnswers(boolean theAnswer) {
      //return an array with index 0 being the correct answer, followed by all choices
      return new TrueFalseAnswer[]{
            new TrueFalseAnswer(theAnswer),
            new TrueFalseAnswer(true),
            new TrueFalseAnswer(false)
      };
   }
}
