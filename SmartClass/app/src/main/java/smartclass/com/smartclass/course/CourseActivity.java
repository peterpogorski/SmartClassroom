package smartclass.com.smartclass.course;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import smartclass.com.smartclass.course.fragments.AttendanceFragment;
import smartclass.com.smartclass.course.fragments.ProgressFragment;
import smartclass.com.smartclass.course.fragments.QuizFragment;
import smartclass.com.smartclass.R;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseActivity extends FragmentActivity implements CourseContract.View {

    private enum CourseTabs {
        PROGRESS, QUIZ, ATTENDANCE
    }

    public final static String COURSE_NAME = "courseName";

    private CoursePresenter mPresenter;

    private Toolbar actionBar;

    private LinearLayout mProgressButton;
    private LinearLayout mQuizButton;
    private LinearLayout mAttendanceButton;

    private View mProgressActive;
    private View mQuizActive;
    private View mAttendanceActive;

    private TextView mCourseTitle;

    private ProgressFragment mProgressFragment;
    private AttendanceFragment mAttendanceFragment;
    private QuizFragment mQuizFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        String courseName = getIntent().getStringExtra(COURSE_NAME);
        actionBar = (Toolbar) findViewById(R.id.action_bar);
        actionBar.setTitle(courseName);

        mPresenter = new CoursePresenter(this);

        mProgressButton = (LinearLayout) findViewById(R.id.first_tab);
        mQuizButton = (LinearLayout) findViewById(R.id.quiz);
        mAttendanceButton = (LinearLayout) findViewById(R.id.attendance);

        mProgressActive = findViewById(R.id.first_tab_active);
        mQuizActive = findViewById(R.id.quiz_active);
        mAttendanceActive = findViewById(R.id.attendance_active);

        ImageView progressButtonImage = (ImageView) findViewById(R.id.first_tab_image);
        progressButtonImage.setImageResource(R.drawable.goals_icon);

        TextView progressButtonLabel = (TextView) findViewById(R.id.first_tab_label);
        progressButtonLabel.setText("Progress");

        mProgressButton.setOnClickListener(mOnProgressClickListener);
        mAttendanceButton.setOnClickListener(mOnAttendanceClickListener);
        mQuizButton.setOnClickListener(mOnQuizClickListener);

        mPresenter.onCreate();
    }

    @Override
    public void disableProgressTab() {
        mProgressActive.setBackgroundResource(0);
    }

    @Override
    public void enableProgressTab() {
        changeTab(CourseTabs.PROGRESS);
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
            case PROGRESS:
                mProgressActive.setBackgroundResource(R.color.active_blue);
                if(mProgressFragment == null) {
                    mProgressFragment = new ProgressFragment();
                }
                transaction.replace(R.id.fragment_container, mProgressFragment);
                break;
            case ATTENDANCE:
                mAttendanceActive.setBackgroundResource(R.color.active_blue);
                if(mAttendanceFragment == null) {
                    mAttendanceFragment = new AttendanceFragment();
                }
                transaction.replace(R.id.fragment_container, mAttendanceFragment);
                break;
            case QUIZ:
                mQuizActive.setBackgroundResource(R.color.active_blue);
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