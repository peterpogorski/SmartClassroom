package smartclass.com.smartclass.models;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Goal implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;

    @SerializedName("creationDate")
    private Date creationDate;

    @SerializedName("startDate")
    private Date startDate;

    @SerializedName("endDate")
    private Date endDate;

    @SerializedName("weight")
    private Double weight;

    /**
     * Constructor
     * @param title Title of the goal
     * @param description Description of the goal
     * @param type The type of goal (goal, assignment, bonus)
     * @param creationDate The creation date of the goal
     * @param startDate The start date of the goal
     * @param endDate The end date of the goal
     * @param weight The weight of the goal (eg: 0.5 corresponds to a weight of 50%)
     */
    public Goal(String title, String description, String type, Date creationDate, Date startDate,
                Date endDate, Double weight, Double mark) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weight = weight;
    }

    /* Getter Methods */

    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public String getType() { return  this.type; }
    public Date getCreationDate() { return this.creationDate; }
    public Date getStartDate() { return this.startDate; }
    public Date getEndDate() { return this.endDate; }
    public Double getWeight() { return this.weight; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(description);
        out.writeString(type);
        out.writeString(creationDate.toString());
        out.writeString(startDate.toString());
        out.writeString(endDate.toString());
        out.writeDouble(weight);
    }

    public static final Parcelable.Creator<Goal> CREATOR = new Parcelable.Creator<Goal>() {
        public Goal createFromParcel(Parcel in) {
            return new Goal(in);
        }

        public Goal[] newArray(int size) {
            return new Goal[size];
        }
    };

    private Goal(Parcel in) {
        title = in.readString();
        description = in.readString();
        type = in.readString();
        creationDate = new Date(in.readString());
        startDate = new Date(in.readString());
        endDate = new Date(in.readString());
        weight = in.readDouble();
    }
}
