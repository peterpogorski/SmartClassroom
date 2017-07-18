package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.QuizQuestionOption;
import smartclass.com.smartclass.models.StudentQuizResponse;

/**
 * Created by kevinT on 2017-07-17.
 */

public class QuizViewContract {

    interface Presenter {
        void onCreate();
        void submitQuiz(@NonNull final Quiz quiz, @NonNull final StudentQuizResponse response);
        void setCorrectAnswer(String correctAnswer);
        boolean isAnswerCorrect(String answer);
    }

    interface View {
        void setupQuizUI(@NonNull Quiz quiz);
        void dismissView();
        void showToastMessage(@NonNull String message);
    }
}
