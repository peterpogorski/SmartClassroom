package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.QuizQuestionOption;

public class QuizViewActivity extends AppCompatActivity implements QuizViewContract.View {

    private QuizViewPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);

        mPresenter = new QuizViewPresenter(this);
        mPresenter.onCreate();
    }

    // CONTRACT METHODS

    @Override
    public void updateQuizTitle(String title) {
        // TODO
    }

    @Override
    public void updateQuizDescription(String description) {
        // TODO
    }

    @Override
    public void updateQuizQuestion(String question) {
        // TODO
    }

    @Override
    public void updateAnswers(ArrayList<QuizQuestionOption> questionOptions) {
        // TODO
    }
}
