package smartclass.com.smartclass.classroom.teacherGoals;

/**
 * Created by kevinT on 2017-06-16.
 */

public class TeacherGoalPresenter implements TeacherGoalContract.Presenter {

    private TeacherGoalContract.View mView;

    public TeacherGoalPresenter(TeacherGoalContract.View view) { mView = view; }

    @Override
    public void onCreate() {

    }
}
