package smartclass.com.smartclass.course.fragments.quizzes.quizViewing;

import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.demodata.UserToken;
import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.Student;
import smartclass.com.smartclass.models.StudentQuizHistory;
import smartclass.com.smartclass.models.StudentQuizResponse;

/**
 * Created by kevinT on 2017-07-17.
 */

public class QuizViewPresenter implements QuizViewContract.Presenter {

    private QuizViewContract.View mView;

    private String correctAnswer = "";

    public QuizViewPresenter(QuizViewContract.View view) {
        mView = view;
    }

    // CONTRACT METHODS

    @Override
    public void onCreate() {
        Quiz quiz = TeacherModeDataManager.getInstance().getSelectedQuiz();
        if (quiz != null) {
            mView.setupQuizUI(quiz);
        }
    }

    @Override
    public void submitQuiz(@NonNull final Quiz quiz, @NonNull final StudentQuizResponse studentQuizResponse) {
        final SmartClassService smartClassService = SmartClassRetrofit.getInstance().create(SmartClassService.class);

        // Check if quiz is still active before submitting
        Call<Quiz> getQuiz = smartClassService.getQuiz(quiz.getQuizId(), UserToken.getInstance().getTokenValue());
        getQuiz.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                Quiz loadedQuiz = response.body();
                if (loadedQuiz == null && mView != null) {
                    mView.showToastMessage("Failed to validate quiz");
                    return;
                }

                final boolean isActive = loadedQuiz.isActivated();
                int earnedMarks = isActive && studentQuizResponse.isCorrect() ? 100 : 0;

                ArrayList<StudentQuizResponse> studentQuizResponses = new ArrayList<>();
                studentQuizResponses.add(studentQuizResponse);

                StudentQuizHistory quizHistory = new StudentQuizHistory(quiz.getTitle(), earnedMarks, 0.1, studentQuizResponses, quiz);

                Call<Student> submitQuiz = smartClassService.submitQuiz(TeacherModeDataManager.getInstance().getCurrentClassroomId(),
                        UserToken.getInstance().getTokenValue(), quizHistory);
                submitQuiz.enqueue(new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        if (mView != null) {
                            if (isActive) {
                                mView.showToastMessage("Successfully submitted the quiz, your answer was "+
                                        (studentQuizResponse.isCorrect() ? "correct" : "incorrect"));
                            } else {
                                mView.showToastMessage("The quiz is no longer active.");
                            }
                            mView.dismissView();
                        }
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
                        // TODO: Handle failure
                    }
                });
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {

            }
        });
    }

    @Override
    public void setCorrectAnswer(@NonNull String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isAnswerCorrect(@NonNull String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
