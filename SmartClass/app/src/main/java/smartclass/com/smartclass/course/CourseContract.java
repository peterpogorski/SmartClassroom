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
    }

    interface View {
        void disableProgressTab();
        void enableProgressTab();
        void disableQuizTab();
        void enableQuizTab();
        void diableAttendanceTab();
        void enableAttendaceTab();
    }

}
