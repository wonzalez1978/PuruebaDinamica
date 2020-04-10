package cl.desafiolatam.pruebadinamica.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import cl.desafiolatam.pruebadinamica.R;

public class PreguntaFragment extends Fragment {
    private int radioButtonValue = 0;
    private TextView preguntaView, categoriaView, preguntaLabel;
    private RadioGroup grupoRespuestasView;
    private RadioButton respuestaUno, respuestaDos, respuestaTres, respuestaCuatro;
    private OnFragmentPreguntaListener mListener;
    private Button btnVerRespuesta;


    public static PreguntaFragment newInstance(int cantidad, String questions, String category, String correct_answer, ArrayList<String> incorrectAnswers) {

        PreguntaFragment fragment = new PreguntaFragment();
        Bundle arguments = new Bundle();
        arguments.putString("NUMERO_PREGUNTA", "PREGUNTA" + cantidad);
        arguments.putString("QUESTIONS", questions);
        arguments.putString("CATEGORY", category);
        arguments.putString("CORRECT_ANSWER", correct_answer);
        arguments.putStringArrayList("INCORRECT_ANSWERS", incorrectAnswers);
        Log.d("incorect__answer", incorrectAnswers.toString());
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentPreguntaListener) {
            mListener = (OnFragmentPreguntaListener) context;
        } else {
            throw new ClassCastException(context.toString() + "aun no implementas la interface en esta actividad");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pregunta, container, false);
        final String preguntaTitulo = Objects.requireNonNull(getArguments()).getString("NUMERO_PREGUNTA");
        final String questions = Objects.requireNonNull(getArguments()).getString("QUESTIONS");
        final String category = Objects.requireNonNull(getArguments().getString("CATEGORY"));
        final String correct_answer = Objects.requireNonNull(getArguments().getString("CORRECT_ANSWER"));
        final ArrayList<String> incorrect_answers = Objects.requireNonNull(getArguments().getStringArrayList("INCORRECT_ANSWERS"));

        //inicializamos las vistas declaradas
        initializeViews(view);
        //asignando valores dinamicos en base a los datos recibidos
        //de nuestra API asignamos valores a las vistas


        preguntaView.setText(questions);
        categoriaView.setText(category);
        preguntaLabel.setText(preguntaTitulo);

        //Recorremos el arreglo de Strings "Incorrect answers" de nuestra API de datos
        for (int x = 0; x < incorrect_answers.size(); x++) {
            switch (x) {
                case 0:
                    respuestaUno.setText(incorrect_answers.get(x));
                    break;
                case 1:
                    respuestaDos.setText(incorrect_answers.get(x));
                    break;
                case 2:
                    respuestaTres.setText(incorrect_answers.get(x));
                    break;
            }
        }
        //AGREGAMOS LA RESPUESTA CORRECTA DE LA API EN UN CUARTO RADIOBUTTON
        respuestaCuatro = view.findViewById(R.id.rRespuestaCuatro);
        respuestaCuatro.setText(correct_answer);
        //EVENTO DE RADIO - BUTTONS - CODIGO PARA QUE LA SELECCION DEL RADIO BUTTON SEA ACTUALIZADO EN LA VISTA
        grupoRespuestasView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (respuestaUno.isChecked()) {
                    setRadioButtonsValues(1, respuestaUno.getText().toString());
                } else if (respuestaDos.isChecked()) {
                    setRadioButtonsValues(2, respuestaDos.getText().toString());
                } else if (respuestaTres.isChecked()) {
                    setRadioButtonsValues(3, respuestaTres.getText().toString());
                } else if (respuestaCuatro.isChecked()) {
                    setRadioButtonsValues(4, respuestaCuatro.getText().toString());
                }
            }

            private void setRadioButtonsValues(int radioButtonValu, String respuesta) {
                //ENVIAMOS NUESTRO VALOR SELECCIONADO AL LISTENER DE LA INTEFACE
                mListener.onRadioButtonSelection(radioButtonValu, respuesta);
                radioButtonValue = radioButtonValu;
            }
        });
        btnVerRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://opentdb.com/api.php?amount=10&category=15&difficulty=easy");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

        });
        return view;
        }

        @Override
        public void onDetach () {
            mListener = null;
            super.onDetach();

        }

        private void initializeViews (View view){
            preguntaView = view.findViewById(R.id.textViewQuestions);
            categoriaView = view.findViewById(R.id.textViewCategory);
            grupoRespuestasView = view.findViewById(R.id.rgRespuestas);
            respuestaUno = view.findViewById(R.id.rRespuestaUno);
            respuestaDos = view.findViewById(R.id.rRespuestaDos);
            respuestaTres = view.findViewById(R.id.rRespuestaTres);
            respuestaCuatro = view.findViewById(R.id.rRespuestaCuatro);
            preguntaLabel = view.findViewById(R.id.tituloPregunta);
            btnVerRespuesta = view.findViewById(R.id.btnConsultaRespuesta);

        }


    }
