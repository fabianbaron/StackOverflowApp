package com.example.fabia.stackoverflowapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StackOverflowRetrofit {
    String BASE_URL = "https://api.stackexchange.com";

    @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    Call<ListWrapper<Questions>> getQuestions();

    @GET("/2.2/questions/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answers>> getAnswersForQuestions(@Path("id") String questionId);
}
