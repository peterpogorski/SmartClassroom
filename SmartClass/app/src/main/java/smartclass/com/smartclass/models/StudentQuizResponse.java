package smartclass.com.smartclass.models;

/**
 * Created by max on 2017-06-15.
 */

public class StudentQuizResponse {


    private String question;
    private String answer;
    private boolean correct;

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
