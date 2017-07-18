package smartclass.com.smartclass.classroom.teacherGoals.goalAssignment;

import java.util.ArrayList;
import java.util.List;

import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class GoalAssignmentPresenter implements GoalAssignmentContract.Presenter {

    private GoalAssignmentContract.View mView;

    public GoalAssignmentPresenter(GoalAssignmentContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.loadStudentList();
    }

    @Override
    public void onStudentListLoaded(ArrayList<Student> studentList) {
        mView.showListOfStudents(studentList);
    }

    @Override
    public void onErrorLoading() {
        mView.showErrorToast();
    }

    @Override
    public void onStudentsAccepted(ArrayList<Student> students) {
        ArrayList<String> studentIds = new ArrayList<String>();
        for(Student student: students) {
            if(student.getAssignGoal()) {
                studentIds.add(student.getStudentId());
            }
        }
        mView.addGoalToStudents(studentIds);
    }
}
