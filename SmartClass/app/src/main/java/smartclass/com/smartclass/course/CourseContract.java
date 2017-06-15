package smartclass.com.smartclass.course;

/**
 * Created by peterpogorski on 2017-06-13.
 */

public class CourseContract {

    interface Presenter {
        void onCreate();
        void onProgressTabSelected();
        void onQuizTabSelected();
        void onAttendanceTabSelected();
        void onStudentsTabSelected();
    }

    interface View {
        void initialDisplay(boolean teacherMode);
        void disableProgressTab();
        void enableProgressTab();
        void disableQuizTab();
        void enableQuizTab();
        void disableAttendanceTab();
        void enableAttendanceTab();
        void disableStudentsTab();
        void enableStudentsTab();
    }

}
