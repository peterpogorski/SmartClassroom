package smartclass.com.smartclass.course.fragments.attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import smartclass.com.smartclass.R;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;

/**
 * Created by peterpogorski on 2017-06-13.
 */

public class AttendanceFragment extends Fragment {

    private Button attendanceButton;
    private TextView attendancePollStatus;

    private boolean polling = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.attendance_view, container, false);

        attendanceButton = (Button) view.findViewById(R.id.attendance_button);
        attendancePollStatus = (TextView) view.findViewById(R.id.attendance_poll_status);
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
                    } else {
                        attendanceButton.setText("Start Attendance Poll");
                        attendancePollStatus.setText("Inactive");
                        attendancePollStatus.setTextColor(getResources().getColor(R.color.red));
                    }
                }
            });
            view.findViewById(R.id.attendance_status_label).setVisibility(View.GONE);
            view.findViewById(R.id.attendance_status).setVisibility(View.GONE);
        }

        return view;
    }
}
