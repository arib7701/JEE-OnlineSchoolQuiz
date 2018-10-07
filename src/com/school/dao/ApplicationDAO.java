package com.school.dao;

import com.school.beans.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {


    // GET FUNCTIONS
    public List<Question> getQuestionsQuizRandom(int quizId){

        System.out.println("into getQuestionsQuiz");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM questions WHERE question_quiz_id = ? ORDER BY RAND();";
        PreparedStatement preparedStatement;
        ResultSet result;

        Question question = new Question();
        List<Question> questions = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                question = new Question();
                question.setId(result.getInt("question_id"));
                question.setProblem(result.getString("question_problem"));
                question.setPossibility_1(result.getString("question_pos1"));
                question.setPossibility_2(result.getString("question_pos2"));
                question.setPossibility_3(result.getString("question_pos3"));
                question.setPossibility_4(result.getString("question_pos4"));
                question.setCorrect_answer(result.getString("question_answer"));
                question.setTheme(result.getString("question_theme"));
                question.setQuiz_id(quizId);

                // add to list
                questions.add(question);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return questions;
    }

    public List<Question> getQuestionsQuiz(int quizId){

        System.out.println("into getQuestionsQuiz");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM questions WHERE question_quiz_id = ?;";
        PreparedStatement preparedStatement;
        ResultSet result;

        Question question = new Question();
        List<Question> questions = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                question = new Question();
                question.setId(result.getInt("question_id"));
                question.setProblem(result.getString("question_problem"));
                question.setPossibility_1(result.getString("question_pos1"));
                question.setPossibility_2(result.getString("question_pos2"));
                question.setPossibility_3(result.getString("question_pos3"));
                question.setPossibility_4(result.getString("question_pos4"));
                question.setCorrect_answer(result.getString("question_answer"));
                question.setTheme(result.getString("question_theme"));
                question.setQuiz_id(quizId);

                // add to list
                questions.add(question);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return questions;
    }

    public String getAnswerOfQuestion(int questionId){

        System.out.println("into getAnswerOfQuestion");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT question_answer FROM questions WHERE question_id = ?;";
        PreparedStatement preparedStatement;
        ResultSet result;

        String answer = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, questionId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                answer = result.getString("question_answer");
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return answer;
    }

    public List<Quiz> getQuizByTheme(String theme){

        System.out.println("into getQuizByTheme");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM quiz WHERE quiz_theme = ?;";
        PreparedStatement preparedStatement;
        ResultSet result;

        Quiz quiz;
        List<Quiz> quizzes = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, theme);
            result = preparedStatement.executeQuery();

            while(result.next()){
                quiz = new Quiz();
                quiz.setId(result.getInt("quiz_id"));
                quiz.setTheme(result.getString("quiz_theme"));
                quiz.setNber_questions(result.getInt("quiz_nber_questions"));
                quiz.setTeacher_id(result.getInt("quiz_teacher_id"));

                // add to list
                quizzes.add(quiz);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return quizzes;
    }

    public List<String> getThemes() {

        System.out.println("into getTheme");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT DISTINCT quiz_theme FROM quiz WHERE quiz_theme <> 'unassigned' AND quiz_theme<> 'entry';";
        PreparedStatement preparedStatement;
        ResultSet result;

        String theme;
        List<String> themes = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while(result.next()){
                theme = result.getString(1);
                // add to list
                themes.add(theme);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return themes;
    }

    public List<Grade> getGradeByIntern(int intern_id){

        System.out.println("into getGradeByIntern");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM grade WHERE grade_intern_id = ?";
        PreparedStatement preparedStatement;
        ResultSet result;
        List<Grade> grades = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, intern_id);
            result = preparedStatement.executeQuery();

            while(result.next()){
                Grade grade = new Grade();
                grade.setGrade_id(result.getInt("grade_id"));
                grade.setIntern_id(result.getInt("grade_intern_id"));
                grade.setQuiz_id(result.getInt("grade_quiz_id"));
                grade.setGrade_value(result.getDouble("grade_value"));
                grade.setGrade_date(result.getDate("grade_date"));

                // add to list
                grades.add(grade);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return grades;
    }

    public List<Grade> getGradeByQuizId(int quizId){

        System.out.println("into getGradeByQuizId");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM grade WHERE grade_quiz_id = ?";
        PreparedStatement preparedStatement;
        ResultSet result;
        List<Grade> grades = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                Grade grade = new Grade();
                grade.setGrade_id(result.getInt("grade_id"));
                grade.setIntern_id(result.getInt("grade_intern_id"));
                grade.setQuiz_id(result.getInt("grade_quiz_id"));
                grade.setGrade_value(result.getDouble("grade_value"));
                grade.setGrade_date(result.getDate("grade_date"));

                // add to list
                grades.add(grade);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return grades;
    }

    public List<User> getAllInterns(){
        System.out.println("into getAllInterns");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM intern;";
        PreparedStatement preparedStatement;
        ResultSet result;

        User user;
        List<User> users = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while(result.next()){
                user = new User();
                user.setId(result.getInt("intern_id"));
                user.setFirstname(result.getString("intern_firstname"));
                user.setLastname(result.getString("intern_lastname"));
                user.setEmail(result.getString("intern_email"));
                user.setUsername(result.getString("intern_username"));
                user.setPassword(result.getString("intern_password"));

                // add to list
                users.add(user);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return users;
    }

    public List<User> getAllTeachers(){
        System.out.println("into getAllTeachers");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM teacher WHERE teacher_id <> 1;";
        PreparedStatement preparedStatement;
        ResultSet result;

        User user;
        List<User> users = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while(result.next()){
                user = new User();
                user.setId(result.getInt("teacher_id"));
                user.setFirstname(result.getString("teacher_firstname"));
                user.setLastname(result.getString("teacher_lastname"));
                user.setEmail(result.getString("teacher_email"));
                user.setUsername(result.getString("teacher_username"));
                user.setPassword(result.getString("teacher_password"));

                // add to list
                users.add(user);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return users;
    }

    public List<Quiz> getQuizByTeacherByTheme(int teacherId, String theme){

        System.out.println("into getQuizByTeacherByTheme");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM quiz WHERE quiz_teacher_id = ? AND quiz_theme = ?;";
        PreparedStatement preparedStatement;
        ResultSet result;

        Quiz quiz;
        List<Quiz> quizzes = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);
            preparedStatement.setString(2, theme);
            result = preparedStatement.executeQuery();

            while(result.next()){
                quiz = new Quiz();
                quiz.setId(result.getInt("quiz_id"));
                quiz.setTheme(result.getString("quiz_theme"));
                quiz.setNber_questions(result.getInt("quiz_nber_questions"));
                quiz.setTeacher_id(result.getInt("quiz_teacher_id"));
                System.out.println("quiz theme and teacher " + quiz.getTheme() + " " + quiz.getTeacher_id());

                // add to list
                quizzes.add(quiz);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return quizzes;
    }

    public String getThemeByGradeId(int gradeId){

        System.out.println("into getThemeByGradeId");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT quiz.quiz_theme FROM quiz" +
                " JOIN grade ON grade.grade_quiz_id = quiz.quiz_id" +
                " WHERE grade.grade_id = ?;";
        PreparedStatement preparedStatement;
        ResultSet result;

        String theme = new String();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, gradeId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                theme = result.getString("quiz_theme");
                System.out.println(theme);
            }


            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return theme;
    }

    public List<Quiz> getQuizByTeacher(int teacherId){

        System.out.println("into getQuizByTeacher");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM quiz WHERE quiz_teacher_id = ?  and quiz_theme <> 'unassigned' and quiz_theme <> 'entry';";
        PreparedStatement preparedStatement;
        ResultSet result;

        Quiz quiz;
        List<Quiz> quizzes = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                quiz = new Quiz();
                quiz.setId(result.getInt("quiz_id"));
                quiz.setTheme(result.getString("quiz_theme"));
                quiz.setNber_questions(result.getInt("quiz_nber_questions"));
                quiz.setTeacher_id(result.getInt("quiz_teacher_id"));
                System.out.println("quiz theme and teacher " + quiz.getTheme() + " " + quiz.getTeacher_id());

                // add to list
                quizzes.add(quiz);
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return quizzes;
    }

    public Double getAverageGradeByInternId(int internId){

        System.out.println("into getAverageGradeByInternId");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT avg(grade_value) as avg from grade " +
                "join intern on grade.grade_intern_id = intern.intern_id " +
                "where intern.intern_id = ? " +
                "group by intern.intern_id;";
        PreparedStatement preparedStatement;
        ResultSet result;
        Double average = 0.0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, internId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                average = result.getDouble("avg");
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return average;

    }

    public Double getAverageGradeByQuizId(int quizId){

        System.out.println("into getAverageGradeByQuizId");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT avg(grade_value) as avg from grade " +
                "join quiz on grade.grade_quiz_id = quiz.quiz_id " +
                "where quiz.quiz_id = ? " +
                "group by quiz.quiz_id;";
        PreparedStatement preparedStatement;
        ResultSet result;
        Double average = 0.0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                average = result.getDouble("avg");
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return average;

    }

    public int getCountByQuizId(int quizId){

        System.out.println("into getCountByQuizId");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT count(grade_quiz_id) as count FROM grade " +
                " WHERE grade_quiz_id = ? " +
                "GROUP BY grade_quiz_id;";
        PreparedStatement preparedStatement;
        ResultSet result;
        int count = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                count = result.getInt("count");
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return count;

    }

    public int getCountByTeacherId(int teacherId){

        System.out.println("into getCountByTeacherId");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT count(quiz_teacher_id) as count FROM quiz " +
                " WHERE quiz_teacher_id = ? " +
                "GROUP BY quiz_teacher_id;";
        PreparedStatement preparedStatement;
        ResultSet result;
        int count = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                count = result.getInt("count");
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return count;

    }




    // VERIFY FUNCTIONS
    public User logInUser(String status, String username){

        System.out.println("into logInUser");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = new String();
        PreparedStatement preparedStatement;
        ResultSet result;

        User user = new User();
        String table = new String();

        if(status.equals("IN")){
            table = "intern";
            query = "SELECT * FROM intern WHERE intern_username = ?;";
        } else if (status.equals("TE")){
            table = "teacher";
            query = "SELECT * FROM teacher WHERE teacher_username = ?;";
        } else if (status.equals("AD")){
            table = "admin";
            query = "SELECT * FROM admin WHERE admin_username = ?;";
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            result = preparedStatement.executeQuery();

            while(result.next()){
                user.setId(result.getInt(table+"_id"));
                user.setFirstname(result.getString(table+"_firstname"));
                user.setLastname(result.getString(table+"_lastname"));
                user.setEmail(result.getString(table+"_email"));
                user.setUsername(result.getString(table+"_username"));
                user.setPassword(result.getString(table+"_password"));
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return user;
    }




    // CREATE FUNCTIONS
    public int addNewUser(User user, String status){

        System.out.println("into addNewUser");

        String hashPassword = getMd5(user.getPassword());
        user.setPassword(hashPassword);

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = null;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        if(status.equals("IN")){
            query = "INSERT INTO intern (`intern_firstname`, `intern_lastname`, `intern_email`, `intern_username`, `intern_password`) VALUES (?,?,?,?,?);";
        } else if (status.equals("TE")){
            query = "INSERT INTO teacher (`teacher_firstname`, `teacher_lastname`, `teacher_email`, `teacher_username`, `teacher_password`) VALUES (?,?,?,?,?);";
        } else if (status.equals("AD")){
            query = "INSERT INTO admin (`admin_firstname`, `admin_lastname`, `admin_email`, `admin_username`, `admin_password`) VALUES (?,?,?,?,?);";
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;

    }

    public int addNewGrade(Grade grade){

        System.out.println("into addNewGrade");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        query = "INSERT INTO grade (`grade_quiz_id`, `grade_intern_id`, `grade_value`, `grade_date`) VALUES (?,?,?,?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, grade.getQuiz_id());
            preparedStatement.setInt(2, grade.getIntern_id());
            preparedStatement.setDouble(3, grade.getGrade_value());
            preparedStatement.setDate(4, grade.getGrade_date());
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;
    }

    public int addNewQuiz(Quiz quiz){

        System.out.println("into addNewQuiz");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;
        int quizId = 0;

        query = "INSERT INTO quiz (`quiz_theme`, `quiz_nber_questions`, `quiz_teacher_id`) VALUES (?,?,?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, quiz.getTheme());
            preparedStatement.setInt(2, quiz.getNber_questions());
            preparedStatement.setInt(3,quiz.getTeacher_id());
            rowAffected = preparedStatement.executeUpdate();

            if(rowAffected > 0){
                query = "SELECT quiz_id FROM quiz ORDER BY quiz_id DESC LIMIT 1;";
                preparedStatement = connection.prepareStatement(query);
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    quizId = result.getInt(1);
                }
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return quizId;
    }

    public int addNewQuestion(Question question){

        System.out.println("into addNewQuestion");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;
        int qId = 0;

        query = "INSERT INTO questions (`question_problem`, `question_pos1`, `question_pos2`, `question_pos3`, `question_pos4`, `question_answer`, `question_quiz_id`, `question_theme`) VALUES (?,?,?,?,?,?,?,?);";


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, question.getProblem());
            preparedStatement.setString(2, question.getPossibility_1());
            preparedStatement.setString(3, question.getPossibility_2());
            preparedStatement.setString(4, question.getPossibility_3());
            preparedStatement.setString(5, question.getPossibility_4());
            preparedStatement.setString(6, question.getCorrect_answer());
            preparedStatement.setInt(7, question.getQuiz_id());
            preparedStatement.setString(8, question.getTheme());
            rowAffected = preparedStatement.executeUpdate();


            // Get Id of new question back
            if(rowAffected > 0){
                query = "SELECT question_id FROM questions ORDER BY question_id DESC LIMIT 1;";
                preparedStatement = connection.prepareStatement(query);
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    qId = result.getInt(1);
                }
            }

            // Increment number of added Question in Quiz
            if(rowAffected > 0) {
                query = "UPDATE quiz SET `quiz_nber_questions` = quiz_nber_questions+1 WHERE quiz_id = ?;";
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, question.getQuiz_id());
                    rowAffected = preparedStatement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            connection.close();
        }
        catch (SQLException e) {
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return qId;
    }




    // UPDATE FUNCTIONS
    public int reassignQuestions(int questionId, int newQuizId, int oldQuizId){

        System.out.println("into reassignedQuestions");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        query = "UPDATE questions SET `question_quiz_id` = ? WHERE question_id = (?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newQuizId);
            preparedStatement.setInt(2, questionId);
            rowAffected = preparedStatement.executeUpdate();

            // Increment number of Question in New assigned Quiz
            if(rowAffected > 0){
                query = "UPDATE quiz SET `quiz_nber_questions` = quiz_nber_questions+1 WHERE quiz_id = ?;";
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, newQuizId);
                    rowAffected = preparedStatement.executeUpdate();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                // Decrement number of Question in Old Quiz
                query = "UPDATE quiz SET `quiz_nber_questions` = quiz_nber_questions-1 WHERE quiz_id = ?;";
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, oldQuizId);
                    rowAffected = preparedStatement.executeUpdate();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;
    }

    public int reassignGrade(int gradeId, int newQuizId){

        System.out.println("into reassignGrades");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        query = "UPDATE grade SET `grade_quiz_id` = ? WHERE grade_id = (?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newQuizId);
            preparedStatement.setInt(2, gradeId);
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;
    }

    public int reassignQuiz(int quizId, int teacherId){

        System.out.println("into reassignQuiz");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        query = "UPDATE quiz SET `quiz_teacher_id` = ? WHERE quiz_id = (?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, quizId);
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;
    }

    public int updateQuestionById(Question question){

        System.out.println("into updateQuestionById");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        query = "UPDATE questions " +
                "SET `question_problem` = ?" +
                ", `question_pos1` = ?" +
                ", `question_pos2` = ?" +
                ", `question_pos3` = ?" +
                ", `question_pos4` = ?" +
                ", `question_answer` = ?" +
                " WHERE question_id = (?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, question.getProblem());
            preparedStatement.setString(2, question.getPossibility_1());
            preparedStatement.setString(3, question.getPossibility_2());
            preparedStatement.setString(4, question.getPossibility_3());
            preparedStatement.setString(5, question.getPossibility_4());
            preparedStatement.setString(6, question.getCorrect_answer());
            preparedStatement.setInt(7, question.getId());
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;
    }

    public int updateUserById(User user, String status){

        System.out.println("into updateUserById");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = null;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        if(status.equals("IN")){
            query = "UPDATE intern " +
                    "SET `intern_firstname` = ?" +
                    ", `intern_lastname` = ?" +
                    ", `intern_email` = ?" +
                    ", `intern_username` = ?" +
                    " WHERE intern_id = (?);";
        } else if (status.equals("TE")){
            query = "UPDATE teacher " +
                    "SET `teacher_firstname` = ?" +
                    ", `teacher_lastname` = ?" +
                    ", `teacher_email` = ?" +
                    ", `teacher_username` = ?" +
                    " WHERE teacher_id = (?);";
        } else if (status.equals("AD")){
            query = "UPDATE admin " +
                    "SET `admin_firstname` = ?" +
                    ", `admin_lastname` = ?" +
                    ", `admin_email` = ?" +
                    ", `admin_username` = ?" +
                    " WHERE admin_id = (?);";
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setInt(5, user.getId());
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;

    }




    // DELETE FUNCTIONS
    public int deleteQuizById(int quizId){

        // First Reassign Questions to Unassigned Pool
        List<Question> questions = getQuestionsQuiz(quizId);
        int reassign = 0;
        for(Question q : questions){
            reassign = reassignQuestions(q.getId(), 2, quizId);
            if(reassign < 1){
                System.out.println("Error reassigning question to pool - question: " + q.getId());
            }
        }

        //Second Reassign Grade to Unassigned Pool
        List<Grade> grades = getGradeByQuizId(quizId);
        reassign = 0;
        for(Grade g : grades){
            reassign = reassignGrade(g.getGrade_id(), 2);
            if(reassign < 1){
                System.out.println("Error reassigning grade to pool - grade: " + g.getGrade_id());
            }
        }

        System.out.println("into deleteQuizById");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query;
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        query = "DELETE FROM quiz WHERE (quiz_id = ?);";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizId);
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;
    }

    public int deleteUserById(int userId, String status){

        System.out.println("into deleteUserByID");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = new String();
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        if(status.equals("IN")){
            query = "DELETE FROM intern  WHERE (intern_id = ?);";
        } else if (status.equals("TE")){

            // Reassign Quiz to Pool
            List<Quiz> quizzes = getQuizByTeacher(userId);
            int reassigned = 0;
            for(Quiz q : quizzes){
                reassigned = reassignQuiz(q.getId(), 1);
            }

            query = "DELETE FROM teacher  WHERE (teacher_id = ?);";
        } else if (status.equals("AD")){
            query = "DELETE FROM admin  WHERE (admin_id = ?);";
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            rowAffected = preparedStatement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;

    }

    public int deleteQuestionById(int questionId, int quizId){

        System.out.println("into deleteQuestionById");

        // Set UP DB Query
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = new String();
        PreparedStatement preparedStatement;
        int rowAffected = 0;

        // Decrement number of Question in Quiz
        query = "UPDATE quiz " +
                "JOIN questions on questions.question_quiz_id = quiz.quiz_id " +
                "SET `quiz_nber_questions` = quiz_nber_questions-1 " +
                "WHERE question_quiz_id = ?;";

        try {
             preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1, quizId);
             rowAffected = preparedStatement.executeUpdate();

            // Then Delete question
            if(rowAffected > 0){
                query = "DELETE FROM questions  WHERE (question_id = ?);";
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, questionId);
                    rowAffected = preparedStatement.executeUpdate();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            connection.close();
        }
        catch (SQLException e){
            System.out.println("SQL Exception");
            e.printStackTrace();
        }

        return rowAffected;
    }




    // STATIC FUNCTIONS
    public static String getMd5(String password)
    {
        System.out.println("in getMd5");
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(password.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
