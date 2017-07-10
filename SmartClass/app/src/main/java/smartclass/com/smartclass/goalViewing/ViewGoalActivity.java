package smartclass.com.smartclass.goalViewing;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Goal;

/**
 * This Activity displays the details of a goal
 */
public class ViewGoalActivity extends AppCompatActivity implements ViewGoalContract.View {

    private Goal goal;
    private ViewGoalPresenter mPresenter;

    private TextView titleField;
    private TextView descriptionField;
    private TextView goalTypeField;
    private TextView startDateField;
    private TextView endDateField;
    private TextView weightField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);

        // Initialize private variables
        mPresenter = new ViewGoalPresenter(this);
        titleField = (TextView) findViewById(R.id.title_field);
        descriptionField = (TextView) findViewById(R.id.description_field);
        goalTypeField = (TextView) findViewById(R.id.goal_type_field);
        startDateField = (TextView) findViewById(R.id.start_date_field);
        endDateField = (TextView) findViewById(R.id.end_date_field);
        weightField = (TextView) findViewById(R.id.weight_field);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            goal = bundle.getParcelable("goal");
            mPresenter.presentGoal(goal);
        }

        // Set up action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Goal Details");
        }
    }

    // CONTRACT methods

    @Override
    public void updateView(Goal goal) {
        String title = goal.getTitle();
        titleField.setText(title != null ? title : "No Title");

        String description = goal.getDescription();
        descriptionField.setText(description != null ? description : "No description");

        String goalType = goal.getType();
        goalTypeField.setText(goalType != null ? goalType : "No type");

        Date startDate = goal.getStartDate();
        if (startDate != null) {
            startDateField.setText(DateFormat.getDateInstance().format(startDate));
        } else {
            startDateField.setText("N/A");
        }

        Date endDate = goal.getEndDate();
        if (endDate != null) {
            endDateField.setText(DateFormat.getDateInstance().format(endDate));
        } else {
            endDateField.setText("N/A");
        }

        String weightString = goal.getWeight()*100 + "%";
        weightField.setText(weightString);
    }
}
