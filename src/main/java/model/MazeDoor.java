package model;

import java.io.Serializable;

/**
 * Specifies the contract for interacting with a Door.
 */
public interface MazeDoor extends Serializable {
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
   boolean tryAnswer(final Answer theAnswer);
   
   /**
    * This sets up the door on the other side of a wall so that when this one is unlocked,
    * the player can still go back through the way they came.
    * @param theDoor door that is to be on the other side of the wall from this one
    */
   void linkOtherSide(final MazeDoor theDoor);
   
   void twinUnlocked(final MazeDoor theCaller);
   
   void twinJammed(final MazeDoor theCaller);
}
