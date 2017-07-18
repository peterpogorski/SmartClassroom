package smartclass.com.smartclass.classroom.teacherGoals.goalAssignment;

import java.util.ArrayList;
import java.util.List;

import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class GoalAssignmentContract {

    interface Presenter {
        void onCreate();
        void onStudentListLoaded(ArrayList<Student> studentList);
        void onErrorLoading();
        void onStudentsAccepted(ArrayList<Student> students);
    }

    interface View {
        void loadStudentList();
        void showErrorToast();
        void showListOfStudents(ArrayList<Student> studentList);
        void addGoalToStudents(ArrayList<String> studentIds);
    }
}
