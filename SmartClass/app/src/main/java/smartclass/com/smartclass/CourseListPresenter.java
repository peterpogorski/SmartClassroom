package smartclass.com.smartclass;

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
        mCourseView.showCourse();
    }
}
