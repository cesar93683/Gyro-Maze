package com.example.ceamaya.gyromaze;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import static com.example.ceamaya.gyromaze.MainActivity.speedup;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_settings);
        RadioGroup radioGroup =  findViewById(R.id.radioGroup);
        if(MainActivity.theme == 1) {
            radioGroup.check(R.id.videoGameTheme);
        } else if(MainActivity.theme == 2) {
            radioGroup.check(R.id.golfTheme);
        } else {
            radioGroup.check(R.id.oldSchoolTheme);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.videoGameTheme) {
                    MainActivity.theme = 1;
                }else if(checkedId == R.id.golfTheme) {
                    MainActivity.theme = 2;
                } else {
                    MainActivity.theme = 3;
                }
            }
        });
        Switch speedSwitch = findViewById(R.id.speed_switch);
        speedSwitch.setChecked(speedup);
        speedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                speedup = !speedup;
                if(speedup) {
                    GameView.MOVE_SCALE = 14;
                } else {
                    GameView.MOVE_SCALE = 7;
                }
            }
        });
    }

}
