package smartclass.com.smartclass.classroom;

/**
 * Created by kevinT on 2017-06-15.
 */

public class ClassroomContract {

    interface Presenter {
        void onCreate();
        void onStudentsTabSelected();
        void onGoalsTabSelected();
        void onQuizTabSelected();
        void onAttendanceTabSelected();
    }

    interface View {
        void disableStudentsTab();
        void enableStudentsTab();
        void disableGoalsTab();
        void enableGoalsTab();
        void disableQuizTab();
        void enableQuizTab();
        void disableAttendanceTab();
        void enableAttendanceTab();
    }

}
