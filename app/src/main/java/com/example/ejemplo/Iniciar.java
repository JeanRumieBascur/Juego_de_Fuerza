package com.example.ejemplo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Iniciar extends AppCompatActivity {
    SensorManager sm;
    Sensor s;
    SensorEventListener sel;
    TextView valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

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

                    Intent i =new Intent(Iniciar.this,Iniciar2.class);
                    i.putExtra("Valor1", EjeX);
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

}

