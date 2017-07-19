package smartclass.com.smartclass.course.fragments.attendance;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.models.Presence;
import smartclass.com.smartclass.models.PresenceStudent;
import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-06-13.
 */

public class AttendanceFragment extends Fragment implements AttendanceContract.View {

    private AttendanceContract.Presenter mPresenter;

    private Button attendanceButton;
    private TextView attendancePollStatus;
    private TextView attendanceStatus;
    private LinearLayout separator;
    private ListView presentStudentsListView;

    private boolean polling = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.attendance_view, container, false);

        attendanceButton = (Button) view.findViewById(R.id.attendance_button);
        attendancePollStatus = (TextView) view.findViewById(R.id.attendance_poll_status);
        attendanceStatus = (TextView) view.findViewById(R.id.attendance_status);
        separator = (LinearLayout) view.findViewById(R.id.separator);
        presentStudentsListView = (ListView) view.findViewById(R.id.present_students_listview);

        mPresenter = new AttendancePresenter(this);

        if (TeacherModeDataManager.getInstance().isTeacherModeEnabled()) {
            attendanceButton.setText("Start Attendance Poll");
            attendanceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    polling = !polling;
                    if (polling) {
                        attendanceButton.setText("Finish Polling");
                        attendancePollStatus.setText("Active");
                        attendancePollStatus.setTextColor(getResources().getColor(R.color.green));

                        if (mPresenter != null) {
                            mPresenter.startAttendancePoll();
                        }
                    } else {
                        attendanceButton.setText("Start Attendance Poll");
                        attendancePollStatus.setText("Inactive");
                        attendancePollStatus.setTextColor(getResources().getColor(R.color.red));

                        if (mPresenter != null) {
                            mPresenter.stopAttendancePoll();
                        }
                    }
                }
            });
            view.findViewById(R.id.attendance_status_label).setVisibility(View.GONE);
            view.findViewById(R.id.attendance_status).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.attendance_status_layout).setVisibility(View.GONE);
            attendanceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.respondToAttendancePoll();
                }
            });
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!TeacherModeDataManager.getInstance().isTeacherModeEnabled()) {
            inflater.inflate(R.menu.menu_attendance, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Handles action bar button clicks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                // TODO: Check for an active attendance poll
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // CONTRACT METHODS


    @Override
    public void showToastMessage(@NonNull String message) {
        Activity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showListOfPresentStudents(ArrayList<Presence> presentStudents) {
        Activity activity = getActivity();
        if (activity == null) { return; }

        if (presentStudents.size() == 0) {
            Toast.makeText(activity, "No students are present", Toast.LENGTH_SHORT).show();
            return;
        }

        separator.setVisibility(View.VISIBLE);
        presentStudentsListView.setVisibility(View.VISIBLE);
        List<String> studentNames = new ArrayList<>();
        for (int i = 0; i < presentStudents.size(); i++) {
            PresenceStudent
                    student = presentStudents.get(i).getStudent();
            String studentFullname = student.getFirstName()+" "+student.getLastName();
            studentNames.add(studentFullname);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, studentNames);
        presentStudentsListView.setAdapter(adapter);
    }

    @Override
    public void updateStudentAttendanceStatus(boolean present) {
        if (present) {
            attendanceStatus.setText("Here");
            attendanceStatus.setTextColor(getResources().getColor(R.color.green));
        } else {
            attendanceStatus.setText("Away");
            attendanceStatus.setTextColor(getResources().getColor(R.color.red));
        }
    }
}
