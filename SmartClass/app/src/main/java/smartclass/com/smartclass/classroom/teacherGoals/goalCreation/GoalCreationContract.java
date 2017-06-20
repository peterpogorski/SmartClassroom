package smartclass.com.smartclass.classroom.teacherGoals.goalCreation;

import java.util.Date;

/**
 * Created by kevinT on 2017-06-18.
 */

public class GoalCreationContract {

    interface Presenter {
        void onCreate();
        boolean createGoal();
    }

    interface View {
        String getTitleInput();
        String getDescriptionInput();
        String getType();
        Double getWeightInput();
        Double getMarksInput();
        Date getStartDate();
        Date getEndDate();
        boolean highlightEmptyFields();
        void displaySnackbar(String message);
    }
}
