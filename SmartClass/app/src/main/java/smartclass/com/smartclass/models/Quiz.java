package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Quiz {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("date")
    private Date date;

    @SerializedName("duration")
    private double duration;

    @SerializedName("questions")
    private ArrayList<QuizQuestion> questions;

    /**
     * Constructor
     * @param title Quiz title
     * @param description Quiz description
     * @param date Quiz date
     * @param duration Duration of Quiz
     * @param questions List of questions
     */
    public Quiz(String title, String description, Date date, double duration, ArrayList<QuizQuestion> questions) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.questions = questions;
    }

    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }
    public Date getDate() { return this.date; }
    public double getDuration() { return this.duration; }
    public ArrayList<QuizQuestion> getQuestions() { return this.questions; }
    public int getQuestionsCount() { return this.questions.size(); }
}
