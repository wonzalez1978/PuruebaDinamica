package cl.desafiolatam.pruebadinamica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.desafiolatam.pruebadinamica.adapters.FragmentosPageAdapter;
import cl.desafiolatam.pruebadinamica.api.IApi;
import cl.desafiolatam.pruebadinamica.api.RetrofitClient;
import cl.desafiolatam.pruebadinamica.bean.Pregunta;
import cl.desafiolatam.pruebadinamica.bean.PreguntasLista;
import cl.desafiolatam.pruebadinamica.fragment.OnFragmentPreguntaListener;
import cl.desafiolatam.pruebadinamica.fragment.PreguntaFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnFragmentPreguntaListener {
    public static final String TAG = "LifeCicleLog";

    FragmentPagerAdapter fragmentsPageAdapter;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: Creando la actividad");
        FragmentManager fragmentManager = getFragmentManager();

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
                    List<Fragment> paginasFragmentos = getFragmentosDinamicos(preguntas);
                    fragmentsPageAdapter = new FragmentosPageAdapter(getSupportFragmentManager(), paginasFragmentos);
                    viewPager = findViewById(R.id.viewPager);
                    viewPager.setAdapter(fragmentsPageAdapter);

                    Log.i(TAG, "onResponse");


                    for (int x = 0; x < response.body().getResults().size(); x++) {
                        Log.d(TAG, "Pregunta:" + response.body().getResults().get(x).getQuestion());
                    }
                }
            }

            @Override
            public void onFailure(Call<PreguntasLista> call, Throwable t) {
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

    private List<Fragment> getFragmentosDinamicos(PreguntasLista preguntas) {
        List<Fragment> fragmentosLista = new ArrayList<>();

        int contadorPregunta = 1;

        for (Pregunta pregunta : preguntas.getResults()) {
            fragmentosLista.add(PreguntaFragment.newInstance(
                    contadorPregunta,
                    pregunta.getQuestion(),
                    pregunta.getCategory(),
                    pregunta.getCorrect_answer(),
                    pregunta.getIncorrect_answers())
            );
            contadorPregunta++;
        }
        return fragmentosLista;
    }


}












