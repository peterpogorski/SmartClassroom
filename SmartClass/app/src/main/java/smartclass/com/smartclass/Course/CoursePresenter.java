package smartclass.com.smartclass.Course;

/**
 * Created by peterpogorski on 2017-06-13.
 */

public class CoursePresenter implements CourseContract.Presenter {

    private CourseContract.View mView;

    public CoursePresenter(CourseContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.enableProgressTab();
    }

    @Override
    public void onProgressTabSelected() {
        mView.enableProgressTab();
        mView.diableAttendanceTab();
        mView.disableQuizTab();
    }

    @Override
    public void onQuizTabSelected() {
        mView.enableQuizTab();
        mView.disableProgressTab();
        mView.diableAttendanceTab();
    }

    @Override
    public void onAttendanceTabSelected() {
        mView.enableAttendaceTab();
        mView.disableProgressTab();;
        mView.disableQuizTab();
    }
}
