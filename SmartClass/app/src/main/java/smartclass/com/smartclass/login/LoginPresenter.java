package smartclass.com.smartclass.login;

/**
 * Created by peterpogorski on 2017-06-09.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
    }

    @Override
    public void onLoginClicked(String username, String password, boolean isTeacher) {
        boolean validFields = true;

        if(username.isEmpty()) {
            mLoginView.onUsernameFieldEmpty();
            validFields = false;
        }

        if(password.isEmpty()) {
            mLoginView.onPasswordFieldEmpty();
            validFields = false;
        }

        if(validFields) {
            mLoginView.authenticateUser(username, password, isTeacher);
        }
    }

    @Override
    public void onAuthenticationSuccess(String token, boolean isTeacher) {
        mLoginView.saveTokenValue(token);
        mLoginView.saveTeacherField(isTeacher);
        mLoginView.showClassList();
    }

    @Override
    public void onAlreadyLoggedIn() {
        mLoginView.showClassList();
    }
}
