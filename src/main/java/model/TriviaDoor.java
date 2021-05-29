package model;

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
      if (myQuestion.getAttemptCount() >= MAX_ATTEMPTS) {
         this.isJammed = true;
      } else if (correct) {
         this.isLocked = false;
      }
      return correct;
   }
}
