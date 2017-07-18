package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Teacher extends User {

    @SerializedName("classrooms")
    private ArrayList<Classroom> classrooms;

    /**
     * Constructor
     * @param firstName Teacher first name
     * @param lastName Teacher last name
     * @param displayName Teacher display name
     */
    public Teacher(String firstName, String lastName, String displayName) {
        super(firstName, lastName, displayName, true);
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }
}
