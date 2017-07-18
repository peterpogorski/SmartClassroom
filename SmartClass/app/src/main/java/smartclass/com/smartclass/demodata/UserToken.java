package smartclass.com.smartclass.demodata;

/**
 * Created by peterpogorski on 2017-07-16.
 */

public class UserToken {

    private static UserToken instance;

    private String mTokenValue;
    private String mUserId;
    private String mClassroomId;

    public static UserToken getInstance() {
        if(instance == null) {
            instance = new UserToken();
        }
        return instance;
    }

    public void init(String tokenValue, String userId) {
        mTokenValue = tokenValue;
        mUserId = userId;
    }

    public void initClassroomId(String classroomId) {
        mClassroomId = classroomId;
    }
    public String getTokenValue() {
        return mTokenValue;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getClassroomId() {
        return mClassroomId;
    }
}
