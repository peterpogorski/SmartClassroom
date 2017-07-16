package smartclass.com.smartclass.course.fragments.quizzes;

import smartclass.com.smartclass.models.Quiz;

/**
 * Created by kevinT on 2017-07-16.
 */

public class QuizContract {

    interface Presenter {
        void onCreate();
        void onGoalsLoaded();
        void onGoalSelected(Quiz quiz);
    }

    interface View {
        void hideLoading();
        void showGoal(Quiz quiz);
    }
}
