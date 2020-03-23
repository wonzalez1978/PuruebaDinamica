package cl.desafiolatam.pruebadinamica.bean;

import java.util.ArrayList;

import cl.desafiolatam.pruebadinamica.bean.Pregunta;

// Creamos un objeto pojo java para el mapeo
// de los datos a recibir desde el api opentdben formato json.

public class PreguntasListas {

    private int response_code;
    private ArrayList<Pregunta> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public ArrayList<Pregunta> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pregunta> results) {
        this.results = results;
    }
}