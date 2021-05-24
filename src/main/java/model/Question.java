package model;

import java.util.List;
import java.util.Observable;

/**
 * Abstraction of a Question object where common attributes and behaviors are defined.
 *
 * @author Kittera Ashleigh McCloud
 * @version 2021.05.11.03.36
 */
@SuppressWarnings("deprecation")
public class Question extends Observable {
   
   /**
    * Enumerated question category.
    */
   public final Category myCategory;
   
   /**
    * The type of a given instance of a question.
    */
   public final QuestionType myType;
   
   /**
    * Enumerated difficulty category.
    */
   public final Difficulty myDifficulty;
   
   /**
    * Stores the question represented by this Question.
    */
   private final String myPrompt;
   
   /**
    * The correct Answer.
    */
   private final Answer myCorrectAnswer;
   
   /**
    * Stores all Answer objects associated with this question.
    */
   private final List<Answer> myIncorrectAnswers;
   
   /**
    * Creates a Question container. By default, all parameters are required to create a
    * Question.
    * @param theCategory enumerated category constant
    * @param theType enumerated format constant
    * @param theDifficulty enumerated difficulty constant
    * @param theQuestion string of text, the question
    * @param theCorrectAnswer Answer containing the correct answer
    * @param theIncorrectAnswers List of incorrect answers
    */
   public Question(
         final Category theCategory,
         final QuestionType theType,
         final Difficulty theDifficulty,
         final String theQuestion,
         final Answer theCorrectAnswer,
         final List<Answer> theIncorrectAnswers) {
      myCategory = theCategory;
      myType = theType;
      myDifficulty = theDifficulty;
      myPrompt = theQuestion;
      myCorrectAnswer = theCorrectAnswer;
      myIncorrectAnswers = theIncorrectAnswers;
   }
   
   /**
    * Accessor for a list of possible answer choices.
    *
    * @return all answer choices contained within the question
    */
   public List<Answer> getIncorrectAnswers() {
      return myIncorrectAnswers;
   }
   
   /**
    * Accessor for enumerated category.
    * @return enumerated category constant
    */
   public Category getCategory() {
      return myCategory;
   }
   
   /**
    * Used by subclasses that override tryAnswer() behaviors.
    *
    * @return the Answer stored in the correct answer field
    */
   public Answer getCorrectAnswer() {
      return myCorrectAnswer;
   }
   
   /**
    * Accessor for enumerated difficulty category.
    * @return enumerated difficulty constant
    */
   public Difficulty getDifficulty() {
      return myDifficulty;
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
    * Determines if the correct answer has been chosen or not.
    *
    * @param theAnswer Answer that was chosen
    * @return whether the answer is correct
    */
   public boolean tryAnswer(Answer theAnswer) {
      return theAnswer == myCorrectAnswer;
   }
}
