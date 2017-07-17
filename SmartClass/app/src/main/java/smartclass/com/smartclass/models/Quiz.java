package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Quiz {

    @SerializedName("_id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("createdDate")
    private Date date;

    @SerializedName("duration")
    private double duration;

    @SerializedName("questions")
    private ArrayList<QuizQuestion> questions;

    @SerializedName("activated")
    private boolean activated;

    /**
     * Constructor
     * @param title Quiz title
     * @param description Quiz description
     * @param date Quiz date
     * @param duration Duration of Quiz
     * @param questions List of questions
     */
    public Quiz(String title, String description, Date date, double duration, ArrayList<QuizQuestion> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.questions = questions;
        // Quizzes are not activated when created by default
        this.activated = false;
    }

    public String getQuizId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public Date getDate() { return this.date; }
    public double getDuration() { return this.duration; }
    public ArrayList<QuizQuestion> getQuestions() { return this.questions; }
    public int getQuestionsCount() { return this.questions.size(); }
    public boolean isActivated() { return activated; }
}
