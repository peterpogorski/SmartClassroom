package smartclass.com.smartclass.classroom.teacherGoals;

import smartclass.com.smartclass.models.Goal;

/**
 * Created by kevinT on 2017-06-16.
 */

public class GoalContract {

    interface Presenter {
        void onCreate();
        void onGoalsLoaded();
        void onGoalSelected(Goal goal);
    }

    interface View {
        void hideLoading();
        void showGoal(Goal goal);
    }
}
