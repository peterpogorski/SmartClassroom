package smartclass.com.smartclass.course.fragments.quizzes;

import smartclass.com.smartclass.models.Quiz;

/**
 * Created by kevinT on 2017-07-16.
 */

public class QuizPresenter implements QuizContract.Presenter {

    private QuizContract.View mView;

    public QuizPresenter(QuizContract.View view) { mView = view; }

    @Override
    public void onCreate() {
    }

    @Override
    public void onQuizzesLoaded() {
        mView.hideLoading();
    }

    @Override
    public void onQuizSelected(Quiz quiz) {
        // TODO
    }
}
