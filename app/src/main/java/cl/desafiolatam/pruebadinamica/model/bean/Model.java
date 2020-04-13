package cl.desafiolatam.pruebadinamica.model.bean;

import android.util.Log;
import android.view.inputmethod.InputMethod;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

import cl.desafiolatam.pruebadinamica.R;
import cl.desafiolatam.pruebadinamica.model.bean.api.IApi;
import cl.desafiolatam.pruebadinamica.model.bean.api.RetrofitClient;
import cl.desafiolatam.pruebadinamica.presenter.IPresenterModel;
import cl.desafiolatam.pruebadinamica.view.fragment.adapters.FragmentosPageAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements IModel {
    public static final String TAG = "LifeCicleLog";

    IPresenterModel iPresenterModel;

    public Model(IPresenterModel iPresenterModel) {
        this.iPresenterModel = iPresenterModel;
    }

    @Override
    public void getDato() {

        IApi service = RetrofitClient.getRetrofitInstance().create(IApi.class);
        Call<PreguntasLista> call = service.getAllQuestions();

        call.enqueue(new Callback<PreguntasLista>() {
            private Object Pregunta;

            @Override
            public void onResponse(Call<PreguntasLista> call, Response<PreguntasLista> response) {
                Log.i(TAG, "onResponse" + response);
                PreguntasLista preguntas = response.body();

                int contadorPregunta = 1;

                if (preguntas != null) {
                    enviarDato(preguntas);
                }
            }

            @Override
            public void onFailure(Call<PreguntasLista> call, Throwable t) {
                Log.i(TAG, "se ejecutó on Failure");

            }
        });

    }

    @Override
    public void enviarDato(PreguntasLista preguntasLista) {
        iPresenterModel.notificarPresenter(preguntasLista);

    }
}
