package smartclass.com.smartclass.courseList;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Classroom;
import smartclass.com.smartclass.models.Course;

/**
 * Created by peterpogorski on 2017-06-12.
 */

public class CourseListContract {

    interface Presenter {
        void onCreate();
        void onCourseSelected(Classroom classroom);
        void onLogoutClicked();
        void onLogoutConfirmed();
        void onClassroomsLoaded(ArrayList<Classroom> classrooms);
    }

    interface View {
        void showCourse(String courseName, String classroomID);
        void confirmLogout();
        void clearCredentials();
        void showLoginScreen();
        void loadClassrooms();
        void displayClassrooms(ArrayList<Classroom> classrooms);
    }
}
