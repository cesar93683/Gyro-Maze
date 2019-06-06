package com.example.ceamaya.gyromaze;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  public static int theme;
  public static boolean speedup;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    theme = 1;
    GameView.MOVE_SCALE = 7;
    speedup = false;
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
