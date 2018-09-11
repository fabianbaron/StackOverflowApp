package com.example.fabia.stackoverflowapp;

import com.google.gson.annotations.SerializedName;

public class Questions {
    public String titulo;
    public String contenido;

    @SerializedName("question_id")
    public String myQuestionId;

    @Override
    public String toString() {
        return titulo;
    }
}
