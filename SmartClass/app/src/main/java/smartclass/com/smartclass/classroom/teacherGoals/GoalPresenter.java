package smartclass.com.smartclass.classroom.teacherGoals;

/**
 * Created by kevinT on 2017-06-16.
 */

public class GoalPresenter implements GoalContract.Presenter {

    private GoalContract.View mView;

    public GoalPresenter(GoalContract.View view) { mView = view; }

    @Override
    public void onCreate() {

    }
}
