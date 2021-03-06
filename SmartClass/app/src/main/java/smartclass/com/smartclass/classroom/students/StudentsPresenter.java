package smartclass.com.smartclass.classroom.students;

import java.util.ArrayList;

import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-16.
 */

public class StudentsPresenter implements StudentsContract.Presenter {

    private StudentsContract.View mView;

    public StudentsPresenter(StudentsContract.View view) {
        mView = view;
    }

    /** Contract methods **/

    @Override
    public void onCreate() {

    }

    @Override
    public void onStudentSelected(Student student) {
        // TODO: Display goals for the selected student
    }

    @Override
    public void onStudentListLoaded(ArrayList<Student> students) {
        mView.hideLoading();
        mView.initStudentsList(students);
    }
}
