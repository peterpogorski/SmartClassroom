package smartclass.com.smartclass.classroom.teacherGoals.goalCreation;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import smartclass.com.smartclass.R;

public class GoalCreationActivity extends AppCompatActivity implements GoalCreationContract.View {

    private GoalCreationPresenter mPresenter;

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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
