package smartclass.com.smartclass.login;

/**
 * Created by peterpogorski on 2017-06-09.
 */
public class LoginContract {

    interface Presenter {
        void onLoginClicked(String username, String password, boolean isTeacher);
        void onAuthenticationSuccess(String token, boolean isTeacher);
        void onAlreadyLoggedIn();
    }

    interface View {
        void onUsernameFieldEmpty();
        void onPasswordFieldEmpty();
        void authenticateUser(String username, String password, boolean isTeacher);
        void saveTokenValue(String tokenValue);
        void saveTeacherField(boolean isTeacher);
        void showClassList();
    }
}
