package model;

public interface Question {
   boolean tryAnswer(Answer theAns);
   QuestionType getType();
}
