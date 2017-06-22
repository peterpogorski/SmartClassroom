package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class StudentActivityLog {

    @SerializedName("date")
    private Date date;

    @SerializedName("log")
    private String log;

    @SerializedName("weight")
    private double weight;

    @SerializedName("mark")
    private double grade;

    /**
     * Constructor
     * @param date Entry date
     * @param log Entry text
     * @param weight Weight obtained
     */
    public StudentActivityLog(Date date, String log, double weight, double grade) {
        this.date = date;
        this.log = log;
        this.weight = weight;
        this.grade = grade;
    }

    public Date getDate() { return this.date; }
    public String getLog() { return this.log; }
    public double getWeight() { return this.weight; }
    public double getGrade() { return this.grade; }
}
