package smartclass.com.smartclass.models;

/**
 * Created by max on 2017-06-15.
 */

public class Facebook {
    private String id;
    private String profilePicture;
    // TODO: Add other fields from facebook response

    /**
     * Constructor
     * @param id Facebook profile id
     * @param profilePicture Facebook Profile picture url
     */
    public Facebook(String id, String profilePicture) {
        this.id = id;
        this.profilePicture = profilePicture;
    }

    public String getId() { return this.id; }
    public String getProfilePicture() { return this.profilePicture; }
}
