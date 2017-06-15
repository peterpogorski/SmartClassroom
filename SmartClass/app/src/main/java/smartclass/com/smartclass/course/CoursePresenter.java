package smartclass.com.smartclass.course;

/**
 * Created by peterpogorski on 2017-06-13.
 */

public class CoursePresenter implements CourseContract.Presenter {

    private boolean teacherMode = false;

    private CourseContract.View mView;

    public CoursePresenter(CourseContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.initialDisplay(teacherMode);
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
        mView.disableAttendanceTab();
        if (teacherMode) {
            mView.disableStudentsTab();
        } else {
            mView.disableProgressTab();
        }
    }

    @Override
    public void onAttendanceTabSelected() {
        mView.enableAttendanceTab();
        mView.disableQuizTab();
        if (teacherMode) {
            mView.disableStudentsTab();
        } else {
            mView.disableProgressTab();
        }
    }

    @Override
    public void onStudentsTabSelected() {
        mView.enableStudentsTab();
        mView.disableAttendanceTab();
        mView.disableQuizTab();
    }
}
