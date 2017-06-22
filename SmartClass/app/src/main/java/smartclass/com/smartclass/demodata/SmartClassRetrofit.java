package smartclass.com.smartclass.demodata;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peterpogorski on 2017-06-21.
 */

public class SmartClassRetrofit {

    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder().baseUrl("https://smart-classroom-ece452.mybluemix.net/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        }
        return instance;
    }
}
