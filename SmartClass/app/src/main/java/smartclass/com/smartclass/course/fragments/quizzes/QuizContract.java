package smartclass.com.smartclass.course.fragments.quizzes;

import smartclass.com.smartclass.models.Quiz;

/**
 * Created by kevinT on 2017-07-16.
 */

public class QuizContract {

    interface Presenter {
        void onCreate();
        void onQuizzesLoaded();
        void onQuizSelected(Quiz quiz);
    }

    interface View {
        void hideLoading();
        void showQuiz(Quiz quiz);
    }
}
