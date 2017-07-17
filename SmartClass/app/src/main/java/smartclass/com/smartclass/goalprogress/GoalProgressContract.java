package smartclass.com.smartclass.goalprogress;

import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-07-17.
 */

public class GoalProgressContract {

    interface Presenter {
        void onCreate();
        void onStudentLoaded(Student student);
    }

    interface View {
        void getStudentModel();
        void populatePieChart();
    }
}
