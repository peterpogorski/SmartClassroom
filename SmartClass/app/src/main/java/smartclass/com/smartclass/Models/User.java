package smartclass.com.smartclass.models;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * User Model.
 *
 * Created by kevinT on 2017-06-15.
 */

public class User {

    // TODO: Update with other necessary properties

    private String firstName;
    private String lastName;
    private boolean teacherMode;
    private String profilePictureUrl;

    /**
     * Constructor
     */
    public User(@NonNull String firstName, @NonNull String lastName,
                boolean teacherMode, @Nullable String profilePictureUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacherMode = teacherMode;
        this.profilePictureUrl = profilePictureUrl;
    }

    /** Getter functions **/

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public boolean isTeacherModeEnabled() { return teacherMode; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
}
