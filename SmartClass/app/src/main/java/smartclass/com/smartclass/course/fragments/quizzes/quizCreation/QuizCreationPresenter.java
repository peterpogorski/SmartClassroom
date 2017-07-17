package smartclass.com.smartclass.course.fragments.quizzes.quizCreation;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.QuizQuestion;
import smartclass.com.smartclass.models.QuizQuestionOption;

/**
 * Created by kevinT on 2017-07-16.
 */

public class QuizCreationPresenter implements QuizCreationContract.Presenter {

    private QuizCreationContract.View mView;
    private Quiz quiz;

    public QuizCreationPresenter(QuizCreationContract.View view) { mView = view; }

    @Override
    public void onCreate(@Nullable Quiz quiz) {
        if (quiz != null) {
            this.quiz = quiz;
            mView.presetInputFields(quiz);
        }
    }

    @Override
    public boolean createQuiz() {
        if (mView.highlightEmptyFields()) {
            return false;
        }
        String title = mView.getTitleInput();
        String description = mView.getDescriptionInput();
        String question = mView.getQuestion();
        Date date = new Date();
        double duration = 100;
        ArrayList<QuizQuestionOption> questionOptions = new ArrayList<>();

        QuizQuestionOption option1 = new QuizQuestionOption(mView.getAnswer1());
        QuizQuestionOption option2 = new QuizQuestionOption(mView.getAnswer2());
        QuizQuestionOption option3 = new QuizQuestionOption(mView.getAnswer3());
        QuizQuestionOption option4 = new QuizQuestionOption(mView.getAnswer4());
        QuizQuestionOption option5 = new QuizQuestionOption(mView.getAnswer5());

        int correctAnswer = Integer.valueOf(mView.getCorrectAnswer());
        switch (correctAnswer) {
            case 1:
                option1.setCorrect(true);
                break;
            case 2:
                option2.setCorrect(true);
                break;
            case 3:
                option3.setCorrect(true);
                break;
            case 4:
                option4.setCorrect(true);
                break;
            default:
                option5.setCorrect(true);
                break;
        }

        questionOptions.add(option1);
        questionOptions.add(option2);
        questionOptions.add(option3);
        questionOptions.add(option4);
        questionOptions.add(option5);

        // Note: Only going to do single quiz questions for now.
        QuizQuestion quizQuestion = new QuizQuestion(question, questionOptions);
        ArrayList<QuizQuestion> questions = new ArrayList<>();
        questions.add(quizQuestion);

        Quiz quiz = new Quiz(title, description, date, duration, questions);
        TeacherModeDataManager.getInstance().addQuiz(quiz);

        SmartClassService smartClassService = SmartClassRetrofit.getInstance().create(SmartClassService.class);

        Call<Quiz> createQuiz = smartClassService.createQuiz(TeacherModeDataManager.getInstance().getCurrentClassroomId(), quiz);
        createQuiz.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                // TODO: Do something, perhaps finish activity here
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                // TODO: Handle failure
            }
        });

        return true;
    }
}
