package smartclass.com.smartclass.courseList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import smartclass.com.smartclass.classroom.ClassroomActivity;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_screen);

        mRecyclerView = (RecyclerView) findViewById(R.id.course_list_view);

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
}
