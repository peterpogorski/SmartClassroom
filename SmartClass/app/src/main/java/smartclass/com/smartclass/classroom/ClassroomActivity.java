package smartclass.com.smartclass.classroom;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.classroom.students.StudentsFragment;
import smartclass.com.smartclass.course.fragments.AttendanceFragment;
import smartclass.com.smartclass.course.fragments.QuizFragment;

/**
 * Created by kevinT on 2017-06-15.
 */

public class ClassroomActivity extends FragmentActivity implements ClassroomContract.View {

    private enum ClassroomTabs {
        STUDENTS, QUIZ, ATTENDANCE
    }

    public final static String COURSE_NAME = "courseName";

    private ClassroomPresenter mPresenter;

    private LinearLayout mStudentsButton;
    private LinearLayout mQuizButton;
    private LinearLayout mAttendanceButton;

    private View mStudentsActive;
    private View mQuizActive;
    private View mAttendanceActive;

    private TextView mCourseTitle;

    private StudentsFragment mStudentsFragment;
    private QuizFragment mQuizFragment;
    private AttendanceFragment mAttendanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        String courseName = getIntent().getStringExtra(COURSE_NAME);

        mPresenter = new ClassroomPresenter(this);

        mStudentsButton = (LinearLayout) findViewById(R.id.students);
        mStudentsButton.setVisibility(View.VISIBLE);
        mQuizButton = (LinearLayout) findViewById(R.id.quiz);
        mAttendanceButton = (LinearLayout) findViewById(R.id.attendance);

        // Hide the default first tab (progress tab)
        LinearLayout mProgressButton = ((LinearLayout) findViewById(R.id.progress));
        mProgressButton.setVisibility(View.GONE);

        mStudentsActive = findViewById(R.id.students_active);
        mQuizActive = findViewById(R.id.quiz_active);
        mAttendanceActive = findViewById(R.id.attendance_active);

        mCourseTitle = (TextView) findViewById(R.id.course_title);
        mCourseTitle.setText(courseName);

        mStudentsButton.setOnClickListener(mOnStudentsClickListener);
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
                mStudentsActive.setBackgroundResource(R.color.active_blue);
                if(mStudentsFragment == null) {
                    mStudentsFragment = StudentsFragment.newInstance();
                }
                transaction.replace(R.id.fragment_container, mStudentsFragment);
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

    private View.OnClickListener mOnStudentsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onStudentsTabSelected();
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