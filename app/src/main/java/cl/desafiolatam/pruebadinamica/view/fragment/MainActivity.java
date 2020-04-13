package cl.desafiolatam.pruebadinamica.view.fragment;

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

import cl.desafiolatam.pruebadinamica.R;
import cl.desafiolatam.pruebadinamica.model.bean.Model;
import cl.desafiolatam.pruebadinamica.presenter.IPresenter;
import cl.desafiolatam.pruebadinamica.presenter.IPresenterModel;
import cl.desafiolatam.pruebadinamica.presenter.Presenter;
import cl.desafiolatam.pruebadinamica.view.fragment.adapters.FragmentosPageAdapter;
import cl.desafiolatam.pruebadinamica.model.bean.api.IApi;
import cl.desafiolatam.pruebadinamica.model.bean.api.RetrofitClient;
import cl.desafiolatam.pruebadinamica.model.bean.Pregunta;
import cl.desafiolatam.pruebadinamica.model.bean.PreguntasLista;
import cl.desafiolatam.pruebadinamica.view.fragment.OnFragmentPreguntaListener;
import cl.desafiolatam.pruebadinamica.view.fragment.PreguntaFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnFragmentPreguntaListener, IPresenterView, IView {
    public static final String TAG = "LifeCicleLog";
    IPresenter iPresenter;
    FragmentPagerAdapter fragmentsPageAdapter;
    ViewPager viewPager;
    Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Log.i(TAG, "onCreate: Creando la actividad");
            FragmentManager fragmentManager = getFragmentManager();
            iniciarPresenter();
            getDatosDesdePresenter();

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


    @Override
    public void notificarVista(PreguntasLista preguntasLista) {

        List<Fragment> paginasFragmentos = getFragmentosDinamicos(preguntasLista);
        fragmentsPageAdapter = new FragmentosPageAdapter(getSupportFragmentManager(), paginasFragmentos);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(fragmentsPageAdapter);

        Log.i(TAG, "Notificar Vista");


    }

    @Override
    public void iniciarPresenter() {
        presenter  = new Presenter(this);
        presenter.setModel(new Model((IPresenterModel)presenter));

    }

    @Override
    public void getDatosDesdePresenter() {
        presenter.getDatosDesdeModel();

    }
}












