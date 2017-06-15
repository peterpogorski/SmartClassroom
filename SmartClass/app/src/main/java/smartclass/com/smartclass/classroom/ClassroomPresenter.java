package smartclass.com.smartclass.classroom;

/**
 * Created by kevinT on 2017-06-15.
 */

public class ClassroomPresenter implements ClassroomContract.Presenter {

    private ClassroomContract.View mView;

    public ClassroomPresenter(ClassroomContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.enableStudentsTab();
    }

    @Override
    public void onStudentsTabSelected() {
        mView.enableStudentsTab();
        mView.disableAttendanceTab();
        mView.disableQuizTab();
    }

    @Override
    public void onQuizTabSelected() {
        mView.enableQuizTab();
        mView.disableStudentsTab();
        mView.disableAttendanceTab();
    }

    @Override
    public void onAttendanceTabSelected() {
        mView.enableAttendanceTab();
        mView.disableStudentsTab();
        mView.disableQuizTab();
    }
}
