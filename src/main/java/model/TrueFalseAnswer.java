package model;

public class TrueFalseAnswer implements Answer {
   
   private final boolean myTruth;
   
   public TrueFalseAnswer(final boolean theTruth) {
      myTruth = theTruth;
   }
   
   @Override
   public String get() {
      return myTruth ? "True" : "False";
   }
   
   @Override
   public boolean equals(Answer other) {
      return other.get().equals(get());
   }
}
