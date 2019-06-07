package com.gyromaze;

import static com.gyromaze.GameView.DEFAULT_SCALE;
import static com.gyromaze.GameView.THEME_GAME;
import static com.gyromaze.GameView.THEME_GOLF;
import static com.gyromaze.GameView.THEME_OLD_SCHOOL;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

  public static final String PREF_THEME = "PREF_THEME";
  public static final String PREF_MOVE_SCALE = "PREF_MOVE_SCALE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    super.setContentView(R.layout.activity_settings);
    RadioGroup radioGroup = findViewById(R.id.radioGroup);
    switch (GameView.THEME) {
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
            GameView.THEME = THEME_GAME;
            break;
          case R.id.golfTheme:
            GameView.THEME = THEME_GOLF;
            break;
          default:
            GameView.THEME = THEME_OLD_SCHOOL;
            break;
        }
        PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this)
            .edit()
            .putInt(PREF_THEME, GameView.THEME)
            .apply();
      }
    });

    Switch speedSwitch = findViewById(R.id.speed_switch);
    speedSwitch.setChecked(GameView.MOVE_SCALE != DEFAULT_SCALE);
    speedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (GameView.MOVE_SCALE == DEFAULT_SCALE) {
          GameView.MOVE_SCALE = DEFAULT_SCALE * 2;
        } else {
          GameView.MOVE_SCALE = DEFAULT_SCALE;
        }
        PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this)
            .edit()
            .putInt(PREF_MOVE_SCALE, GameView.MOVE_SCALE)
            .apply();
      }
    });
  }

}
