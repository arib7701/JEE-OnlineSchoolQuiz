package com.school.beans;
import java.sql.Date;

public class Grade {

    private int grade_id;
    private int intern_id;
    private int quiz_id;
    private double grade_value;
    private Date grade_date;

    public Grade() {
    }

    public Grade(int intern_id, int quiz_id, double value, Date date) {
        this.intern_id = intern_id;
        this.quiz_id = quiz_id;
        this.grade_value = value;
        this.grade_date = date;
    }

    public Grade(int id, int intern_id, int quiz_id, Date date) {
        this.grade_id = id;
        this.intern_id = intern_id;
        this.quiz_id = quiz_id;
        this.grade_date = date;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getIntern_id() {
        return intern_id;
    }

    public void setIntern_id(int intern_id) {
        this.intern_id = intern_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public double getGrade_value() { return grade_value; }

    public void setGrade_value(double grade_value) { this.grade_value = grade_value; }

    public Date getGrade_date() { return grade_date; }

    public void setGrade_date(Date grade_date) { this.grade_date = grade_date; }
}
