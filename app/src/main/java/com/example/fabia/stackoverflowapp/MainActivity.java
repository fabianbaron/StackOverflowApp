package com.example.fabia.stackoverflowapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private StackOverflowRetrofit myRetrofit;
    String token;
    private Button botonAutenticar;

    private Spinner selectorPreguntas;
    private RecyclerView myRecyclerView;
    private Callback<ListWrapper<Answers>> respuestaCallback = new Callback<ListWrapper<Answers>>() {
        @Override
        public void onResponse(Call<ListWrapper<Answers>> call,
                               Response<ListWrapper<Answers>> response) {
            if (response.isSuccessful()) {
                List<Answers> datos = new ArrayList<>();
                datos.addAll(response.body().items);
                myRecyclerView.setAdapter(new RecyclerViewAdapter(datos));
            } else {
                Log.d("Question callback", "Code: " + response.code() + " Message: " +
                    response.message());
            }
        }


        @Override
        public void onFailure(Call<ListWrapper<Answers>> call, Throwable t) {

        }
    };

    Callback<ListWrapper<Questions>> preguntasCallback = new Callback<ListWrapper<Questions>>() {
        @Override
        public void onResponse(Call<ListWrapper<Questions>> call,
                               Response<ListWrapper<Questions>> response) {
            if (response.isSuccessful()) {
                ListWrapper<Questions> datos = response.body();
                ArrayAdapter<Questions> myArrayAdapter =
                        new ArrayAdapter<Questions>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, datos.items);
                selectorPreguntas.setAdapter(myArrayAdapter);
            } else {
                Log.d("QuestionsCallback: ", "Code: " + response.code() + ". Message: " +
                response.message());
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Questions>> call, Throwable t) {
            t.printStackTrace();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectorPreguntas = (Spinner) findViewById(R.id.spinner_preguntas);
        selectorPreguntas.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                                               long id) {
                        Toast.makeText(MainActivity.this,"Item seleccionado", Toast.LENGTH_SHORT).show();
                        Questions myQuestions = (Questions) parent.getAdapter().getItem(position);
                        myRetrofit.getAnswersForQuestions(myQuestions.myQuestionId)
                                .enqueue(respuestaCallback);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
            );

        botonAutenticar = (Button) findViewById(R.id.button_autenticar);
        myRecyclerView = (RecyclerView) findViewById(R.id.recycler_lista);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        crearMyRetrofit();
        myRetrofit.getQuestions().enqueue(preguntasCallback);
    }

    private void crearMyRetrofit() {
        Gson myGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(StackOverflowRetrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(myGson))
                .build();
        myRetrofit = retrofit.create(StackOverflowRetrofit.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(token != null) {
            botonAutenticar.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.text1:
                if (token != null) {
                    //Por hacer
                } else {
                    Toast.makeText(MainActivity.this, "Debe autenticarse primero..."
                    ,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_autenticar:
                Toast.makeText(MainActivity.this,"Autenticando...",Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }


}
