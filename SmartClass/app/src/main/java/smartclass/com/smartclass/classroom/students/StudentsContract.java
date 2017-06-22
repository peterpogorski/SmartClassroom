package smartclass.com.smartclass.classroom.students;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-16.
 */

public class StudentsContract {

    interface Presenter {
        void onCreate();
        void onStudentSelected(Student student);
        void onStudentListLoaded(ArrayList<Student> students);
    }

    interface View {
        void addStudent(Student student);
        void initStudentsList(ArrayList<Student> students);
        void hideLoading();
    }
}
