package smartclass.com.smartclass.courseList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import smartclass.com.smartclass.classroom.ClassroomActivity;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.login.LoginActivity;
import smartclass.com.smartclass.models.Course;
import smartclass.com.smartclass.course.CourseActivity;
import smartclass.com.smartclass.R;

/**
 * Created by peterpogorski on 2017-06-12.
 */
public class CourseListActivity extends Activity implements CourseListContract.View {

    private CourseListAdapter mListAdapter;
    private List<Course> mCourseList = new ArrayList<Course>();
    private RecyclerView mRecyclerView;
    private CourseListPresenter mPresenter;
    private ImageButton mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_screen);

        mRecyclerView = (RecyclerView) findViewById(R.id.course_list_view);
        mLogout = (ImageButton) findViewById(R.id.logout);

        mLogout.setOnClickListener(mOnLogoutClickListener);

        Course course1 = new Course("ECE 105", "James Smith", 25);
        Course course2 = new Course("ECE 116", "James Kennings", 25);
        Course course3 = new Course("ECE 125", "Arthur Kennings", 25);
        Course course4 = new Course("ECE 135", "Tim Smith", 25);
        Course course5 = new Course("ECE 149", "Able John", 25);

        mCourseList.add(course1);
        mCourseList.add(course2);
        mCourseList.add(course3);
        mCourseList.add(course4);
        mCourseList.add(course5);

        mPresenter = new CourseListPresenter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mListAdapter = new CourseListAdapter(mCourseList, mPresenter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void showCourse(String courseName) {
        Intent intent;
        if (TeacherModeDataManager.getInstance().isTeacherModeEnabled()) {
            intent = new Intent(this, ClassroomActivity.class);
            intent.putExtra(ClassroomActivity.COURSE_NAME, courseName);
        } else {
            intent = new Intent(this, CourseActivity.class);
            intent.putExtra(CourseActivity.COURSE_NAME, courseName);
        }
        startActivity(intent);
    }

    @Override
    public void confirmLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure that you want to log out?");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onLogoutConfirmed();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void clearCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    @Override
    public void showLoginScreen() {
        Intent intent = new Intent(CourseListActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener mOnLogoutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onLogoutClicked();
        }
    };
}
