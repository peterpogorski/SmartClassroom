package smartclass.com.smartclass.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Teacher extends User {
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
        super(facebook, firstName, lastName, displayName, birthday, classrooms, true);
    }
}
