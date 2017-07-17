package smartclass.com.smartclass.goalprogress;

import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-07-17.
 */

public class GoalProgressPresenter implements GoalProgressContract.Presenter {

    private GoalProgressContract.View mView;

    public GoalProgressPresenter(GoalProgressContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.getStudentModel();
    }

    @Override
    public void onStudentLoaded(Student student) {

    }
}
