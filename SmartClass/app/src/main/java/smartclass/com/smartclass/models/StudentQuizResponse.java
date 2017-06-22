package smartclass.com.smartclass.models;

/**
 * Created by max on 2017-06-15.
 */

public class StudentQuizResponse {


    private int questionNumber;
    private String answer;

    /**
     * Constructor
     * @param questionNumber Question number
     * @param answer Student's answer
     */
    public StudentQuizResponse(int questionNumber, String answer) {
        this.questionNumber = questionNumber;
        this.answer = answer;
    }

    public int getQuestionNumber() { return this.questionNumber; }
    public String getAnswer() { return this.answer; }
}
