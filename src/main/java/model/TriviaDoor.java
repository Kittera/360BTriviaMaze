package model;

import java.util.Observable;

public class TriviaDoor implements MazeDoor {
   
   /**
    * Max number of attempts at answering a question before this door jams.
    */
   public static final int MAX_ATTEMPTS = 3;
   
   /**
    * Becomes true when MAX_ATTEMPTS is exceeded.
    */
   private boolean amIJammed;
   
   /**
    * Default True, becomes false if internal question is answered correctly.
    */
   private boolean amILocked;
   
   /**
    * Reference to the Room behind this Door.
    */
   private final MazeRoom roomBehindMe;
   
   /**
    * Question that must be answered to unlock this door.
    */
   private final Question myQuestion;
   
   /**
    * Builds a Door with a question and a connection to a room.
    * @param theQuestion question that must be answered to unlock this Door
    * @param theRoom Room this door leads to
    */
   public TriviaDoor(final Question theQuestion, final MazeRoom theRoom) {
      myQuestion = theQuestion;
      roomBehindMe = theRoom;
      amIJammed = false;
      amILocked = true;
   }
   
   
   @Override
   public Question getQuestion() {
      return myQuestion;
   }
   
   @Override
   public boolean isLocked() {
      return amILocked;
   }
   
   @Override
   public boolean isJammed() {
      return amIJammed;
   }
   
   @Override
   public MazeRoom roomBehind() {
      return roomBehindMe;
   }
   
   @Override
   public void update(Observable o, Object arg) {
      if (arg instanceof Question) {
         Question q = (Question) arg;
   
         if (q.getAttemptCount() > MAX_ATTEMPTS) {
            amIJammed = true;
         } else if (q.hasCorrectAnswer()) {
            amILocked = false;
         }
      }
   }
}
