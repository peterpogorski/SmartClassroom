package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class AuthenticatedUser {

    @SerializedName("id")
    private String userId;

    @SerializedName("success")
    private boolean success;

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}
