package smartclass.com.smartclass.demodata;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-06-21.
 */

public interface SmartClassService {

    @GET("students")
    Call<ArrayList<Student>> getStudents();

    @GET("students/{id}")
    Call<Student> getStudent(@Path("id") String studentId);
}
