package cl.desafiolatam.pruebadinamica.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.widget.Toast;

public class RetrofitClient {
    private static final String TAG = "RETROFITcLIENT";
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://opentdb.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Log.i(TAG,"Getretrofitinstance") ;
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
