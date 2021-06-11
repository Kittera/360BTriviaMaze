package model;

public class MockDoor implements MazeDoor {
   
   private final Question myTestQuestion;
   
   public MockDoor(final Question testQuestion) {
      myTestQuestion = testQuestion;
   }
   
   @Override
   public Question getQuestion() {
      return myTestQuestion;
   }
   
   @Override
   public boolean isLocked() {
      return false;
   }
   
   @Override
   public boolean isJammed() {
      return false;
   }
   
   @Override
   public MazeRoom roomBehind() {
      return null;
   }
   
   @Override
   public boolean tryAnswer(Answer theAnswer) { return false;}
   
   @Override
   public void linkOtherSide(MazeDoor theDoor) {
   
   }
   @Override
   public void setJammed(boolean theBoolean){

   }

   @Override
   public void twinUnlocked(MazeDoor theCaller) {
   
   }
   
   @Override
   public void twinJammed(MazeDoor theCaller) {
   
   }
}
