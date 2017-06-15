package smartclass.com.smartclass.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class StudentGoalHistory {
    private String title;
    private boolean completed;
    private boolean active;
    private Date completionDate;
    private Date startDate;
    private double mark;
    private Classroom classroom;
    private Goal goal;
    private ArrayList<StudentActivityLog> activityLog;

    /**
     * Constructor
     * @param title Goal title
     * @param completed Boolean indicating completion of goal
     * @param active Boolean indicating if the goal is currently active
     * @param completionDate Goal completion date
     * @param startDate Goal start date
     * @param mark Goal mark
     * @param classroom Classroom the goal was obtained in
     * @param goal The goal details
     * @param activityLog The goal activity log indicating where the mark was obtained from
     */
    public StudentGoalHistory(String title, boolean completed, boolean active, Date completionDate,
                       Date startDate, double mark, Classroom classroom, Goal goal,
                       ArrayList<StudentActivityLog> activityLog) {
        this.title = title;
        this.completed = completed;
        this.active = active;
        this.completionDate = completionDate;
        this.startDate = startDate;
        this.classroom = classroom;
        this.goal = goal;
        this.activityLog = activityLog;
    }

    public String getTitle() { return this.title; }
    public boolean isCompleted() { return this.completed; }
    public boolean isActive() { return this.active; }
    public Date getCompletionDate() { return this.completionDate; }
    public Date getStartDate() { return this.startDate; }
    public Classroom getClassroom() { return this.classroom; }
    public Goal getGoal() { return this.goal; }
    public ArrayList<StudentActivityLog> getActivityLog() { return this.activityLog; }
    public int getActivityLogCount() { return this.activityLog.size(); }
}
