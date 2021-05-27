package model;

import java.util.List;
import java.util.Observable;

/**
 * Question acts as a container for a question and some statistics about that question
 * needed for the context of the trivia maze game.
 *
 * @author Kittera Ashleigh McCloud
 * @version 2021.05.26.03.36
 */
@SuppressWarnings("deprecation")
public class Question extends Observable {
   
   /**
    * Number of times an attempt has been made to answer this question.
    */
   private int myAttemptCounter;
   
   /**
    * Whether this question has been answered correctly yet.
    */
   private boolean myAnswered;
   
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
   public final String myPrompt;
   
   /**
    * The correct Answer.
    */
   public final Answer myCorrectAnswer;
   
   /**
    * Stores all Answer objects associated with this question.
    */
   public final List<Answer> myIncorrectAnswers;
   
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
         final List<Answer> theIncorrectAnswers
   ) {
      myCategory = theCategory;
      myType = theType;
      myDifficulty = theDifficulty;
      myPrompt = theQuestion;
      myCorrectAnswer = theCorrectAnswer;
      myIncorrectAnswers = theIncorrectAnswers;
      myAttemptCounter = 0;
      myAnswered = false;
   }
   
   /**
    * Accessor for attempt counter.
    * @return number of attempts so far
    */
   public int getAttemptCount() {
      return myAttemptCounter;
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
    * Query for whether this question has been answered correctly
    * @return status of correct answer detection
    */
   public boolean hasCorrectAnswer() {
      return myAnswered;
   }
   
   /**
    * Determines if the correct answer has been chosen or not.
    *
    * @param theAnswer Answer that was chosen
    * @return whether the answer is correct
    */
   public boolean tryAnswer(Answer theAnswer) {
      myAttemptCounter++;
      notifyObservers(this);
      boolean result = theAnswer == myCorrectAnswer;
      if (result) {
         myAnswered = true;
      }
      return result;
   }
}
