package smartclass.com.smartclass.Models;

import java.util.ArrayList;

/**
 * Created by max on 2017-06-15.
 */

public class Classroom {
    private String title;
    private String description;
    private String courseCode;
    private int year;
    private String level;
    private Teacher teacher;
    private ArrayList<Student> students;
    private ArrayList<Quiz> quizHistory;

    /**
     * Constructor
     * @param title Classroom title
     * @param description Classroom description
     * @param courseCode Classroom Course code
     * @param year Year (eg: 4)
     * @param level Classroom Level (academic, gifted, special)
     * @param teacher Classroom teacher
     * @param students Array of students
     * @param quizHistory Array of quizzes
     */
    public Classroom(String title, String description, String courseCode, int year, String level,
                     Teacher teacher, ArrayList<Student> students, ArrayList<Quiz> quizHistory) {
        this.title = title;
        this.description = description;
        this.courseCode = courseCode;
        this.year = year;
        this.level = level;
        this.teacher = teacher;
        this.students = students;
        this.quizHistory = quizHistory;
    }

    /* Getter Methods */

    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public String getCourseCode() { return this.courseCode; }
    public int getYear() {return this.year; }
    public String getLevel(){ return this.level; }
    public Teacher getTeacher() { return this.teacher; }
    public ArrayList<Student> getStudents() { return this.students; }
    public ArrayList<Quiz> getQuizHistory() { return this.quizHistory; }
    public int getStudentsCount() { return this.students.size(); }
    public int getQuizHistoryCount() { return this.quizHistory.size(); }
}
