package smartclass.com.smartclass.models;

import android.hardware.camera2.params.Face;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;


/**
 * User Model.
 *
 * Created by kevinT on 2017-06-15.
 */

public class User {

    private boolean teacherMode;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("displayName")
    private String displayName;


    /*
    @SerializedName("classrooms")
    private ArrayList<Classroom> classrooms;
    */

    private Facebook facebook;
    private Date birthday;

    public User(Facebook facebook, String firstName, String lastName, String displayName,
                   Date birthday, ArrayList<Classroom> classrooms, boolean teacherMode) {
        this.facebook = facebook;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.birthday = birthday;
        //this.classrooms = classrooms;
        this.teacherMode = teacherMode;
    }

    /** Getter functions **/

    public Facebook getFacebook() { return this.facebook; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getDisplayName() { return this.displayName; }
    public Date getBirthday() { return this.birthday; }
    //public ArrayList<Classroom> getClassrooms() { return this.classrooms; }
    public boolean isTeacherMode() { return teacherMode; }
    //public int getClassroomsCount() { return this.classrooms.size(); }
}
