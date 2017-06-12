package smartclass.com.smartclass;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseListPresenter implements CourseContract.Presenter {

    private CourseContract.View mCourseView;

    public CourseListPresenter(CourseContract.View courseView) {
        mCourseView = courseView;
    }

    @Override
    public void onCourseSelected(Course course) {
        mCourseView.showCourse();
    }
}
