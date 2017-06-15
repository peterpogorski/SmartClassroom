package smartclass.com.smartclass.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Teacher {
    private Facebook facebook;
    private String firstName;
    private String lastName;
    private String displayName;
    private Date birthday;
    private ArrayList<Classroom> classrooms;

    /**
     * Constructor
     * @param facebook Facebook details
     * @param firstName Teacher first name
     * @param lastName Teacher last name
     * @param displayName Teacher display name
     * @param birthday Teacher birthday
     * @param classrooms Classrooms of which the teacher teaches
     */
    public Teacher(Facebook facebook, String firstName, String lastName, String displayName,
                   Date birthday, ArrayList<Classroom> classrooms) {
        this.facebook = facebook;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.birthday = birthday;
        this.classrooms = classrooms;
    }

    public Facebook getFacebook() { return this.facebook; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getDisplayName() { return this.displayName; }
    public Date getBirthday() { return this.birthday; }
    public ArrayList<Classroom> getClassrooms() {return this.classrooms; }
}
