package smartclass.com.smartclass.goalprogress;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

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

public class GoalProgressActivity extends AppCompatActivity implements GoalProgressContract.View {

    private GoalProgressPresenter mPreseneter;
    private Retrofit mRetrofit;

    private PieChart mChart;
    private TextView mQuizCompleted;
    private TextView mAverageGrade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goal_progress);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitle("Progress");


        mRetrofit = SmartClassRetrofit.getInstance();
        mPreseneter = new GoalProgressPresenter(this);

        mChart = (PieChart) findViewById(R.id.pie_chart);
        mQuizCompleted = (TextView) findViewById(R.id.quiz_completed);
        mAverageGrade = (TextView) findViewById(R.id.average_grade);

        mPreseneter.onCreate();
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
                mPreseneter.onStudentLoaded(student);
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });
    }

    @Override
    public void populatePieChart(int goalsCompleted, int goalsInProgress) {
        mChart.setCenterTextColor(R.color.white);
        mChart.setCenterText(getString(R.string.goal_progress));
        mChart.setCenterTextColor(R.color.black);
        mChart.setCenterTextSize(18);
        mChart.setHoleRadius(32f);
        mChart.setTransparentCircleRadius(20f);
        mChart.getDescription().setEnabled(false);

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(goalsCompleted, "Completed"));
        entries.add(new PieEntry(goalsInProgress, "In Progress"));

        PieDataSet dataSet = new PieDataSet(entries, "");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);
    }

    @Override
    public void showQuizzesCompleted(int quizCompleted) {
        mQuizCompleted.setText(String.valueOf(quizCompleted));
    }

    @Override
    public void showAverageGrade(double averageGrade) {
        mAverageGrade.setText(String.format( "%.1f", averageGrade)  + "%");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
