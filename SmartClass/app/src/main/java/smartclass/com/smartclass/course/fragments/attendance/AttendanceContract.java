package smartclass.com.smartclass.course.fragments.attendance;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Presence;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-07-18.
 */

public class AttendanceContract {

    interface Presenter {
        void startAttendancePoll();
        void stopAttendancePoll();
        void checkForAttendancePoll();
        void respondToAttendancePoll();
    }

    interface View {
        void showToastMessage(@NonNull String message);
        void showListOfPresentStudents(ArrayList<Presence> presentStudents);
        void updateStudentAttendanceStatus(boolean present);
    }
}
