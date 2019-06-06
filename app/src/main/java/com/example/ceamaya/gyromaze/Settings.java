package com.example.ceamaya.gyromaze;

import static com.example.ceamaya.gyromaze.GameView.THEME_GAME;
import static com.example.ceamaya.gyromaze.GameView.THEME_GOLF;
import static com.example.ceamaya.gyromaze.GameView.THEME_OLD_SCHOOL;
import static com.example.ceamaya.gyromaze.MainActivity.speedup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    super.setContentView(R.layout.activity_settings);
    RadioGroup radioGroup = findViewById(R.id.radioGroup);
    switch (MainActivity.theme) {
      case 1:
        radioGroup.check(R.id.videoGameTheme);
        break;
      case 2:
        radioGroup.check(R.id.golfTheme);
        break;
      default:
        radioGroup.check(R.id.oldSchoolTheme);
        break;
    }
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
          case R.id.videoGameTheme:
            MainActivity.theme = THEME_GAME;
            break;
          case R.id.golfTheme:
            MainActivity.theme = THEME_GOLF;
            break;
          default:
            MainActivity.theme = THEME_OLD_SCHOOL;
            break;
        }
      }
    });
    Switch speedSwitch = findViewById(R.id.speed_switch);
    speedSwitch.setChecked(speedup);
    speedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        speedup = !speedup;
        if (speedup) {
          GameView.MOVE_SCALE = 14;
        } else {
          GameView.MOVE_SCALE = 7;
        }
      }
    });
  }

}
