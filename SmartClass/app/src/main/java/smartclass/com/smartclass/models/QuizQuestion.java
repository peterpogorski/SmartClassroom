package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by max on 2017-06-15.
 */

public class QuizQuestion {

    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;

    @SerializedName("options")
    private ArrayList<QuizQuestionOption> options;

    /**
     * Constructor for short answer questions
     * @param question Question text
     * @param answer Answer text
     */
    public QuizQuestion(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    /**
     * Constructor for multiple choice questions
     * @param question Question text
     * @param options List of options
     */
    public QuizQuestion(String question, ArrayList<QuizQuestionOption> options){
        this.question = question;
        this.options = options;
    }

    public String getQuestion() { return this.question; }
    public String getAnswer() { return this.answer; }
    public ArrayList<QuizQuestionOption> getOptions() { return this.options; }
    public int getOptionsCount() { return this.options.size(); }
}
