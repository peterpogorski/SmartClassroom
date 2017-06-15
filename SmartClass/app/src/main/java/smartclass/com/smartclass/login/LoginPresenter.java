package smartclass.com.smartclass.login;

/**
 * Created by peterpogorski on 2017-06-09.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
    }


}
