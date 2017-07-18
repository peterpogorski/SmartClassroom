package smartclass.com.smartclass.classroom.teacherGoals.goalAssignment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import smartclass.com.smartclass.R;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.UserToken;
import smartclass.com.smartclass.models.AssignGoalStudents;
import smartclass.com.smartclass.models.Goal;
import smartclass.com.smartclass.models.GoalAssignedResponse;
import smartclass.com.smartclass.models.GoalCreate;
import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class GoalAssignmentActivity extends Activity implements GoalAssignmentContract.View {

    public static final String GOAL_ID = "goal_id";
    public static final String GOAL_TITLE = "goal_title";

    private ImageButton mAcceptButton;
    private RecyclerView mRecyclerView;

    private String mGoalTitle;
    private String mGoalId;
    private GoalAssignmentAdapter mAdapter;
    private GoalAssignmentPresenter mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Student> mStudentList = new ArrayList<>();
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoalId = getIntent().getStringExtra(GOAL_ID);
        mGoalTitle = getIntent().getStringExtra(GOAL_TITLE);

        setContentView(R.layout.activity_goal_assignment);

        mAcceptButton = (ImageButton) findViewById(R.id.accept);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAcceptButton.setOnClickListener(mOnAcceptClicked);

        mPresenter = new GoalAssignmentPresenter(this);
        mRetrofit = SmartClassRetrofit.getInstance();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPresenter.onCreate();
    }

    @Override
    public void loadStudentList() {
        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);

        String classroomId = UserToken.getInstance().getClassroomId();
        String token = UserToken.getInstance().getTokenValue();

        Call<ArrayList<Student>> getStudents = smartClassService.getClassroomStudents(classroomId, token);
        getStudents.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                mPresenter.onStudentListLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                mPresenter.onErrorLoading();
            }
        });
    }

    @Override
    public void showListOfStudents(ArrayList<Student> studentList) {
        mStudentList = studentList;
        mAdapter = new GoalAssignmentAdapter(this, mStudentList, mPresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void addGoalToStudents(ArrayList<String> studentIds) {
        Date date = new Date();

        GoalCreate goalCreate = new GoalCreate(mGoalTitle, date ,mGoalId);
        AssignGoalStudents assignGoalStudents = new AssignGoalStudents(studentIds, goalCreate);
        String token = UserToken.getInstance().getTokenValue();

        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);
        Call<GoalAssignedResponse> assignGoalToStudents = smartClassService.assignGoalToStudents(assignGoalStudents, token);
        assignGoalToStudents.enqueue(new Callback<GoalAssignedResponse>() {
            @Override
            public void onResponse(Call<GoalAssignedResponse> call, Response<GoalAssignedResponse> response) {
                if(response.body().isAccepted()) {
                    mPresenter.onGoalAssignedSuccessfully();
                }
            }

            @Override
            public void onFailure(Call<GoalAssignedResponse> call, Throwable t) {
                Toast.makeText(GoalAssignmentActivity.this, "Sorry an error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this, "Sorry, something went wrong.", Toast.LENGTH_SHORT);
    }

    @Override
    public void showSuccess() {
        Toast.makeText(GoalAssignmentActivity.this, "Goal assigned successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private View.OnClickListener mOnAcceptClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onStudentsAccepted(mStudentList);
        }
    };

}
