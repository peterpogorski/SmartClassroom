package smartclass.com.smartclass.demodata;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import smartclass.com.smartclass.models.Goal;
import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-06-21.
 */

public interface SmartClassService {

    @GET("students")
    Call<ArrayList<Student>> getStudents();

    @GET("students/{id}")
    Call<Student> getStudent(@Path("id") String studentId);
  
    @POST("students")
    Call<Student> createStudent(@Body Student student);

    @POST("students/{id}/goals/{goaldId}/activityLogs")
    Call<Student> createGoal(@Path("id") String studentId, @Body Goal goal);

    @DELETE("students/{id}")
    Call<Student> deleteStudent();

    @GET("goals")
    Call<ArrayList<Goal>> getGoals();

    @GET("goals/{id}")
    Call<Goal> getGoal(@Path("id") String studentId);

    @POST("goals")
    Call<Goal> createGoal(@Body Goal goal);

    @DELETE("goals/{id}")
    Call<Goal> deleteGoal(@Path("id") String goalId);

}
