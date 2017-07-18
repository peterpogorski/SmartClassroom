package smartclass.com.smartclass.goalprogress;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.PieChart;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import smartclass.com.smartclass.R;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.UserToken;
import smartclass.com.smartclass.models.Student;

/**
 * Created by peterpogorski on 2017-07-17.
 */

public class GoalProgressActivity extends Activity implements GoalProgressContract.View {

    private GoalProgressPresenter mPreseneter;
    private Retrofit mRetrofit;

    private PieChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRetrofit = SmartClassRetrofit.getInstance();
        mPreseneter = new GoalProgressPresenter(this);

        mChart = (PieChart) findViewById(R.id.pie_chart);
    }

    @Override
    public void getStudentModel() {
        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);
        String userId = UserToken.getInstance().getUserId();
        String token = UserToken.getInstance().getTokenValue();

        Call<Student> getStudent = smartClassService.getStudent(userId, token);
        getStudent.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Student student = response.body();
                //mPreseneter.onStudentLoaded(student);
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });
    }

    @Override
    public void populatePieChart() {
        mChart.setCenterTextColor(R.color.white);
        mChart.setCenterText(getString(R.string.goal_progress));
        mChart.setCenterTextColor(R.color.colorPrimaryDark);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);


    }
}
