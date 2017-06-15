package smartclass.com.smartclass.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Student extends User {
    private ArrayList<StudentQuizHistory> quizHistory;
    private ArrayList<StudentGoalHistory> goals;

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
    public Student(Facebook facebook, String firstName, String lastName, String displayName,
                   Date birthday, ArrayList<Classroom> classrooms,
                   ArrayList<StudentQuizHistory> quizHistory, ArrayList<StudentGoalHistory> goals) {
        super(facebook, firstName, lastName, displayName, birthday, classrooms, false);
        this.quizHistory = quizHistory;
        this.goals = goals;
    }

    public ArrayList<StudentQuizHistory> getQuizHistory() { return this.quizHistory; }
    public ArrayList<StudentGoalHistory> getGoals() { return this.goals; }
    public int getQuizHistoryCount() { return this.quizHistory.size(); }
    public int getGoalCount() { return this.goals.size(); }
}
