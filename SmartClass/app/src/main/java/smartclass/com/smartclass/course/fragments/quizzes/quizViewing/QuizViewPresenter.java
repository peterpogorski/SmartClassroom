package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import smartclass.com.smartclass.models.Quiz;

/**
 * Created by kevinT on 2017-07-17.
 */

public class QuizViewPresenter implements QuizViewContract.Presenter {

    private QuizViewContract.View mView;

    public QuizViewPresenter(QuizViewContract.View view) {
        mView = view;
    }

    // CONTRACT METHODS

    @Override
    public void onCreate() {
        // TODO
    }

    @Override
    public void startQuiz(Quiz quiz) {
        // TODO
    }

    @Override
    public void stopQuiz(Quiz quiz) {
        // TODO
    }
}
