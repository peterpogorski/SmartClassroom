package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class LoginUser {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("userType")
    private String userType;

    public LoginUser(String username, String password, boolean isTeacher) {
        this.username = username;
        this.password = password;

        if(isTeacher) {
            userType = "teacher";
        } else {
            userType = "student";
        }
    }
}
