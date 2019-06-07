package com.gyromaze;

import static com.gyromaze.SettingsActivity.PREF_MOVE_SCALE;
import static com.gyromaze.SettingsActivity.PREF_THEME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    GameView.THEME = prefs.getInt(PREF_THEME, 1);
    GameView.MOVE_SCALE = prefs.getInt(PREF_MOVE_SCALE, GameView.DEFAULT_SCALE);
  }

  public void playClick(@SuppressWarnings("unused") View view) {
    Intent intent = new Intent(this, LevelActivity.class);
    startActivity(intent);
  }

  public void settingsClick(@SuppressWarnings("unused") View view) {
    Intent intent = new Intent(this, SettingsActivity.class);
    startActivity(intent);
  }
}
