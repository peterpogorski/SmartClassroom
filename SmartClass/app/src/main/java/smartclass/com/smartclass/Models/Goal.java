package smartclass.com.smartclass.Models;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Goal {
    private String title;
    private String description;
    private String type;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private Double weight;
    private Double mark;

    /**
     * Constructor
     * @param title Title of the goal
     * @param description Description of the goal
     * @param type The type of goal (goal, assignment, bonus)
     * @param creationDate The creation date of the goal
     * @param startDate The start date of the goal
     * @param endDate The end date of the goal
     * @param weight The weight of the goal (eg: 0.5 corresponds to a weight of 50%)
     * @param mark The maximum mark obtainable by the goal (eg: 100)
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
        this.mark = mark;
    }

    /* Getter Methods */

    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public String getType() { return  this.type; }
    public Date getCreationDate() { return this.creationDate; }
    public Date getStartDate() { return this.startDate; }
    public Date getEndDate() { return this.endDate; }
    public Double getWeight() { return this.weight; }
    public Double getMark() { return this.mark; }
}
