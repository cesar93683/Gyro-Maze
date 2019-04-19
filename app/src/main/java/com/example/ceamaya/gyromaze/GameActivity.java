package com.example.ceamaya.gyromaze;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private int moveAmount = 100;
    private final String TAG = GameActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(
                this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void leftClick(View view) {
        GameView gameView = findViewById(R.id.ball_view);
        gameView.moveLeft(moveAmount);
    }

    public void rightClick(View view) {
        GameView gameView = findViewById(R.id.ball_view);
        gameView.moveRight(moveAmount);
    }

    public void upClick(View view) {
        GameView gameView = findViewById(R.id.ball_view);
        gameView.moveUp(moveAmount);
    }

    public void downClick(View view) {
        GameView gameView = findViewById(R.id.ball_view);
        gameView.moveDown(moveAmount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
                this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
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
}
