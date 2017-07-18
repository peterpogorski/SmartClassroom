package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.QuizQuestionOption;

/**
 * This Activity is for viewing the details of a quiz in teacher mode.
 * From this activity a teacher can start or stop a quiz.
 *
 * Created by kevinT on 2017-07-17.
 */

public class QuizViewActivity extends AppCompatActivity implements QuizViewContract.View {

    private QuizViewPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mPresenter = new QuizViewPresenter(this);
        mPresenter.onCreate();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // CONTRACT METHODS

    @Override
    public void setupQuizUI(@NonNull Quiz quiz) {
        // TODO
    }
}
