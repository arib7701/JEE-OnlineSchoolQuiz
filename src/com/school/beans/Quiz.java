package com.school.beans;

public class Quiz {

    private int id;
    private String theme;
    private int nber_questions;
    private int teacher_id;

    public Quiz() {
    }

    public Quiz(int id, String theme, int nber_questions, int teacher) {
        this.id = id;
        this.theme = theme;
        this.nber_questions = nber_questions;
        this.teacher_id = teacher;
    }

    public Quiz(String theme, int nber_questions, int teacher) {
        this.theme = theme;
        this.nber_questions = nber_questions;
        this.teacher_id = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getNber_questions() {
        return nber_questions;
    }

    public void setNber_questions(int nber_questions) {
        this.nber_questions = nber_questions;
    }

    public int getTeacher_id() { return teacher_id; }

    public void setTeacher_id(int teacher_id) { this.teacher_id = teacher_id; }
}
