package smartclass.com.smartclass;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseActivity extends FragmentActivity implements CourseContract.View {

    public final static String COURSE_NAME = "courseName";

    private CoursePresenter mPresenter;

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

        mPresenter = new CoursePresenter(this);

        mProgressButton = (LinearLayout) findViewById(R.id.progress);
        mQuizButton = (LinearLayout) findViewById(R.id.quiz);
        mAttendanceButton = (LinearLayout) findViewById(R.id.attendance);

        mProgressActive = findViewById(R.id.progress_active);
        mQuizActive = findViewById(R.id.quiz_active);
        mAttendanceActive = findViewById(R.id.attendance_active);

        mCourseTitle = (TextView) findViewById(R.id.course_title);
        mCourseTitle.setText(courseName);

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
        mProgressActive.setBackgroundResource(R.color.active_blue);
        if(mProgressFragment == null) {
            mProgressFragment = new ProgressFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, mProgressFragment);
        transaction.commit();
    }

    @Override
    public void disableQuizTab() {
        mQuizActive.setBackgroundResource(0);
    }

    @Override
    public void enableQuizTab() {
        mQuizActive.setBackgroundResource(R.color.active_blue);
        if(mQuizFragment == null) {
            mQuizFragment = new QuizFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, mQuizFragment);
        transaction.commit();
    }

    @Override
    public void diableAttendanceTab() {
        mAttendanceActive.setBackgroundResource(0);
    }

    @Override
    public void enableAttendaceTab() {
        mAttendanceActive.setBackgroundResource(R.color.active_blue);
        if(mAttendanceFragment == null) {
            mAttendanceFragment = new AttendanceFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, mAttendanceFragment);
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
