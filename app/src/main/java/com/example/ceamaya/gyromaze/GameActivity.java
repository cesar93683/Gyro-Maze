package com.example.ceamaya.gyromaze;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private final String TAG = GameActivity.class.getSimpleName();
    private boolean isGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        isGameOver = false;
        Intent intent = getIntent();
        int levelNumber = intent.getIntExtra(LevelActivity.EXTRA_LEVEL_NUMBER, Levels.level1.levelNumber);
        GameView gameView = findViewById(R.id.ball_view);
        gameView.setLevel(levelNumber);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isGameOver) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, accelerometer);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        GameView gameView = findViewById(R.id.ball_view);
        gameView.move(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void userWins() {
        isGameOver = true;
        sensorManager.unregisterListener(this, accelerometer);
        createCongratsDialog();
    }

    private void createCongratsDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Congratulations, you win!");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(
                "Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = getIntent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
        AlertDialog alert11 = alertDialog.create();
        alert11.show();
    }
}
