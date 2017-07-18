package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by peterpogorski on 2017-07-17.
 */

public class GoalCreate {

    @SerializedName("title")
    private String title;

    @SerializedName("startDate")
    private Date startDate;

    @SerializedName("goal")
    private String goal;

    public GoalCreate(String title, Date startDate, String goal) {
        this.title = title;
        this.startDate = startDate;
        this.goal = goal;
    }
}
