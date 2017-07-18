package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.QuizQuestionOption;

/**
 * Created by kevinT on 2017-07-17.
 */

public class QuizViewContract {

    interface Presenter {
        void onCreate();
        void submitQuiz(@NonNull Quiz quiz);
    }

    interface View {
        void setupQuizUI(@NonNull Quiz quiz);
    }
}
