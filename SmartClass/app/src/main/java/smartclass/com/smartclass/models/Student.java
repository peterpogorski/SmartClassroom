package smartclass.com.smartclass.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Student extends User {
    private class QuizResponse {
        private int questionNumber;
        private String answer;

        /**
         * Constructor
         * @param questionNumber Question number
         * @param answer Student's answer
         */
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

        /**
         * Constructor
         * @param title Quiz title
         * @param mark Student's mark on the quiz
         * @param weight Weight of quiz
         * @param results Students answers
         * @param quiz Quiz details and questions
         */
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

        /**
         * Constructor
         * @param date Entry date
         * @param log Entry text
         * @param weight Weight obtained
         */
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

        /**
         * Constrcutor
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

    private ArrayList<QuizHistory> quizHistory;
    private ArrayList<GoalHistory> goals;

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
                   ArrayList<QuizHistory> quizHistory, ArrayList<GoalHistory> goals) {
        super(facebook, firstName, lastName, displayName, birthday, classrooms, false);
        this.quizHistory = quizHistory;
        this.goals = goals;
    }

    public ArrayList<QuizHistory> getQuizHistory() { return this.quizHistory; }
    public ArrayList<GoalHistory> getGoals() { return this.goals; }
    public int getQuizHistoryCount() { return this.quizHistory.size(); }
    public int getGoalCount() { return this.goals.size(); }
}
