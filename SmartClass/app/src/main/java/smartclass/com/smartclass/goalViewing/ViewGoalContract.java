package smartclass.com.smartclass.goalViewing;

import smartclass.com.smartclass.models.Goal;

/**
 * Created by kevinT on 2017-07-09.
 */

public class ViewGoalContract {

    interface Presenter {
        void presentGoal(Goal goal);
    }

    interface View {
        void updateView(Goal goal);
    }
}
