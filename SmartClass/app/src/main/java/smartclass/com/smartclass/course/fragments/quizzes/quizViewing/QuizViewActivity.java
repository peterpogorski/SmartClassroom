package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.QuizQuestion;
import smartclass.com.smartclass.models.QuizQuestionOption;
import smartclass.com.smartclass.models.StudentQuizResponse;

/**
 * This Activity is for viewing the details of a quiz in teacher mode.
 * From this activity a teacher can start or stop a quiz.
 *
 * Created by kevinT on 2017-07-17.
 */

public class QuizViewActivity extends AppCompatActivity implements QuizViewContract.View {

    private QuizViewPresenter mPresenter;

    private TextView quizTitle;
    private TextView quizDescription;
    private TextView quizQuestion;
    private RadioGroup answersRadioGroup;
    private Button submitButton;
    private ArrayList<RadioButton> radioButtons;

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

        quizTitle = (TextView) findViewById(R.id.quiz_title);
        quizDescription = (TextView) findViewById(R.id.quiz_description);
        quizQuestion = (TextView) findViewById(R.id.quiz_question);
        answersRadioGroup = (RadioGroup) findViewById(R.id.answers_radio_group);
        radioButtons = new ArrayList<>();
        radioButtons.add((RadioButton) findViewById(R.id.answer1_button));
        radioButtons.add((RadioButton) findViewById(R.id.answer2_button));
        radioButtons.add((RadioButton) findViewById(R.id.answer3_button));
        radioButtons.add((RadioButton) findViewById(R.id.answer4_button));
        radioButtons.add((RadioButton) findViewById(R.id.answer5_button));
        submitButton = (Button) findViewById(R.id.submit_buton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPresenter == null) { return; }

                int selectedRadioButtonId = answersRadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId == -1) { return ; }

                String selectedAnswer = "";
                for (int i = 0; i < 5; i++) {
                    if (radioButtons.get(i).isChecked()) {
                        selectedAnswer = radioButtons.get(i).getText().toString();
                        break;
                    }
                }

                Quiz quiz = TeacherModeDataManager.getInstance().getSelectedQuiz();
                if (quiz == null) { return; }

                QuizQuestion question = quiz.getQuestions().get(0);
                boolean isCorrect = mPresenter.isAnswerCorrect(selectedAnswer);
                StudentQuizResponse response = new StudentQuizResponse(question.getQuestion(), selectedAnswer, isCorrect);
                mPresenter.submitQuiz(quiz, response);
            }
        });

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
        quizTitle.setText(quiz.getTitle());
        quizDescription.setText(quiz.getDescription());
        QuizQuestion question = quiz.getQuestions().get(0);
        quizQuestion.setText(question.getQuestion());
        ArrayList<QuizQuestionOption> questionOptions = question.getOptions();
        radioButtons.get(0).setText(questionOptions.get(0).getText());
        radioButtons.get(1).setText(questionOptions.get(1).getText());
        radioButtons.get(2).setText(questionOptions.get(2).getText());
        radioButtons.get(3).setText(questionOptions.get(3).getText());
        radioButtons.get(4).setText(questionOptions.get(4).getText());
        for (QuizQuestionOption option : questionOptions) {
            if (option.isCorrect()) {
                if (mPresenter == null) { break; }
                mPresenter.setCorrectAnswer(option.getText());
                break;
            }
        }
    }

    @Override
    public void dismissView() {
        finish();
    }

    @Override
    public void showToastMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
