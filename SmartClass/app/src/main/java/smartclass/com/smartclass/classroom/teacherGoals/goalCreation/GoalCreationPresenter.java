package smartclass.com.smartclass.classroom.teacherGoals.goalCreation;

/**
 * Created by kevinT on 2017-06-18.
 */

public class GoalCreationPresenter implements GoalCreationContract.Presenter {

    private GoalCreationContract.View mView;

    public GoalCreationPresenter(GoalCreationContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {

    }
}
