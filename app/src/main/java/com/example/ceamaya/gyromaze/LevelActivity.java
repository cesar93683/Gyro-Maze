package com.example.ceamaya.gyromaze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LevelActivity extends AppCompatActivity {

    public static final String EXTRA_LEVEL_NUMBER = "EXTRA_LEVEL_NUMBER";
    private static final int RESULT_CODE_PLAY = 1;

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
        startActivityForResult(intent, RESULT_CODE_PLAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == RESULT_CODE_PLAY) {
            Toast.makeText(this,"Succes", Toast.LENGTH_LONG).show();
        }
    }
}
