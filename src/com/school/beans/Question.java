package com.school.beans;

public class Question {

    private int id;
    private String problem;
    private String possibility_1;
    private String possibility_2;
    private String possibility_3;
    private String possibility_4;
    private String correct_answer;
    private int quiz_id;

    public Question() {
    }

    public Question(int id, String problem, String possibility_1, String possibility_2, String possibility_3, String possibility_4, String correct_answer, int quiz_id) {
        this.id = id;
        this.problem = problem;
        this.possibility_1 = possibility_1;
        this.possibility_2 = possibility_2;
        this.possibility_3 = possibility_3;
        this.possibility_4 = possibility_4;
        this.correct_answer = correct_answer;
        this.quiz_id = quiz_id;
    }

    public Question(String problem, String possibility_1, String possibility_2, String possibility_3, String possibility_4, String correct_answer, int quiz_id) {
        this.problem = problem;
        this.possibility_1 = possibility_1;
        this.possibility_2 = possibility_2;
        this.possibility_3 = possibility_3;
        this.possibility_4 = possibility_4;
        this.correct_answer = correct_answer;
        this.quiz_id = quiz_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getPossibility_1() {
        return possibility_1;
    }

    public void setPossibility_1(String possibility_1) {
        this.possibility_1 = possibility_1;
    }

    public String getPossibility_2() {
        return possibility_2;
    }

    public void setPossibility_2(String possibility_2) {
        this.possibility_2 = possibility_2;
    }

    public String getPossibility_3() {
        return possibility_3;
    }

    public void setPossibility_3(String possibility_3) {
        this.possibility_3 = possibility_3;
    }

    public String getPossibility_4() {
        return possibility_4;
    }

    public void setPossibility_4(String possibility_4) {
        this.possibility_4 = possibility_4;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }
}
