package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by peterpogorski on 2017-07-18.
 */

public class PresenceStudent {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("_id")
    private String studentId;

    @SerializedName("quizHistory")
    private ArrayList<StudentQuizHistory> quizHistory;

    @SerializedName("goals")
    private ArrayList<Goal> goals;

    @SerializedName("attendanceHistory")
    private ArrayList<String> attendanceHistory;

    private boolean assignGoal = false;

    /**
     * Constructor
     * @param facebook Facebook details
     * @param firstName Student's first name
     * @param lastName Students's last name
     * @param displayName Student's display name
     * @param birthday Student's birthday
     * @param classrooms Classrooms the student is a part of
     * @param quizHistory Student's quiz history
     * @param goals Student's goals
     */


    @SerializedName("classrooms")
    private ArrayList<String> classrooms;

    public ArrayList<String> getClassrooms() {
        return classrooms;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAssignGoal(boolean assignGoal) {
        this.assignGoal = assignGoal;
    }

    public boolean getAssignGoal() {
        return assignGoal;
    }

    public String getStudentId() {
        return studentId;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public ArrayList<StudentQuizHistory> getQuizHistory() {
        return quizHistory;
    }

    public int getCompletedQuizCount() {
        return quizHistory.size();
    }
}
