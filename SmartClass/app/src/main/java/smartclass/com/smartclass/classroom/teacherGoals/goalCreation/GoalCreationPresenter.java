package smartclass.com.smartclass.classroom.teacherGoals.goalCreation;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.demodata.UserToken;
import smartclass.com.smartclass.models.Goal;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-18.
 */

public class GoalCreationPresenter implements GoalCreationContract.Presenter {

    private GoalCreationContract.View mView;

    private Retrofit mRetrofit;

    public GoalCreationPresenter(GoalCreationContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mRetrofit = SmartClassRetrofit.getInstance();
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

        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);

        Call<Goal> createGoal = smartClassService.createGoal(goal, UserToken.getInstance().getTokenValue());
        createGoal.enqueue(new Callback<Goal>() {
            @Override
            public void onResponse(Call<Goal> call, Response<Goal> response) {
                mView.assignToStudents(response.body());
            }

            @Override
            public void onFailure(Call<Goal> call, Throwable t) {
                // TODO: Handle failure
            }
        });

        return true;
    }
}
