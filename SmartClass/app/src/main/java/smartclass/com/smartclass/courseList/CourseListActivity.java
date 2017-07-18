package smartclass.com.smartclass.courseList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import smartclass.com.smartclass.classroom.ClassroomActivity;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.demodata.UserToken;
import smartclass.com.smartclass.login.LoginActivity;
import smartclass.com.smartclass.models.Classroom;
import smartclass.com.smartclass.models.Course;
import smartclass.com.smartclass.course.CourseActivity;
import smartclass.com.smartclass.R;
import smartclass.com.smartclass.models.Student;
import smartclass.com.smartclass.models.Teacher;

/**
 * Created by peterpogorski on 2017-06-12.
 */
public class CourseListActivity extends Activity implements CourseListContract.View {

    private CourseListAdapter mListAdapter;
    private List<Course> mCourseList = new ArrayList<Course>();
    private RecyclerView mRecyclerView;
    private CourseListPresenter mPresenter;
    private ImageButton mLogout;
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_screen);

        mRetrofit = SmartClassRetrofit.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.course_list_view);
        mLogout = (ImageButton) findViewById(R.id.logout);

        mLogout.setOnClickListener(mOnLogoutClickListener);

        mPresenter = new CourseListPresenter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mPresenter.onCreate();
    }

    @Override
    public void showCourse(String courseName, String classroomId) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(getString(R.string.saved_classroom_id), classroomId);
        sharedPreferences.edit().commit();

        UserToken.getInstance().initClassroomId(classroomId);

        Intent intent;
        if (TeacherModeDataManager.getInstance().isTeacherModeEnabled()) {
            intent = new Intent(this, ClassroomActivity.class);
            intent.putExtra(ClassroomActivity.COURSE_NAME, courseName);
            TeacherModeDataManager.getInstance().setCurrentClassroomId(classroomId);
        } else {
            intent = new Intent(this, CourseActivity.class);
            intent.putExtra(CourseActivity.COURSE_NAME, courseName);
            TeacherModeDataManager.getInstance().setCurrentClassroomId(classroomId);
        }
        startActivity(intent);
    }

    @Override
    public void confirmLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure that you want to log out?");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onLogoutConfirmed();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void clearCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    @Override
    public void showLoginScreen() {
        Intent intent = new Intent(CourseListActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loadClassrooms() {
        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);
        String userId = UserToken.getInstance().getUserId();
        String token = UserToken.getInstance().getTokenValue();

        if(TeacherModeDataManager.getInstance().isTeacherModeEnabled()) {
            Call<Teacher> getTeacher = smartClassService.getTeacher(userId, token);
            getTeacher.enqueue(new Callback<Teacher>() {
                @Override
                public void onResponse(Call<Teacher> call, Response<Teacher> response) {
                    if (mPresenter != null) {
                        mPresenter.onClassroomsLoaded(response.body().getClassrooms());
                    }
                }

                @Override
                public void onFailure(Call<Teacher> call, Throwable t) {

                }
            });
        } else {
            Call<Student> getStudent = smartClassService.getStudent(userId, token);
            getStudent.enqueue(new Callback<Student>() {
                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {
                    if (mPresenter != null && response.body() != null) {
                        mPresenter.onClassroomsLoaded(response.body().getClassrooms());
                    }
                }

                @Override
                public void onFailure(Call<Student> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void displayClassrooms(ArrayList<Classroom> classrooms) {
        mListAdapter = new CourseListAdapter(classrooms, mPresenter);
        mRecyclerView.setAdapter(mListAdapter);
    }

    private View.OnClickListener mOnLogoutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onLogoutClicked();
        }
    };
}
