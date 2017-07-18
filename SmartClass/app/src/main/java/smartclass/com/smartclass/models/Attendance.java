package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kevinT on 2017-07-18.
 */

public class Attendance {

    @SerializedName("_id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("classroom")
    private String classroomId;

    @SerializedName("presences")
    private ArrayList<Student> presentStudents;

    @SerializedName("activated")
    private boolean isActivated;

    @SerializedName("date")
    private Date date;

    // For students responding to an attendance poll
    @SerializedName("accepted")
    private boolean accepted;

    /**
     * Constructor
     *
     * @param title
     * @param classroomId
     */
    public Attendance(String title, String classroomId, Date date) {
        this.title = title;
        this.classroomId = classroomId;
        this.isActivated = false;
        this.date = date;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getClassroomId() { return classroomId; }
    public ArrayList<Student> getPresentStudentsList() { return presentStudents; }
    public boolean isActive() { return isActivated; }
    public void setActiveStatus(boolean isActive) { this.isActivated = isActive; }
    private boolean getAttendanceAcceptance() { return accepted; }
}
