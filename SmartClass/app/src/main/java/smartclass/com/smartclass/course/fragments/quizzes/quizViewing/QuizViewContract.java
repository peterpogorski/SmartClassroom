package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.QuizQuestionOption;

/**
 * Created by kevinT on 2017-07-17.
 */

public class QuizViewContract {

    interface Presenter {
        void onCreate();
        void startQuiz(Quiz quiz);
        void stopQuiz(Quiz quiz);
    }

    interface View {
        void updateQuizTitle(String title);
        void updateQuizDescription(String description);
        void updateQuizQuestion(String question);
        void updateAnswers(ArrayList<QuizQuestionOption> questionOptions);
    }
}
