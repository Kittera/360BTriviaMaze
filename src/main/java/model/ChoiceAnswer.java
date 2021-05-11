package model;

public class ChoiceAnswer implements Answer {
   
   private final String myText;
   private final char myLetter;
   
   public ChoiceAnswer(final String theText, final char theLetter) {
      myText = theText;
      myLetter = theLetter;
   }
   
   @Override
   public String get() {
      return myText;
   }
   
   @Override
   public boolean equals(Answer other) {
      return other.get().equals(this.get());
   }
}
