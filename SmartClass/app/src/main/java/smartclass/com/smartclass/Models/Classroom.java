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
    private String teacher;
    private ArrayList students;
    private ArrayList quizHistory;

    /**
     * Constructor
     * @param title Classroom title
     * @param description Classroom description
     * @param courseCode Classroom Course code
     * @param year Year (eg: 4)
     * @param level Classroom Level (academic, gifted, special)
     * @param teacher Classroom teacher (will contain an ID that can be expanded to the teacher model)
     * @param students Array of students (Array of student IDs that can be expanded to the student model)
     * @param quizHistory Array of quizzes (Array of quiz IDs that can be expanded to the quiz model)
     */
    public Classroom(String title, String description, String courseCode, int year, String level,
                     String teacher, ArrayList students, ArrayList quizHistory) {
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
    public String getTeacher() { return this.teacher; }
    public ArrayList getStudents() { return this.students; }
    public ArrayList getQuizHistory() { return this.quizHistory; }
    public int getStudentsCount() { return this.students.size(); }
    public int getQuizHistoryCount() { return this.quizHistory.size(); }
}
