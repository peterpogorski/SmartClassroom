package smartclass.com.smartclass.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import smartclass.com.smartclass.R;
import smartclass.com.smartclass.courseList.CourseListActivity;
import smartclass.com.smartclass.demodata.SmartClassRetrofit;
import smartclass.com.smartclass.demodata.SmartClassService;
import smartclass.com.smartclass.demodata.TeacherModeDataManager;
import smartclass.com.smartclass.demodata.UserToken;
import smartclass.com.smartclass.models.AuthenticatedUser;
import smartclass.com.smartclass.models.LoginUser;

public class LoginActivity extends Activity implements LoginContract.View {

    private LoginPresenter mPresenter;
    private Retrofit mRetrofit;

    private Button mLoginButton;
    private EditText mUsername;
    private EditText mPassword;
    private CheckBox mTeacherCheck;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        mPresenter = new LoginPresenter(this);
        mRetrofit = SmartClassRetrofit.getInstance();
        mSharedPreferences = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);

        String tokenValue = mSharedPreferences.getString(getString(R.string.saved_user_token), null);
        if(tokenValue != null) {
            UserToken.getInstance().init(tokenValue);
            boolean isTeacher = mSharedPreferences.getBoolean(getString(R.string.saved_teacher_field), false);
            TeacherModeDataManager.getInstance().init(isTeacher);
            mPresenter.onAlreadyLoggedIn();
        }

        mLoginButton = (Button) findViewById(R.id.login_button);
        mUsername = (EditText) findViewById(R.id.username_field);
        mPassword = (EditText) findViewById(R.id.password_field);
        mTeacherCheck = (CheckBox) findViewById(R.id.teacher_check);

        mLoginButton.setOnClickListener(mOnLoginClickListener);
    }

    @Override
    public void onUsernameFieldEmpty() {
        mUsername.setError(getResources().getString(R.string.username_empty));
    }

    @Override
    public void onPasswordFieldEmpty() {
        mPassword.setError(getResources().getString(R.string.password_empty));
    }

    @Override
    public void saveTokenValue(String tokenValue) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(getString(R.string.saved_user_token), tokenValue);
        editor.commit();
        UserToken.getInstance().init(tokenValue);
    }

    @Override
    public void saveTeacherField(boolean isTeacher) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(getString(R.string.saved_teacher_field), isTeacher);
        editor.commit();
        TeacherModeDataManager.getInstance().init(isTeacher);
    }

    @Override
    public void authenticateUser(String username, String password, final boolean isTeacher) {
        SmartClassService smartClassService = mRetrofit.create(SmartClassService.class);

        LoginUser loginUser = new LoginUser(username, password, isTeacher);

        Call<AuthenticatedUser> userAuthentication = smartClassService.authenticateUser(loginUser);
        userAuthentication.enqueue(new Callback<AuthenticatedUser>() {
            @Override
            public void onResponse(Call<AuthenticatedUser> call, Response<AuthenticatedUser> response) {
                AuthenticatedUser user = response.body();
                if(user != null && user.getToken() != null) {
                    mPresenter.onAuthenticationSuccess(user.getToken(), isTeacher);
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthenticatedUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void showClassList() {
        Intent intent = new Intent(LoginActivity.this, CourseListActivity.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener mOnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();
            boolean isTeacher = mTeacherCheck.isChecked();
            mPresenter.onLoginClicked(username, password, isTeacher);
        }
    };
}
