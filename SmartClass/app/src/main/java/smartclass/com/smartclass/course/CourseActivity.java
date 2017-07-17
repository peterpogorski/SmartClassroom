package smartclass.com.smartclass.course;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import smartclass.com.smartclass.classroom.teacherGoals.GoalFragment;
import smartclass.com.smartclass.course.fragments.AttendanceFragment;
import smartclass.com.smartclass.course.fragments.quizzes.QuizFragment;
import smartclass.com.smartclass.R;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseActivity extends AppCompatActivity implements CourseContract.View {

    private enum CourseTabs {
        GOALS, QUIZ, ATTENDANCE
    }

    public final static String COURSE_NAME = "courseName";

    private CoursePresenter mPresenter;

    private Toolbar actionBar;

    private RelativeLayout mGoalsButton;
    private RelativeLayout mQuizButton;
    private RelativeLayout mAttendanceButton;

    private View mGoalsActive;
    private View mQuizActive;
    private View mAttendanceActive;

    private TextView mCourseTitle;

    private GoalFragment mGoalFragment;
    private AttendanceFragment mAttendanceFragment;
    private QuizFragment mQuizFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        String courseName = getIntent().getStringExtra(COURSE_NAME);
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle(courseName);
        setSupportActionBar(actionBar);

        mPresenter = new CoursePresenter(this);

        mGoalsButton = (RelativeLayout) findViewById(R.id.first_tab);
        mQuizButton = (RelativeLayout) findViewById(R.id.quiz);
        mAttendanceButton = (RelativeLayout) findViewById(R.id.attendance);

        mGoalsActive = findViewById(R.id.first_tab_active);
        mQuizActive = findViewById(R.id.quiz_active);
        mAttendanceActive = findViewById(R.id.attendance_active);

        ImageView progressButtonImage = (ImageView) findViewById(R.id.first_tab_image);
        progressButtonImage.setImageResource(R.drawable.trophy_white);

        TextView progressButtonLabel = (TextView) findViewById(R.id.first_tab_label);
        progressButtonLabel.setText("Goals");

        mGoalsButton.setOnClickListener(mOnProgressClickListener);
        mAttendanceButton.setOnClickListener(mOnAttendanceClickListener);
        mQuizButton.setOnClickListener(mOnQuizClickListener);

        mPresenter.onCreate();
    }

    @Override
    public void disableProgressTab() {
        mGoalsActive.setBackgroundResource(0);
    }

    @Override
    public void enableProgressTab() {
        changeTab(CourseTabs.GOALS);
    }

    @Override
    public void disableQuizTab() {
        mQuizActive.setBackgroundResource(0);
    }

    @Override
    public void enableQuizTab() {
        changeTab(CourseTabs.QUIZ);
    }

    @Override
    public void disableAttendanceTab() {
        mAttendanceActive.setBackgroundResource(0);
    }

    @Override
    public void enableAttendanceTab() {
        changeTab(CourseTabs.ATTENDANCE);
    }

    /**
     * Handles switching tab fragments.
     *
     * @param tab The tab that the user is switching to
     */
    private void changeTab(CourseTabs tab) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(tab) {
            case GOALS:
                mGoalsActive.setBackgroundResource(R.color.colorAccent);
                if(mGoalFragment == null) {
                    mGoalFragment = new GoalFragment();
                }
                transaction.replace(R.id.fragment_container, mGoalFragment);
                break;
            case ATTENDANCE:
                mAttendanceActive.setBackgroundResource(R.color.colorAccent);
                if(mAttendanceFragment == null) {
                    mAttendanceFragment = new AttendanceFragment();
                }
                transaction.replace(R.id.fragment_container, mAttendanceFragment);
                break;
            case QUIZ:
                mQuizActive.setBackgroundResource(R.color.colorAccent);
                if(mQuizFragment == null) {
                    mQuizFragment = new QuizFragment();
                }
                transaction.replace(R.id.fragment_container, mQuizFragment);
                break;
        }
        transaction.commit();
    }

    private View.OnClickListener mOnProgressClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onProgressTabSelected();
        }
    };

    private View.OnClickListener mOnQuizClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onQuizTabSelected();
        }
    };

    private View.OnClickListener mOnAttendanceClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onAttendanceTabSelected();
        }
    };
}