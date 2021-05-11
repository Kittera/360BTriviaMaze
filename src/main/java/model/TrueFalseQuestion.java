package model;

import java.util.List;

public class TrueFalseQuestion extends AbstractQuestion implements Question {
   
   private final Answer myAnswer;
   
   private TrueFalseQuestion(final String thePrompt,
                             final Answer... theChoices) {
      super(thePrompt, theChoices);
      myAnswer = theChoices[0];
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
   
   @Override
   public List<Answer> getChoices() {
      return super.myChoices;
   }
   
   @Override
   public boolean tryAnswer(Answer theAnswer) {
      return myAnswer.equals(theAnswer);
   }
   
   @Override
   public QuestionType getType() {
      return QuestionType.TRUE_FALSE;
   }
}
