package smartclass.com.smartclass.classroom;

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

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.classroom.students.StudentsFragment;
import smartclass.com.smartclass.classroom.teacherGoals.GoalFragment;
import smartclass.com.smartclass.course.fragments.AttendanceFragment;
import smartclass.com.smartclass.course.fragments.QuizFragment;

/**
 * Created by kevinT on 2017-06-15.
 */

public class ClassroomActivity extends AppCompatActivity implements ClassroomContract.View {

    private enum ClassroomTabs {
        STUDENTS, GOALS, QUIZ, ATTENDANCE
    }

    public final static String COURSE_NAME = "courseName";

    private ClassroomPresenter mPresenter;

    private Toolbar actionBar;

    private RelativeLayout mStudentsButton;
    private RelativeLayout mGoalsButton;
    private RelativeLayout mQuizButton;
    private RelativeLayout mAttendanceButton;

    private View mStudentsActive;
    private View mGoalsActive;
    private View mQuizActive;
    private View mAttendanceActive;

    private TextView mCourseTitle;

    private StudentsFragment mStudentsFragment;
    private GoalFragment mGoalsFragment;
    private QuizFragment mQuizFragment;
    private AttendanceFragment mAttendanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        // Set up the action bar
        String courseName = getIntent().getStringExtra(COURSE_NAME);
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle(courseName);
        setSupportActionBar(actionBar);

        // Initialize the presenter for this Activity
        mPresenter = new ClassroomPresenter(this);

        mStudentsButton = ((RelativeLayout) findViewById(R.id.first_tab));
        mGoalsButton = (RelativeLayout) findViewById(R.id.second_tab);
        mQuizButton = (RelativeLayout) findViewById(R.id.quiz);
        mAttendanceButton = (RelativeLayout) findViewById(R.id.attendance);

        mStudentsActive = findViewById(R.id.first_tab_active);
        mGoalsActive = findViewById(R.id.second_tab_active);
        mQuizActive = findViewById(R.id.quiz_active);
        mAttendanceActive = findViewById(R.id.attendance_active);

        // Make the second tab visible (goals tab)
        mGoalsButton.setVisibility(View.VISIBLE);

        // Adjust images on the first and second tabs
        ImageView studentsImageView = (ImageView) findViewById(R.id.first_tab_image);
        ImageView goalsImageView = (ImageView) findViewById(R.id.second_tab_image);
        studentsImageView.setImageResource(R.drawable.children);
        goalsImageView.setImageResource(R.drawable.trophy_white);

        // Adjust labels on the first and second tabs
        TextView studentsLabel = (TextView) findViewById(R.id.first_tab_label);
        TextView goalsLabel = (TextView) findViewById(R.id.second_tab_label);
        studentsLabel.setText("Students");
        goalsLabel.setText("Goals");

        // Register onClickListeners for the tab buttons
        mStudentsButton.setOnClickListener(mOnStudentsClickListener);
        mGoalsButton.setOnClickListener(mOnGoalsClickListener);
        mAttendanceButton.setOnClickListener(mOnAttendanceClickListener);
        mQuizButton.setOnClickListener(mOnQuizClickListener);

        mPresenter.onCreate();
    }

    @Override
    public void disableStudentsTab() {
        mStudentsActive.setBackgroundResource(0);
    }

    @Override
    public void enableStudentsTab() {
        changeTab(ClassroomTabs.STUDENTS);
    }

    @Override
    public void disableGoalsTab() {
        mGoalsActive.setBackgroundResource(0);
    }

    @Override
    public void enableGoalsTab() {
        changeTab(ClassroomTabs.GOALS);
    }

    @Override
    public void disableQuizTab() {
        mQuizActive.setBackgroundResource(0);
    }

    @Override
    public void enableQuizTab() {
        changeTab(ClassroomTabs.QUIZ);
    }

    @Override
    public void disableAttendanceTab() {
        mAttendanceActive.setBackgroundResource(0);
    }

    @Override
    public void enableAttendanceTab() {
        changeTab(ClassroomTabs.ATTENDANCE);
    }

    /**
     * Handles switching tab fragments.
     *
     * @param tab The tab that the user is switching to
     */
    private void changeTab(ClassroomTabs tab) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(tab) {
            case STUDENTS:
                mStudentsActive.setBackgroundResource(R.color.colorAccent);
                if(mStudentsFragment == null) {
                    mStudentsFragment = StudentsFragment.newInstance();
                }
                transaction.replace(R.id.fragment_container, mStudentsFragment);
                break;
            case GOALS:
                mGoalsActive.setBackgroundResource(R.color.colorAccent);
                if(mGoalsFragment == null) {
                    mGoalsFragment = new GoalFragment();
                }
                transaction.replace(R.id.fragment_container, mGoalsFragment);
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

    private View.OnClickListener mOnStudentsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onStudentsTabSelected();
        }
    };

    private View.OnClickListener mOnGoalsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onGoalsTabSelected();
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