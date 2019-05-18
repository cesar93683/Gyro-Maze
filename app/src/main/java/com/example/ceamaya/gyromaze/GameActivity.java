package com.example.ceamaya.gyromaze;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private final String TAG = GameActivity.class.getSimpleName();
    private boolean isGameOver;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        isGameOver = false;
        Intent intent = getIntent();
        int levelNumber = intent.getIntExtra(LevelActivity.EXTRA_LEVEL_NUMBER, Levels.level1.levelNumber);
        gameView = findViewById(R.id.ball_view);
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
        gameView.move(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    public void userWins() {
        isGameOver = true;
        sensorManager.unregisterListener(this, accelerometer);
        createCongratsDialog(2);
    }

    private void createCongratsDialog(int numYellowStars) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_game_won, null);

        setStars(numYellowStars, view);

        alertDialog.setView(view)
                .setMessage("Congratulations, you win!")
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = getIntent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void setStars(int numYellowStars, View view) {
        if(numYellowStars >= 1) {
            ImageView starOne = view.findViewById(R.id.star_one);
            starOne.setBackgroundResource(R.drawable.ic_star_yellow);
            if(numYellowStars >= 2) {
                ImageView starTwo = view.findViewById(R.id.star_two);
                starTwo.setBackgroundResource(R.drawable.ic_star_yellow);
                if(numYellowStars >= 3) {
                    ImageView starThree = view.findViewById(R.id.star_three);
                    starThree.setBackgroundResource(R.drawable.ic_star_yellow);

                }
            }
        }
    }
}
