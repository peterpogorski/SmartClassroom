package smartclass.com.smartclass.course.fragments.quizzes.quizCreation;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import smartclass.com.smartclass.R;

public class QuizCreationActivity extends AppCompatActivity implements QuizCreationContract.View{

    private QuizCreationContract.Presenter mPresenter;

    private EditText titleField;
    private EditText descriptionField;
    private EditText questionField;
    private EditText answer1Field;
    private EditText answer2Field;
    private EditText answer3Field;
    private EditText answer4Field;
    private EditText answer5Field;
    private Spinner correctAnswerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_creation);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        titleField = (EditText) findViewById(R.id.title_field);
        descriptionField = (EditText) findViewById(R.id.description_field);
        questionField = (EditText) findViewById(R.id.question_field);
        answer1Field = (EditText) findViewById(R.id.answer1_field);
        answer2Field = (EditText) findViewById(R.id.answer2_field);
        answer3Field = (EditText) findViewById(R.id.answer3_field);
        answer4Field = (EditText) findViewById(R.id.answer4_field);
        answer5Field = (EditText) findViewById(R.id.answer5_field);

        // Set up the goal type spinner
        correctAnswerSpinner = (Spinner) findViewById(R.id.correct_answer_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quiz_answer_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        correctAnswerSpinner.setAdapter(adapter);

        mPresenter = new QuizCreationPresenter(this);
        mPresenter.onCreate();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quiz_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_finish:
                if (mPresenter.createQuiz()) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // CONTRACT METHODS


    @Override
    public String getTitleInput() {
        if (titleField == null) { return ""; }

        Editable title = titleField.getText();
        return title == null ? "" : title.toString();
    }

    @Override
    public String getDescriptionInput() {
        if (descriptionField == null) { return ""; }

        Editable description = descriptionField.getText();
        return description == null ? "" : description.toString();
    }

    @Override
    public String getQuestion() {
        if (questionField == null) { return ""; }

        Editable question = questionField.getText();
        return question == null ? "" : question.toString();
    }

    @Override
    public String getAnswer1() {
        if (answer1Field == null) { return ""; }

        Editable answer1 = answer1Field.getText();
        return answer1 == null ? "" : answer1.toString();
    }

    @Override
    public String getAnswer2() {
        if (answer2Field == null) { return ""; }

        Editable answer2 = answer2Field.getText();
        return answer2 == null ? "" : answer2.toString();
    }

    @Override
    public String getAnswer3() {
        if (answer3Field == null) { return ""; }

        Editable answer3 = answer3Field.getText();
        return answer3 == null ? "" : answer3.toString();
    }

    @Override
    public String getAnswer4() {
        if (answer4Field == null) { return ""; }

        Editable answer4 = answer4Field.getText();
        return answer4 == null ? "" : answer4.toString();
    }

    @Override
    public String getAnswer5() {
        if (answer5Field == null) { return ""; }

        Editable answer5 = answer5Field.getText();
        return answer5 == null ? "" : answer5.toString();
    }

    @Override
    public String getCorrectAnswer() {
        if (correctAnswerSpinner != null) {
            return correctAnswerSpinner.getSelectedItem().toString();
        }

        return "";
    }

    /**
     * Highlights empty EditText fields and returns true if fields were highlighted, false otherwise.
     */
    @Override
    public boolean highlightEmptyFields() {
        boolean returnValue = false;
        if (titleField.getText().toString().trim().isEmpty()) {
            titleField.setError("This field cannot be empty");
            returnValue = true;
        }

        if (descriptionField.getText().toString().trim().isEmpty()) {
            descriptionField.setError("This field cannot be empty");
            returnValue = true;
        }

        if (questionField.getText().toString().trim().isEmpty()) {
            questionField.setError("This field cannot be empty");
            returnValue = true;
        }

        if (answer1Field.getText().toString().trim().isEmpty()) {
            answer1Field.setError("This field cannot be empty");
            returnValue = true;
        }

        if (answer2Field.getText().toString().trim().isEmpty()) {
            answer2Field.setError("This field cannot be empty");
            returnValue = true;
        }

        if (answer3Field.getText().toString().trim().isEmpty()) {
            answer3Field.setError("This field cannot be empty");
            returnValue = true;
        }

        if (answer4Field.getText().toString().trim().isEmpty()) {
            answer4Field.setError("This field cannot be empty");
            returnValue = true;
        }

        if (answer5Field.getText().toString().trim().isEmpty()) {
            answer5Field.setError("This field cannot be empty");
            returnValue = true;
        }

        return returnValue;
    }
}
