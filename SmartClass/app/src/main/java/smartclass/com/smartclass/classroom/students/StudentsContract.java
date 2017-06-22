package smartclass.com.smartclass.classroom.students;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-06-16.
 */

public class StudentsContract {

    interface Presenter {
        void onCreate(ArrayList<Student> students);
        void onStudentSelected(Student student);
    }

    interface View {
        void addStudent(Student student);
        void initStudentsList(ArrayList<Student> students);
    }
}
