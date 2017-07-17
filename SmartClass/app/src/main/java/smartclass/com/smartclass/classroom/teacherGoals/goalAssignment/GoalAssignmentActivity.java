package smartclass.com.smartclass.classroom.teacherGoals.goalAssignment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import smartclass.com.smartclass.R;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class GoalAssignmentActivity extends Activity implements GoalAssignmentContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goal_assignment);
    }

}
