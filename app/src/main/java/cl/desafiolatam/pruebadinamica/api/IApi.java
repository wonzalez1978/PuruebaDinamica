package cl.desafiolatam.pruebadinamica.api;

import android.provider.CallLog;

import cl.desafiolatam.pruebadinamica.bean.PreguntasListas;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IApi {

    @GET("api.php?amount=10&category=15&difficulty=easy")
    Call<PreguntasListas> getAllQuestions();
}
