package smartclass.com.smartclass.classroom.teacherGoals.goalCreation;

import java.util.Date;

import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.models.Goal;

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

    /**
     * Creates a goal using the input values from the user
     *
     * @return true if the goal was created successfully, false otherwise.
     */
    @Override
    public boolean createGoal() {
        if (mView.highlightEmptyFields()) {
            return false;
        }

        String title = mView.getTitleInput();
        String description = mView.getDescriptionInput();
        String type = mView.getType();
        double weight = mView.getWeightInput();
        double marks = mView.getMarksInput();

        Date creationDate = new Date();
        // TODO: Handle null dates
        // TODO: Handle the case when end date is before the start date
        Date startDate = mView.getStartDate();
        Date endDate = mView.getEndDate();

        Goal goal = new Goal(title, description, type, creationDate, startDate, endDate, weight, marks);
        TeacherModeDataManager.getInstance().addGoal(goal);

        return true;
    }
}
