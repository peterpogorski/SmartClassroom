package smartclass.com.smartclass.course;

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
        mView.disableAttendanceTab();
        mView.disableQuizTab();
    }

    @Override
    public void onQuizTabSelected() {
        mView.enableQuizTab();
        mView.disableProgressTab();
        mView.disableAttendanceTab();
    }

    @Override
    public void onAttendanceTabSelected() {
        mView.enableAttendanceTab();
        mView.disableProgressTab();
        mView.disableQuizTab();
    }
}
