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
      return new Answer[]{
            new Answer(theAnswer),
            new Answer(true),
            new Answer(false)
      };
   }
   
   @Override
   public boolean tryAnswer(Answer theAnswer) {
      
      return theAnswer.get().equals(getCorrectAnswer().get());
   }
}
