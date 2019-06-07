package com.gyromaze;

import static com.gyromaze.Settings.PREF_MOVE_SCALE;
import static com.gyromaze.Settings.PREF_SETTINGS;
import static com.gyromaze.Settings.PREF_THEME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SharedPreferences prefs = getSharedPreferences(PREF_SETTINGS, MODE_PRIVATE);
    GameView.THEME = prefs.getInt(PREF_THEME, 1);
    GameView.MOVE_SCALE = prefs.getInt(PREF_MOVE_SCALE, GameView.DEFAULT_SCALE);
  }

  public void playClick(@SuppressWarnings("unused") View view) {
    Intent intent = new Intent(this, LevelActivity.class);
    startActivity(intent);
  }

  public void settingsClick(@SuppressWarnings("unused") View view) {
    Intent intent = new Intent(this, Settings.class);
    startActivity(intent);
  }
}
