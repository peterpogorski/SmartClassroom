package smartclass.com.smartclass;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by peterpogorski on 2017-06-12.
 */
public class CourseListActivity extends Activity implements CourseContract.View {

    private CourseListAdapter mListAdapter;
    private List<Course> mCourseList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_screen);

        mRecyclerView = (RecyclerView) findViewById(R.id.course_list_view);

        Course course1 = new Course("ECE 105", "Andrew Kennings", 25);
        Course course2 = new Course("ECE 105", "Andrew Kennings", 25);
        Course course3 = new Course("ECE 105", "Andrew Kennings", 25);
        Course course4 = new Course("ECE 105", "Andrew Kennings", 25);
        Course course5 = new Course("ECE 105", "Andrew Kennings", 25);

        mCourseList.add(course1);
        mCourseList.add(course2);
        mCourseList.add(course3);
        mCourseList.add(course4);
        mCourseList.add(course5);


        mListAdapter = new CourseListAdapter(mCourseList, this);
        mRecyclerView.setAdapter(mListAdapter);

    }



    @Override
    public void showCourse() {

    }
}
