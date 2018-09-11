package com.example.fabia.stackoverflowapp;

import java.util.ArrayList;
import java.util.List;

public class FakeProvider {

    public static List<Questions> getQuestions() {
        List<Questions> preguntas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Questions pregunta = new Questions();
            pregunta.myQuestionId = String.valueOf(i);
            pregunta.titulo = "Pregunta " + String.valueOf(i);
            pregunta.contenido = "Cuerpo de la pregunta " + String.valueOf(i);
            preguntas.add(pregunta);
        }
        return preguntas;
    }

    public static List<Answers> getAnswers() {
        List<Answers> respuestas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Answers respuesta = new Answers();
            respuesta.answerId = String.valueOf(i);
            respuesta.isAccepted = false;
            respuesta.score = i;
            respuestas.add(respuesta);
        }
        return respuestas;
    }
}
