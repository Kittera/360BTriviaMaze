package model;

public interface Question {
   QuestionType getType();
   boolean tryAnswer(Answer theAns);
}
