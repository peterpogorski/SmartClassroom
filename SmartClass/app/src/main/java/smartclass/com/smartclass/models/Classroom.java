package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by max on 2017-06-15.
 */

public class Classroom {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("courseCode")
    private String courseCode;

    @SerializedName("year")
    private int year;

    @SerializedName("level")
    private String level;

    @SerializedName("teacher")
    private String teacher;

    @SerializedName("students")
    private ArrayList<String> students;

    @SerializedName("quizHistory")
    private ArrayList<String> quizHistory;

    /* Getter Methods */

    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public String getCourseCode() { return this.courseCode; }
    public int getYear() {return this.year; }
    public String getLevel(){ return this.level; }
    public String getTeacher() { return this.teacher; }
    public ArrayList<String> getStudents() { return this.students; }
    public ArrayList<String> getQuizHistory() { return this.quizHistory; }
    public int getStudentsCount() { return this.students.size(); }
    public int getQuizHistoryCount() { return this.quizHistory.size(); }
}
