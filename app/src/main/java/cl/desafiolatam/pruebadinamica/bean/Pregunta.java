package cl.desafiolatam.pruebadinamica.bean;

import java.util.ArrayList;

/*objeto pojo para el mapeo de los datos a recibir desde el api opentdb,
 ubicados en el mismo package bean, creamos una clase llamada “Pregunta”,
// la cual recibirá todos losdatos contenidos dentro del array results y mapeado en la clase “PreguntasLista”*/

public class Pregunta {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private ArrayList<String>incorrect_answer;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public ArrayList<String> getIncorrect_answer() {
        return incorrect_answer;
    }

    public void setIncorrect_answer(ArrayList<String> incorrect_answer) {
        this.incorrect_answer = incorrect_answer;
    }
}
