package com.example.ceamaya.gyromaze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LevelActivity extends AppCompatActivity {

    public static final String EXTRA_LEVEL_NUMBER = "EXTRA_LEVEL_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    public void levelClick(View view) {
        int id =  view.getId();
        int levelNumber = 1;
        switch (id) {
            case R.id.level_1_button:
                levelNumber = Levels.level1.levelNumber;
                break;
            case R.id.level_2_button:
                levelNumber = Levels.level2.levelNumber;
                break;
            case R.id.level_3_button:
                levelNumber = Levels.level3.levelNumber;
                break;
        }
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_LEVEL_NUMBER, levelNumber);
        startActivity(intent);
    }
}
