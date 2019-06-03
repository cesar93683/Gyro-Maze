package com.example.ceamaya.gyromaze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import static com.example.ceamaya.gyromaze.MainActivity.speedup;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_settings);
        RadioGroup radioGroup =  findViewById(R.id.radioGroup);
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
                        MainActivity.theme = 1;
                        break;
                    case R.id.golfTheme:
                        MainActivity.theme = 2;
                        break;
                    default:
                        MainActivity.theme = 3;
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
                if(speedup) {
                    GameView.MOVE_SCALE = 14;
                } else {
                    GameView.MOVE_SCALE = 7;
                }
            }
        });
    }

}
