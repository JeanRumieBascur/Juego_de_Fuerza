package com.example.ejemplo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Iniciar2 extends AppCompatActivity {

    SensorManager sm;
    Sensor s;
    SensorEventListener sel;
    TextView valor2;
    float valor1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar2);

        valor1=getIntent().getFloatExtra("Valor1", valor1);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (s == null) {
            finish();
        }
        sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float EjeX=sensorEvent.values[0];

                if(EjeX>5){
                    Registrar(valor1,EjeX);
                    Intent i =new Intent(Iniciar2.this,Resultados.class);
                    i.putExtra("Valor1", valor1);
                    i.putExtra("Valor2",EjeX);
                    startActivity(i);


                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }
    private void Start(){
        sm.registerListener(sel,s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void Stop(){
        sm.unregisterListener(sel);
    }

    @Override
    protected void onPause() {
        Stop();
        super.onPause();

    }

    @Override
    protected void onResume() {
        Start();
        super.onResume();

    }
    public void Registrar(float Valor1, float Valor2){
        SQliteOpenHelperClase DB=new SQliteOpenHelperClase(this,"DBValores",null,1);
        SQLiteDatabase sql =DB.getWritableDatabase();

        ContentValues registro=new ContentValues();
        registro.put("Valor1", Valor1);
        registro.put("Valor2", Valor2);
        sql.insert("valores", null, registro);
        sql.close();


    }

    }
