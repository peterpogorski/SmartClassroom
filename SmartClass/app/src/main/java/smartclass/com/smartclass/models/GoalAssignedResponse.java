package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by peterpogorski on 2017-07-18.
 */

public class GoalAssignedResponse {

    @SerializedName("accepted")
    private boolean accepted;

    public boolean isAccepted() {
        return accepted;
    }
}
