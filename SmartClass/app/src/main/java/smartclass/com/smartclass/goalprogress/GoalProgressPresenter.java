package smartclass.com.smartclass.goalprogress;

import smartclass.com.smartclass.models.Goal;
import smartclass.com.smartclass.models.Student;
import smartclass.com.smartclass.models.StudentQuizHistory;

/**
 * Created by peterpogorski on 2017-07-17.
 */

public class GoalProgressPresenter implements GoalProgressContract.Presenter {

    private GoalProgressContract.View mView;

    public GoalProgressPresenter(GoalProgressContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.getStudentModel();
    }

    @Override
    public void onStudentLoaded(Student student) {
        int goalCompleted = 0;
        int goalInProgress = 0;

        double averageGrade = 0;

        for(Goal goal: student.getGoals()) {
            if(goal.getCompleted()) {
                goalCompleted++;
            } else {
                goalInProgress++;
            }
        }

        for(StudentQuizHistory studentQuizHistory: student.getQuizHistory()) {
            averageGrade += studentQuizHistory.getMark();
        }

        averageGrade = averageGrade / student.getCompletedQuizCount();

        mView.showQuizzesCompleted(student.getCompletedQuizCount());
        mView.showAverageGrade(averageGrade);
        mView.populatePieChart(goalCompleted, goalInProgress);
    }
}
