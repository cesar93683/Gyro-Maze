package com.gyromaze;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

  private SensorManager sensorManager;
  private Sensor accelerometer;
  private boolean isGameOver;
  private GameView gameView;
  private int levelNumber;
  private long startTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    startTime = System.currentTimeMillis();
    isGameOver = false;

    Intent intent = getIntent();
    levelNumber = intent.getIntExtra(LevelActivity.EXTRA_LEVEL_NUMBER, 1);
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
    long endTime = System.currentTimeMillis();
    int time = (int) (endTime - startTime);
    updateBestTime(time);
    int numOfStars = Levels.getLevelByNumber(levelNumber).getNumStars(time);
    createCongratsDialog(numOfStars, time);
  }

  private void updateBestTime(int newTime) {
    SharedPreferences prefs = getSharedPreferences(LevelActivity.BEST_TIMES, MODE_PRIVATE);
    SharedPreferences.Editor prefsEditor = prefs.edit();
    int prevTime = prefs.getInt(Integer.toString(levelNumber), LevelActivity.LEVEL_UNLOCKED);
    if (prevTime == LevelActivity.LEVEL_UNLOCKED || newTime < prevTime) {
      prefsEditor.putInt(Integer.toString(levelNumber), newTime);
    }
    if (levelNumber < 10) {
      int nextLevelTime = prefs
          .getInt(Integer.toString(levelNumber + 1), LevelActivity.LEVEL_LOCKED);
      if (nextLevelTime == LevelActivity.LEVEL_LOCKED) {
        prefsEditor.putInt(Integer.toString(levelNumber + 1), LevelActivity.LEVEL_UNLOCKED);
      }
    }
    prefsEditor.apply();
  }

  private void createCongratsDialog(int numYellowStars, int time) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
    @SuppressLint("InflateParams") View view = getLayoutInflater()
        .inflate(R.layout.dialog_game_won, null);

    setStars(numYellowStars, view);

    TextView timeTextView = view.findViewById(R.id.time_text_view);
    double timeInSeconds = (double) time / 1000.0;
    timeTextView.setText(String.format("You finished in %s seconds!", timeInSeconds));

    alertDialog.setView(view)
        .setTitle("Congratulations!")
        .setOnCancelListener(new DialogInterface.OnCancelListener() {
          @Override
          public void onCancel(DialogInterface dialog) {
            finish();
          }
        })
        .setPositiveButton("continue", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            finish();
          }
        });
    AlertDialog alert = alertDialog.create();
    alert.show();
  }

  private void setStars(int numYellowStars, View view) {
    if (numYellowStars >= 1) {
      ImageView starOne = view.findViewById(R.id.star_one);
      starOne.setBackgroundResource(R.drawable.ic_star_yellow);
    }
    if (numYellowStars >= 2) {
      ImageView starTwo = view.findViewById(R.id.star_two);
      starTwo.setBackgroundResource(R.drawable.ic_star_yellow);
    }
    if (numYellowStars == 3) {
      ImageView starThree = view.findViewById(R.id.star_three);
      starThree.setBackgroundResource(R.drawable.ic_star_yellow);

    }
  }
}
