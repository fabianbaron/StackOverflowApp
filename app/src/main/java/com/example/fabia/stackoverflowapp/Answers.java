package com.example.fabia.stackoverflowapp;

import com.google.gson.annotations.SerializedName;

public class Answers {
    @SerializedName("answer_id")
    public String answerId;
    @SerializedName("is_accepted")
    public boolean isAccepted;

    public int score;

    @Override
    public String toString() {
        return answerId + "- Puntaje: " + score + "- Aceptada: " + (isAccepted?"Si":"No");
    }
}
