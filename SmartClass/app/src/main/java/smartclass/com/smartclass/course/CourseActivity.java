package smartclass.com.smartclass.course;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import smartclass.com.smartclass.course.fragments.AttendanceFragment;
import smartclass.com.smartclass.course.fragments.ProgressFragment;
import smartclass.com.smartclass.course.fragments.QuizFragment;
import smartclass.com.smartclass.course.fragments.StudentsFragment;
import smartclass.com.smartclass.R;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseActivity extends FragmentActivity implements CourseContract.View {

    private enum CourseTabs {
        PROGRESS, QUIZ, ATTENDANCE, STUDENTS
    }

    public final static String COURSE_NAME = "courseName";

    private CoursePresenter mPresenter;

    private LinearLayout mProgressButton;
    private LinearLayout mQuizButton;
    private LinearLayout mAttendanceButton;
    private LinearLayout mStudentsButton;

    private View mProgressActive;
    private View mQuizActive;
    private View mAttendanceActive;
    private View mStudentsActive;

    private TextView mCourseTitle;

    private ProgressFragment mProgressFragment;
    private AttendanceFragment mAttendanceFragment;
    private QuizFragment mQuizFragment;
    private StudentsFragment mStudentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        String courseName = getIntent().getStringExtra(COURSE_NAME);

        mPresenter = new CoursePresenter(this);

        mProgressButton = (LinearLayout) findViewById(R.id.progress);
        mQuizButton = (LinearLayout) findViewById(R.id.quiz);
        mAttendanceButton = (LinearLayout) findViewById(R.id.attendance);
        mStudentsButton = (LinearLayout) findViewById(R.id.students);

        mProgressActive = findViewById(R.id.progress_active);
        mQuizActive = findViewById(R.id.quiz_active);
        mAttendanceActive = findViewById(R.id.attendance_active);
        mStudentsActive = findViewById(R.id.students_active);

        mCourseTitle = (TextView) findViewById(R.id.course_title);
        mCourseTitle.setText(courseName);

        mProgressButton.setOnClickListener(mOnProgressClickListener);
        mAttendanceButton.setOnClickListener(mOnAttendanceClickListener);
        mQuizButton.setOnClickListener(mOnQuizClickListener);
        mStudentsButton.setOnClickListener(mOnStudentsClickListener);

        mPresenter.onCreate();
    }

    @Override
    public void initialDisplay(boolean teacherMode) {
        if (teacherMode) {
            enableStudentsTab();
            mStudentsButton.setVisibility(View.VISIBLE);
            mProgressButton.setVisibility(View.GONE);
        } else {
            enableProgressTab();
            mProgressButton.setVisibility(View.VISIBLE);
            mStudentsButton.setVisibility(View.GONE);
        }
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

    @Override
    public void disableStudentsTab() {
        mStudentsActive.setBackgroundResource(0);
    }

    @Override
    public void enableStudentsTab() {
        changeTab(CourseTabs.STUDENTS);
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
            case STUDENTS:
                mStudentsActive.setBackgroundResource(R.color.active_blue);
                if(mStudentsFragment == null) {
                    mStudentsFragment = new StudentsFragment();
                }
                transaction.replace(R.id.fragment_container, mStudentsFragment);
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

    private View.OnClickListener mOnStudentsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onStudentsTabSelected();
        }
    };
}
