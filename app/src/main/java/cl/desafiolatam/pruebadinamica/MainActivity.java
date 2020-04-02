package cl.desafiolatam.pruebadinamica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import cl.desafiolatam.pruebadinamica.api.IApi;
import cl.desafiolatam.pruebadinamica.api.RetrofitClient;
import cl.desafiolatam.pruebadinamica.bean.Pregunta;
import cl.desafiolatam.pruebadinamica.bean.PreguntasListas;
import cl.desafiolatam.pruebadinamica.fragment.OnFragmentPreguntaListener;
import cl.desafiolatam.pruebadinamica.fragment.PreguntaFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnFragmentPreguntaListener {
    public static final String TAG = "LifeCicleLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: Creando la actividad");
        FragmentManager fragmentManager = getFragmentManager();

        IApi service = RetrofitClient.getRetrofitInstance().create(IApi.class);
        Call<PreguntasListas> call = service.getAllQuestions();

        call.enqueue(new Callback<PreguntasListas>() {
            private Object Pregunta;

            @Override
            public void onResponse(Call<PreguntasListas> call, Response<PreguntasListas> response) {
                Log.i(TAG, "onResponse" + response);
                PreguntasListas preguntas = response.body();

                if (preguntas != null) {
                    Log.i(TAG, "onResponse");
                    Pregunta pregunta = preguntas.getResults().get(0);
                    Log.d("*+*", pregunta.toString());
                    PreguntaFragment preguntaFragment = PreguntaFragment.newInstance(
                            pregunta.getQuestion(),
                            pregunta.getCategory(),
                            pregunta.getCorrect_answer(),
                            pregunta.getIncorrect_answers());

                    getSupportFragmentManager().beginTransaction().add(R.id.fragmentolayout, preguntaFragment, "FRAGMENTO").commit();

                    for (int x = 0; x < response.body().getResults().size(); x++) {
                        Log.d(TAG, "Pregunta:" + response.body().getResults().get(x).getQuestion());
                    }
                }
            }

            @Override
            public void onFailure(Call<PreguntasListas> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error no pudimos recuperar los datos de el servidor", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "se ejecutó on Failure");

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Ha iniciado nuestra actividad en laUI", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onStar: Ha iniciado la actividad");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "nuestra actividad se ha pausado", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onPause: se ha pausado la actividad");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Hemos vuelto a la actividad", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onResume: hemos vuelto a la actividad");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "nuestra actividad se ha detenido", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onStop: se ha detenido la actividad");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Nuestra actividad se ha destruido", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "se ha destruido la actividad");
    }

    @Override
    public void onRadioButtonSelection(int selection, String respuesta) {
        Toast.makeText(getApplicationContext(),
                "La respuesta seleccionada en el fragmento fue:" +
                        "la: " + selection + ", correspondiente a" + respuesta, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "La respuesta seleccionada en el fragmento fue la:" + selection + ", correspondiente a " + respuesta);

    }
}
