package smartclass.com.smartclass.course.fragments.attendance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.demodata.UserToken;
import smartclass.com.smartclass.models.Attendance;
import smartclass.com.smartclass.models.Student;

/**
 * Created by kevinT on 2017-07-18.
 */

public class AttendancePresenter implements  AttendanceContract.Presenter {

    private AttendanceContract.View mView;

    public AttendancePresenter(AttendanceContract.View view) { this.mView = view; }

    // CONTRACT METHODS

    @Override
    public void startAttendancePoll() {
        SmartClassService smartClassService = SmartClassRetrofit.getInstance().create(SmartClassService.class);

        Call<Attendance> startAttendancePoll = smartClassService.startAttendancePoll(UserToken.getInstance().getClassroomId(),
                UserToken.getInstance().getTokenValue());
        startAttendancePoll.enqueue(new Callback<Attendance>() {
            @Override
            public void onResponse(Call<Attendance> call, Response<Attendance> response) {
                Attendance currentAttendance = response.body();
                if (currentAttendance != null) {
                    TeacherModeDataManager.getInstance().setCurrentAttendanceId(currentAttendance.getId());
                }
            }

            @Override
            public void onFailure(Call<Attendance> call, Throwable t) {
                // TODO: Handle failure
            }
        });
    }

    @Override
    public void stopAttendancePoll() {
        SmartClassService smartClassService = SmartClassRetrofit.getInstance().create(SmartClassService.class);

        Call<Attendance> stopAttendancePoll = smartClassService.stopAttendancePoll(UserToken.getInstance().getClassroomId(),
                TeacherModeDataManager.getInstance().getCurrentAttendanceId(), UserToken.getInstance().getTokenValue());
        stopAttendancePoll.enqueue(new Callback<Attendance>() {
            @Override
            public void onResponse(Call<Attendance> call, Response<Attendance> response) {
                Attendance attendance = response.body();
                if (attendance != null) {
                    ArrayList<Student> presentStudents = attendance.getPresentStudentsList();
                    if (mView !=null) {
                        mView.showListOfPresentStudents(presentStudents);
                    }
                }
            }

            @Override
            public void onFailure(Call<Attendance> call, Throwable t) {
                // TODO: Handle failure
            }
        });
    }

    @Override
    public void checkForAttendancePoll() {

    }

    @Override
    public void respondToAttendancePoll() {
        SmartClassService smartClassService = SmartClassRetrofit.getInstance().create(SmartClassService.class);

        Call<Attendance> respondToAttendancePoll = smartClassService.respondToAttendancePoll(UserToken.getInstance().getClassroomId(),
                UserToken.getInstance().getUserId(), UserToken.getInstance().getTokenValue());
        respondToAttendancePoll.enqueue(new Callback<Attendance>() {
            @Override
            public void onResponse(Call<Attendance> call, Response<Attendance> response) {
                Attendance attendance = response.body();
                if (mView == null) { return; }
                if (attendance != null) {
                    mView.updateStudentAttendanceStatus(attendance.getAttendanceAcceptance());
                } else {
                    mView.showToastMessage("Failed to respond to attendance poll");
                }
            }

            @Override
            public void onFailure(Call<Attendance> call, Throwable t) {
                // TODO: Handle failure
            }
        });
    }
}
