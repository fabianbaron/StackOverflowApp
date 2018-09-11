package com.example.fabia.stackoverflowapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private StackOverflowRetrofit myRetrofit;
    String token;
    private Button botonAutenticar;

    private Spinner selectorPreguntas;
    private RecyclerView myRecyclerView;

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
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
            );

        ///////////////////////////Test///////////////////////////////
        List<Questions> myPreguntas = FakeProvider.getQuestions();
        ArrayAdapter<Questions> myArrayAdapter = new ArrayAdapter<Questions>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item);
        selectorPreguntas.setAdapter(myArrayAdapter);
        List<Answers> myRespuestas = FakeProvider.getAnswers();
        RecyclerViewAdapter myRecyclerViewAdapter = new RecyclerViewAdapter(myRespuestas);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        ///////////////////////////Test///////////////////////////////

        botonAutenticar = (Button) findViewById(R.id.button_autenticar);
        myRecyclerView = (RecyclerView) findViewById(R.id.recycler_lista);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }
}
