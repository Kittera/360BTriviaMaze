package model;

import java.util.Objects;
import java.util.Optional;

public class TriviaDoor implements MazeDoor {
   
   /**
    * Max number of attempts at answering a question before this door jams.
    */
   public static final int MAX_ATTEMPTS = 3;
   
   /**
    * Becomes true when MAX_ATTEMPTS is exceeded.
    */
   private boolean isJammed;
   
   /**
    * Default True, becomes false if internal question is answered correctly.
    */
   private boolean isLocked;
   
   /**
    * Reference to the Room behind this Door.
    */
   private final MazeRoom roomBehindMe;
   
   /**
    * Question that must be answered to unlock this door.
    */
   private final Question myQuestion;
   
   /**
    * Serves the purpose of linking Door objects so that when the door one one side
    * of a wall unlocks, so does the corresponding door in the other room. This ensures
    * that the player can come back through the door they just answered.
    */
   private MazeDoor myOtherSide;
   
   /**
    * Builds a Door with a question and a connection to a room.
    *
    * @param theQuestion   question that must be answered to unlock this Door
    * @param theRoomBehind Room this door leads to
    */
   public TriviaDoor(final Question theQuestion, final MazeRoom theRoomBehind) {
      myQuestion = theQuestion;
      roomBehindMe = theRoomBehind;
      isJammed = false;
      isLocked = true;
      myOtherSide = null;
   }
   
   @Override
   public Question getQuestion() {
      return myQuestion;
   }
   
   @Override
   public boolean isLocked() {
      return this.isLocked;
   }
   
   @Override
   public boolean isJammed() {
      return this.isJammed;
   }
   
   @Override
   public MazeRoom roomBehind() {
      return roomBehindMe;
   }


   
   @Override
   public boolean tryAnswer(Answer theAnswer) {
      boolean correct = myQuestion.tryAnswer(theAnswer);
      if (!correct && myQuestion.getAttemptCount() > MAX_ATTEMPTS && this.isLocked) {
         this.isJammed = true;
         Optional.ofNullable(myOtherSide)
               .ifPresent(otherSide -> otherSide.twinJammed(this));
      } else if (correct) {
         this.isLocked = false;
         Optional.ofNullable(myOtherSide)
               .ifPresent(otherSide -> otherSide.twinUnlocked(this));
      }
      return correct;
   }
   
   @Override
   public void linkOtherSide(MazeDoor theDoor) {
      if (Objects.isNull(myOtherSide)){
         myOtherSide = theDoor;
      }
   }
   
   public void twinUnlocked(final MazeDoor theCaller) {
      Optional.ofNullable(myOtherSide)
            .ifPresent(door -> this.isLocked = !(door == theCaller));
   }
   
   public void twinJammed(final MazeDoor theCaller) {
      Optional.ofNullable(myOtherSide)
            .ifPresent(door -> this.isJammed = (door == theCaller));
   }
}
