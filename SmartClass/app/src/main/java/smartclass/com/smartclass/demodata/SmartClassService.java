package smartclass.com.smartclass.demodata;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import smartclass.com.smartclass.models.AssignGoalStudents;
import smartclass.com.smartclass.models.AuthenticatedUser;
import smartclass.com.smartclass.models.Goal;
import smartclass.com.smartclass.models.GoalAssignedResponse;
import smartclass.com.smartclass.models.LoginUser;
import smartclass.com.smartclass.models.Quiz;
import smartclass.com.smartclass.models.Student;
import smartclass.com.smartclass.models.StudentQuizHistory;
import smartclass.com.smartclass.models.Teacher;

/**
 * Created by peterpogorski on 2017-06-21.
 */

public interface SmartClassService {

    @POST("authenticate")
    Call<AuthenticatedUser> authenticateUser(@Body LoginUser loginUser);

    @GET("students")
    Call<ArrayList<Student>> getStudents();

    @GET("students/{id}")
    Call<Student> getStudent(@Path("id") String studentId, @Header("token") String token);

    @GET("teachers/{id}")
    Call<Teacher> getTeacher(@Path("id") String teacherId, @Header("token") String token);
  
    @POST("students")
    Call<Student> createStudent(@Body Student student);

    @POST("students/{id}/goals/{goaldId}/activityLogs")
    Call<Student> createGoal(@Path("id") String studentId, @Body Goal goal);

    @DELETE("students/{id}")
    Call<Student> deleteStudent();

    @GET("goals")
    Call<ArrayList<Goal>> getGoals(@Header("token") String token);

    @GET("goals/{id}")
    Call<Goal> getGoal(@Path("id") String studentId);

    @POST("goals")
    Call<Goal> createGoal(@Body Goal goal, @Header("token") String token);

    @DELETE("goals/{id}")
    Call<Goal> deleteGoal(@Path("id") String goalId);

    @GET("classrooms/{id}/students")
    Call<ArrayList<Student>> getClassroomStudents(@Path("id") String classroomId, @Header("token") String token);

    @POST("students/goals")
    Call<GoalAssignedResponse> assignGoalToStudents(@Body AssignGoalStudents assignGoalStudents, @Header("token") String token);

    // Quizzes

    @POST("classrooms/{id}/quizzes")
    Call<Quiz> createQuiz(@Path("id") String classroomId, @Header("token") String token, @Body Quiz quiz);

    @GET("classrooms/{id}/quizzes")
    Call<ArrayList<Quiz>> getQuizzes(@Path("id") String classroomId, @Header("token") String token);

    @GET("quizzes/{id}")
    Call<Quiz> getQuiz(@Path("id") String quizId, @Header("token") String token);

    @POST("quizzes/{id}/start")
    Call<Quiz> startQuiz(@Path("id") String quizId);

    @POST("quizzes/{id}/stop")
    Call<Quiz> stopQuiz(@Path("id") String quizId);

    @DELETE("quizzes/{id}")
    Call<Quiz> deleteQuiz(@Path("id") String quizId);

    @POST("students/{id}/quizzes")
    Call<Student> submitQuiz(@Path("id") String studentId, @Header("token") String token, @Body StudentQuizHistory response);
}
