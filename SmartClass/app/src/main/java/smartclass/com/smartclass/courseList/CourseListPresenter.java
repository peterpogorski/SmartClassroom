package smartclass.com.smartclass.courseList;

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
    public void onCourseSelected(Course course) {
        mCourseView.showCourse(course.getClassName());
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
}
