package smartclass.com.smartclass.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by max on 2017-06-15.
 */

public class Quiz {
    private class Option {
        private String text;
        private Boolean correct;

        /**
         * Constructor
         * @param text Option text
         * @param correct Boolean indicating if the answer is correct
         */
        public Option(String text, Boolean correct) {
            this.text = text;
            this.correct = correct;
        }

        /**
         * Constructor for incorrect option
         * @param text Option text
         */
        public Option(String text) {
            this.text = text;
            this.correct = false;
        }

        public String getText() { return this.text; }
        private Boolean getCorrect() { return this.correct; }
    }

    private class Question {
        private String question;
        private String answer;
        private ArrayList<Option> options;

        /**
         * Constructor for short answer questions
         * @param question Question text
         * @param answer Answer text
         */
        public Question(String question, String answer){
            this.question = question;
            this.answer = answer;
        }

        /**
         * Constructor for multiple choice questions
         * @param question Question text
         * @param options List of options
         */
        public Question(String question, ArrayList<Option> options){
            this.question = question;
            this.options = options;
        }

        public String getQuestion() { return this.question; }
        public String getAnswer() { return this.answer; }
        public ArrayList<Option> getOptions() { return this.options; }
        public int getOptionsCount() { return this.options.size(); }
    }

    private String title;
    private String description;
    private Date date;
    private double duration;
    private ArrayList<Question> questions;

    /**
     * Constructor
     * @param title Quiz title
     * @param description Quiz description
     * @param date Quiz date
     * @param duration Duration of Quiz
     * @param questions List of questions
     */
    public Quiz(String title, String description, Date date, double duration, ArrayList<Question> questions) {
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
    public ArrayList<Question> getQuestions() { return this.questions; }
    public int getQuestionsCount() { return this.questions.size(); }
}
