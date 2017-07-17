package smartclass.com.smartclass.course.fragments.quizzes.quizCreation;

import android.support.annotation.NonNull;

import smartclass.com.smartclass.models.Quiz;

/**
 * Created by kevinT on 2017-07-16.
 */

public class QuizCreationContract {

    interface Presenter {
        void onCreate(Quiz quiz);
        boolean createQuiz();
    }

    interface View {
        String getTitleInput();
        String getDescriptionInput();
        String getQuestion();
        String getAnswer1();
        String getAnswer2();
        String getAnswer3();
        String getAnswer4();
        String getAnswer5();
        String getCorrectAnswer();
        void presetInputFields(@NonNull Quiz quiz);
        boolean highlightEmptyFields();
    }
}
