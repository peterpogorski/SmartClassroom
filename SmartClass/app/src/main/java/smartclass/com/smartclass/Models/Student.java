package smartclass.com.smartclass.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Student {
    private class QuizResponse {
        private int questionNumber;
        private String answer;

        public QuizResponse(int questionNumber, String answer) {
            this.questionNumber = questionNumber;
            this.answer = answer;
        }

        public int getQuestionNumber() { return this.questionNumber; }
        public String getAnswer() { return this.answer; }
    }
    private class QuizHistory {
        private String title;
        private double mark;
        private double weight;
        private ArrayList<QuizResponse> results;
        private Quiz quiz;

        public QuizHistory(String title, double mark, double weight,
                           ArrayList<QuizResponse> results, Quiz quiz) {
            this.title = title;
            this.mark = mark;
            this.weight = weight;
            this.results = results;
            this.quiz = quiz;
        }

        public String getTitle() { return this.title; }
        public double getMark() { return this.mark; }
        public double getWeight() { return this.weight; }
        public ArrayList<QuizResponse> getResults() { return this.results; }
        public int getResultsCount() { return this.results.size(); }
        public Quiz getQuiz() { return this.quiz; }

    }
    private class ActivityLog {
        private Date date;
        private String log;
        private double weight;

        public ActivityLog(Date date, String log, double weight) {
            this.date = date;
            this.log = log;
            this.weight = weight;
        }

        public Date getDate() { return this.date; }
        public String getLog() { return this.log; }
        public double getWeight() { return this.weight; }
    }
    private class GoalHistory {
        private String title;
        private boolean completed;
        private boolean active;
        private Date completionDate;
        private Date startDate;
        private double mark;
        private Classroom classroom;
        private Goal goal;
        private ArrayList<ActivityLog> activityLog;

        public GoalHistory(String title, boolean completed, boolean active, Date completionDate,
                           Date startDate, double mark, Classroom classroom, Goal goal,
                           ArrayList<ActivityLog> activityLog) {
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
        public Classroom getClassroom() { return this.classroom; }
        public Goal getGoal() { return this.goal; }
        public ArrayList<ActivityLog> getActivityLog() { return this.activityLog; }
        public int getActivityLogCount() { return this.activityLog.size(); }
    }
    private Facebook facebook;
    private String firstName;
    private String lastName;
    private String displayName;
    private Date birthday;
    private ArrayList<Classroom> classrooms;
    private ArrayList<QuizHistory> quizHistory;
    private ArrayList<GoalHistory> goals;

    public Student(Facebook facebook, String firstName, String lastName, String displayName,
                   Date birthday, ArrayList<Classroom> classrooms,
                   ArrayList<QuizHistory> quizHistory, ArrayList<GoalHistory> goals) {
        this.facebook = facebook;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.birthday = birthday;
        this.classrooms = classrooms;
        this.quizHistory = quizHistory;
        this.goals = goals;
    }

    public Facebook getFacebook() { return this.facebook; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getDisplayName() { return this.displayName; }
    public Date getBirthday() { return this.birthday; }
    public ArrayList<Classroom> getClassrooms() {return this.classrooms; }
    public ArrayList<QuizHistory> getQuizHistory() { return this.quizHistory; }
    public ArrayList<GoalHistory> getGoals() { return this.goals; }
    public int getClassroomsCount() { return this.classrooms.size(); }
    public int getQuizHistoryCount() { return this.quizHistory.size(); }
    public int getGoalCount() { return this.goals.size(); }
}
