package cl.desafiolatam.pruebadinamica.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import cl.desafiolatam.pruebadinamica.R;

public class PreguntaFragment extends Fragment {
    int radioButtonValue = 0;
    private TextView preguntaView, categoriaView;
    private RadioGroup grupoRespuestasView;
    private RadioButton respuestaUno, respuestaDos, respuestaTres, respuestaCuatro;


    public static PreguntaFragment newIntance(String pregunta, String categoria, String respuesta_Correcta, ArrayList<String> respuesta_Incorrecta) {

        PreguntaFragment fragment = new PreguntaFragment();
        Bundle arguments = new Bundle();
        arguments.putString("Pregunta", pregunta);
        arguments.putString("Categoria", categoria);
        arguments.putString("Respuesta_correcta", respuesta_Correcta);
        arguments.putStringArrayList("Respuesta_incorrecta", respuesta_Incorrecta);
        fragment.setArguments(arguments);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pregunta, container, false);

        final String pregunta = Objects.requireNonNull(getArguments()).getString("Pregunta");
        final String categoria = Objects.requireNonNull(getArguments().getString("Categoria"));
        final String respuestaCorrecta = Objects.requireNonNull(getArguments().getString("Respuesta_Correcta"));
        final ArrayList<String> respuestasIncorrectas = Objects.requireNonNull(getArguments().getStringArrayList("Respuesta_incorrecta"));

        initializeViews(view);
        preguntaView.setText(pregunta);
        preguntaView.setText(categoria);
        preguntaView=view.findViewById(R.id.pregunta);
        categoriaView=view.findViewById(R.id.categoria);
        grupoRespuestasView=view.findViewById(R.id.radioGrupoRespuestas);
        respuestaUno=view.findViewById(R.id.radioRespuestaUno);
        respuestaDos=view.findViewById(R.id.radioRespuestaDos);
        respuestaTres=view.findViewById(R.id.radioRespuestaTres);
        respuestaCuatro=view.findViewById(R.id.radioRespuestaCuatro);

        for (int x = 0; x < respuestasIncorrectas.size(); x++) {
            switch (x) {
                case 0:
                    respuestaUno.setText(respuestasIncorrectas.get(x));
                    break;
                case 1:
                respuestaDos.setText(respuestasIncorrectas.get(x));
                break;
                case 2:
                respuestaTres.setText(respuestasIncorrectas.get(x));
                break;
            }
        }
        respuestaCuatro = view.findViewById(R.id.radioRespuestaCuatro);
        respuestaCuatro.setText(respuestaCorrecta);
        return view;
    }

    private View initializeViews(View view) {
        preguntaView = view.findViewById(R.id.pregunta);
        categoriaView = view.findViewById(R.id.categoria);
        grupoRespuestasView = view.findViewById(R.id.radioGrupoRespuestas);
        respuestaUno = view.findViewById(R.id.radioRespuestaUno);
        respuestaDos = view.findViewById(R.id.radioRespuestaDos);
        respuestaTres = view.findViewById(R.id.radioRespuestaTres);

        grupoRespuestasView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (respuestaUno.isChecked()){
                    radioButtonValue = 1;

                }else if (respuestaDos.isChecked()){
                    radioButtonValue = 2;
                }else if (respuestaTres.isChecked()){
                    radioButtonValue = 3;
                }else if (respuestaCuatro.isChecked()){
                    radioButtonValue = 4;
                }
            }
       });
        return view;
    }



}

