package smartclass.com.smartclass.classroom.teacherGoals.goalCreation;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import smartclass.com.smartclass.DatePickerFragment;
import smartclass.com.smartclass.R;

public class GoalCreationActivity extends AppCompatActivity implements GoalCreationContract.View {

    private GoalCreationPresenter mPresenter;

    private EditText titleField;
    private EditText descriptionField;
    private EditText weightField;
    private EditText marksField;
    private Spinner goalTypeSpinner;
    // TODO: Add DatePickers
    private Button startDateButton;
    private Button endDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_creation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        titleField = (EditText) findViewById(R.id.title_field);
        descriptionField = (EditText) findViewById(R.id.description_field);
        weightField = (EditText) findViewById(R.id.weight_field);
        marksField = (EditText) findViewById(R.id.marks_field);

        // Set up the goal type spinner
        goalTypeSpinner = (Spinner) findViewById(R.id.goal_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.goal_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goalTypeSpinner.setAdapter(adapter);

        startDateButton = (Button) findViewById(R.id.start_date_button);
        endDateButton = (Button) findViewById(R.id.end_date_button);

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Display date picker for start date
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "datePicker");
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Display date picker for end date
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "datePicker");
            }
        });

        mPresenter = new GoalCreationPresenter(this);
        mPresenter.onCreate();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_goal_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_finish:
                // TODO: Save Goal
                if (mPresenter.createGoal()) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // CONTRACT METHODS


    @NonNull
    @Override
    public String getTitleInput() {
        return titleField != null ? titleField.getText().toString().trim() : "";
    }

    @NonNull
    @Override
    public String getDescriptionInput() {
        return descriptionField != null ? descriptionField.getText().toString().trim() : "";
    }

    @NonNull
    @Override
    public String getType() {
        if (goalTypeSpinner != null) {
            return goalTypeSpinner.getSelectedItem().toString();
        }

        return "";
    }

    @Override
    public Date getStartDate() {
        return null;
    }

    @Override
    public Date getEndDate() {
        return null;
    }

    @NonNull
    @Override
    public Double getWeightInput() {
        if (weightField != null) {
            String weight = weightField.getText().toString().trim();
            return Double.parseDouble(weight);
        }

        return 0d;
    }

    @NonNull
    @Override
    public Double getMarksInput() {
        if (marksField != null) {
            String marks = marksField.getText().toString().trim();
            return Double.parseDouble(marks);
        }

        return 0d;
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

        if (weightField.getText().toString().trim().isEmpty()) {
            weightField.setError("This field cannot be empty");
            returnValue = true;
        }

        if (marksField.getText().toString().trim().isEmpty()) {
            marksField.setError("This field cannot be empty");
            returnValue = true;
        }

        return returnValue;
    }

    @Override
    public void displaySnackbar(String message) {

    }
}
