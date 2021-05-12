package model;

import java.util.List;

/**
 * Abstraction of a Question object where common attributes and behaviors are defined.
 * @author Kittera Ashleigh McCloud
 * @version 2021.05.11.03.36
 */
public abstract class AbstractQuestion implements Question {
   
   private final Answer myAnswer;
   /**
    * Stores all Answer objects associated with this question.
    */
   private final List<Answer> myChoices;
   
   /**
    * Stores the question represented by this Question.
    */
   private final String myPrompt;
   
   
   protected AbstractQuestion(final String theQuestion,
                              final Answer[] theChoices) {
      myPrompt = theQuestion;
      myAnswer = theChoices[0];
      myChoices = List.of(theChoices);
   }
   
   /**
    * Accessor for a list of possible answer choices.
    *
    * @return all answer choices contained within the question
    */
   public List<Answer> getChoices() {
      return myChoices;
   }
   
   /**
    * Gets the question wrapped in this Question
    * @return text of the question
    */
   public String getPrompt() {
      return myPrompt;
   }
   
   protected Answer getCorrectAnswer() {
      return myAnswer;
   }
   
   
   /**
    * Determines if the correct answer has been chosen or not.
    * @param theAnswer Answer that was chosen
    * @return whether the answer is correct
    */
   public abstract boolean tryAnswer(Answer theAnswer);
}
