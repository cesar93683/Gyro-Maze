package com.example.ceamaya.gyromaze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    public void levelClick(View view) {
        int id =  view.getId();
        switch (id) {
            case R.id.level_1_button:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.level_2_button:

                break;
            case R.id.level_3_button:

                break;
        }
    }
}
