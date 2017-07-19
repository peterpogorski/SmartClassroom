package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kevinT on 2017-07-18.
 */

public class Presence {

    @SerializedName("student")
    private Student student;

    @SerializedName("present")
    private boolean present;

    /**
     * Constructor
     * @param student
     */
    public Presence(Student student) {
        this.student = student;
        this.present = false;
    }

    public Student getStudent() { return student; }
    public boolean getAttendanceStatus() { return present; }
}
