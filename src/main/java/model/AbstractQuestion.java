package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Abstraction of a Question object where common attributes and behaviors are defined.
 *
 * @author Kittera Ashleigh McCloud
 * @version 2021.05.11.03.36
 */
public abstract class AbstractQuestion {
   
   /**
    * The type of a given instance of a question.
    */
   public final QuestionType myType;
   
   /**
    * The correct Answer.
    */
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
                              final Answer[] theChoices,
                              final QuestionType theType) {
      myPrompt = theQuestion;
      myAnswer = theChoices[0];
      myChoices = Arrays.stream(theChoices, 1, theChoices.length).
            collect(Collectors.toList());
      myType = theType;
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
    *
    * @return text of the question
    */
   public String getPrompt() {
      return myPrompt;
   }
   
   /**
    * Used by subclasses that override tryAnswer() behaviors.
    * @return the Answer stored in the correct answer field
    */
   protected Answer getCorrectAnswer() {
      return myAnswer;
   }
   
   /**
    * Determines if the correct answer has been chosen or not.
    *
    * @param theAnswer Answer that was chosen
    * @return whether the answer is correct
    */
   public boolean tryAnswer(Answer theAnswer) {
      return theAnswer == myAnswer;
   }
}
