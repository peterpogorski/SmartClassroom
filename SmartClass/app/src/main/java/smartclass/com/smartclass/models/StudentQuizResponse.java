package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by max on 2017-06-15.
 */

public class StudentQuizResponse {

    @SerializedName("answer")
    private String answer;

    @SerializedName("correct")
    private boolean correct;

    @SerializedName("question")
    private String question;

    /**
     * Constructor
     * @param question Quiz question
     * @param answer Student's answer
     */
    public StudentQuizResponse(String question, String answer, boolean correct) {
        this.question = question;
        this.answer = answer;
        this.correct = correct;
    }

    public String getQuestionNumber() { return this.question; }
    public String getAnswer() { return this.answer; }
    public boolean isCorrect() { return this.correct; }
}
