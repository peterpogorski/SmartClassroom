package smartclass.com.smartclass.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by max on 2017-06-15.
 */

public class StudentQuizResponse {

    @SerializedName("answer")
    private String answer;

    @SerializedName("correct")
    private boolean correct;

    @SerializedName("question")
    private String question;
}
