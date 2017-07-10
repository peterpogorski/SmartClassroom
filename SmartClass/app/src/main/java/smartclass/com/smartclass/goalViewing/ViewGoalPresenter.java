package smartclass.com.smartclass.goalViewing;

import smartclass.com.smartclass.models.Goal;

/**
 * Created by kevinT on 2017-07-10.
 */

public class ViewGoalPresenter implements ViewGoalContract.Presenter {

    private ViewGoalContract.View mView;

    public ViewGoalPresenter(ViewGoalContract.View view) {
        mView = view;
    }

    @Override
    public void presentGoal(Goal goal) {
        mView.updateView(goal);
    }
}
