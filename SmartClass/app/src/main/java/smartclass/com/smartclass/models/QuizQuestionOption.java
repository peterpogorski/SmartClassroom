package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by max on 2017-06-15.
 */

public class QuizQuestionOption {

    @SerializedName("text")
    private String text;

    @SerializedName("correct")
    private Boolean correct;

    /**
     * Constructor
     * @param text Option text
     * @param correct Boolean indicating if the answer is correct
     */
    public QuizQuestionOption(String text, Boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    /**
     * Constructor for incorrect option
     * @param text Option text
     */
    public QuizQuestionOption(String text) {
        this.text = text;
        this.correct = false;
    }

    public String getText() { return this.text; }
    private Boolean getCorrect() { return this.correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
}
