package com.example.ejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Resultados extends AppCompatActivity {

    float jugador1;
    float jugador2;
    TextView txtgame;
    TextView txtpuntaje;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        jugador1=getIntent().getFloatExtra("Valor1",jugador1);
        jugador2=getIntent().getFloatExtra("Valor2",jugador2);
        txtgame=(TextView)findViewById(R.id.jugador);
        txtpuntaje=(TextView)findViewById(R.id.puntaje);
        Ganador(jugador1,jugador2,txtgame,txtpuntaje);



    }
    private void Ganador(float j1, float j2,TextView txtgame, TextView txtpuntaje){
        if(j1>j2){
            txtgame.setText("JUGADOR 1");
            txtpuntaje.setText(""+j1);
        }
        else if(j1<j2){
            txtgame.setText("JUGADOR 2");
            txtpuntaje.setText(""+j2);

        }
        else{
            txtgame.setText("EMPATEEEEE!!!!");
            txtpuntaje.setText(""+j2);
        }
    }
    public void Resultados(View v){
        Intent i =new Intent(Resultados.this,Valores.class);
        startActivity(i);
    }
    public void Volver(View v){
        Intent i =new Intent(Resultados.this,MainActivity.class);
        startActivity(i);

    }

}
