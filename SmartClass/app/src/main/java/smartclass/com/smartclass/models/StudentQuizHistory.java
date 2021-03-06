package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by max on 2017-06-15.
 */

public class StudentQuizHistory {

    @SerializedName("title")
    private String title;

    @SerializedName("mark")
    private double mark;

    @SerializedName("weight")
    private double weight;

    @SerializedName("results")
    private ArrayList<StudentQuizResponse> results;

    @SerializedName("quiz")
    private String quizId;

    /**
     * Constructor
     * @param title Quiz title
     * @param mark Student's mark on the quiz
     * @param weight Weight of quiz
     * @param results Students answers
     * @param quizId Quiz details and questions
     */
    public StudentQuizHistory(String title, double mark, double weight,
                       ArrayList<StudentQuizResponse> results, String quizId) {
        this.title = title;
        this.mark = mark;
        this.weight = weight;
        this.results = results;
        this.quizId = quizId;
    }

    public String getTitle() { return this.title; }
    public double getMark() { return this.mark; }
    public double getWeight() { return this.weight; }
    public ArrayList<StudentQuizResponse> getResults() { return this.results; }
    public int getResultsCount() { return this.results.size(); }
    public String getQuizId() { return this.quizId; }
}
