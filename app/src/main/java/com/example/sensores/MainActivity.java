package com.example.sensores;

import android.content.Context;
import android.hardware.Sensor;// classe que representa um sensor no dispositivo, como um acelerômetro
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/*
* ALTERADO O SDK NO BUILD GRADLE PARA 34, VERSÃO 33 NÃO ESTAVA FUNCIONANDO
* IMPLEMENTADO NO XML NO VALUES ESPAÇAMENTO DAS DIMENSÕES
* */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    /*
    * DECLARAÇÃO DE VARIÁVEIS
    * */
    private SensorManager sensorManager;
    private Sensor acelerometro;
    private TextView xTextView, yTextView, zTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar o SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Verificar se o dispositivo possui acelerômetro
        if (sensorManager != null) {
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (acelerometro != null) {
                // Registrar o SensorEventListener
                sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

        // Inicializar os TextViews
        xTextView = findViewById(R.id.xTextView);
        yTextView = findViewById(R.id.yTextView);
        zTextView = findViewById(R.id.zTextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Obter os valores do sensor de acelerômetro
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Atualizar os TextViews com os valores
        xTextView.setText("X: " + x);
        yTextView.setText("Y: " + y);
        zTextView.setText("Z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Método necessário, mas não será usado neste exemplo
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (acelerometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
