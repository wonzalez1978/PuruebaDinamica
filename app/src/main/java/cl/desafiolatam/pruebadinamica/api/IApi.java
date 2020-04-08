package cl.desafiolatam.pruebadinamica.api;

import cl.desafiolatam.pruebadinamica.bean.PreguntasLista;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IApi {

    @GET("api.php?amount=10&category=15&difficulty=easy")
    Call<PreguntasLista> getAllQuestions();
}
