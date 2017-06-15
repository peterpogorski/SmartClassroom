package smartclass.com.smartclass.models;

import java.util.ArrayList;

/**
 * Created by max on 2017-06-15.
 */

public class StudentQuizHistory {
    private String title;
    private double mark;
    private double weight;
    private ArrayList<StudentQuizResponse> results;
    private Quiz quiz;

    /**
     * Constructor
     * @param title Quiz title
     * @param mark Student's mark on the quiz
     * @param weight Weight of quiz
     * @param results Students answers
     * @param quiz Quiz details and questions
     */
    public StudentQuizHistory(String title, double mark, double weight,
                       ArrayList<StudentQuizResponse> results, Quiz quiz) {
        this.title = title;
        this.mark = mark;
        this.weight = weight;
        this.results = results;
        this.quiz = quiz;
    }

    public String getTitle() { return this.title; }
    public double getMark() { return this.mark; }
    public double getWeight() { return this.weight; }
    public ArrayList<StudentQuizResponse> getResults() { return this.results; }
    public int getResultsCount() { return this.results.size(); }
    public Quiz getQuiz() { return this.quiz; }
}
