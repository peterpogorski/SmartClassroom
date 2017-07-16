package smartclass.com.smartclass.demodata;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class UserToken {

    private static UserToken instance;

    private String mTokenValue;

    public static UserToken getInstance() {
        if(instance == null) {
            instance = new UserToken();
        }
        return instance;
    }

    public void init(String tokenValue) {
        mTokenValue = tokenValue;
    }

    public String getTokenValue() {
        return mTokenValue;
    }
}
