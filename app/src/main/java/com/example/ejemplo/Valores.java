package com.example.ejemplo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Valores  extends AppCompatActivity {
     TextView jugador1;
     TextView jugador2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valores);
        jugador1=(TextView)findViewById(R.id.resultado1);
        jugador2=(TextView)findViewById(R.id.resultado2);
        Mostrar(jugador1,jugador2);

    }
    public void Mostrar(TextView t1, TextView t2){
        SQliteOpenHelperClase DB=new SQliteOpenHelperClase(this,"DBValores",null,1);
        SQLiteDatabase sql =DB.getWritableDatabase();
        Cursor c1=sql.rawQuery("select valor1, valor2 from valores order by id desc limit 1 ",null);

        if(c1.moveToFirst()){
            t1.setText(c1.getString(0));
            t2.setText(c1.getString(1));
            sql.close();
        }
        else {
            t1.setText("NO RESULTADO");
            t2.setText("NO RESULTADO");
        }
    }
    public void Volver(View v){
        Intent i =new Intent(Valores.this,MainActivity.class);
        startActivity(i);

    }

}
