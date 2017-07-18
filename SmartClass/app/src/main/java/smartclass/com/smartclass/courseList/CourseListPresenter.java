package smartclass.com.smartclass.courseList;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Classroom;
import smartclass.com.smartclass.models.Course;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseListPresenter implements CourseListContract.Presenter {

    private CourseListContract.View mCourseView;

    public CourseListPresenter(CourseListContract.View courseView) {
        mCourseView = courseView;
    }

    @Override
    public void onLogoutClicked() {
        mCourseView.confirmLogout();
    }

    @Override
    public void onLogoutConfirmed() {
        mCourseView.clearCredentials();
        mCourseView.showLoginScreen();
    }

    @Override
    public void onCreate() {
        mCourseView.loadClassrooms();
    }

    @Override
    public void onCourseSelected(Classroom classroom) {
        mCourseView.showCourse(classroom.getTitle(), classroom.getClassroomId());
    }

    @Override
    public void onClassroomsLoaded(ArrayList<Classroom> classrooms) {
        mCourseView.displayClassrooms(classrooms);
    }
}
