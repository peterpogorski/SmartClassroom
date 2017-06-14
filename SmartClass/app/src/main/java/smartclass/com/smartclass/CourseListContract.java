package smartclass.com.smartclass;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseListContract {

    interface Presenter {
        void onCourseSelected(Course course);
    }

    interface View {
        void showCourse();
    }
}
