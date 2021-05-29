package model;

/**
 * Specifies the contract for interacting with a Door.
 */
public interface MazeDoor {
   /**
    * Accessor for the Question a door is holding onto.
    * @return stored Question
    */
   Question getQuestion();
   
   /**
    * Asks the door if it is still locked, but hasn't been jammed by incorrect answers.
    * @return door lock status
    */
   boolean isLocked();
   
   /**
    * Asks the door if it has been jammed by too many incorrect answers.
    * @return door jam status
    */
   boolean isJammed();
   
   /**
    * This method will return the room on the other side of this door or throw
    * IllegalStateException if the door is locked or jammed.
    * IllegalStateException can be caught, and its message displayed for the player.
    * @return Room behind this Door if this Door is unlocked
    * @throws IllegalStateException if door is not unlocked
    */
   MazeRoom roomBehind();
   
   /**
    * Forward the Answer attempt to the stored Question and update based on question
    * state.
    * @param theAnswer answer to try
    * @return whether the answer was correct
    */
   boolean tryAnswer(Answer theAnswer);
}
