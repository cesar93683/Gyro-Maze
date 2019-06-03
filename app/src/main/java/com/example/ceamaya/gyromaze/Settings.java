package com.example.ceamaya.gyromaze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static com.example.ceamaya.gyromaze.MainActivity.speedup;

public class Settings extends AppCompatActivity {
    //final ConstraintLayout layout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_settings);

    }

    public void setVideogames(View v){
        MainActivity.theme = 1;
        Toast.makeText(getApplicationContext(),"Set to Video Game Theme", Toast.LENGTH_SHORT).show();
    }

    public void setGolf(View v){
        MainActivity.theme = 2;
        Toast.makeText(getApplicationContext(),"Set to Golf Theme", Toast.LENGTH_SHORT).show();
    }

    public void setOld(View v) {
        MainActivity.theme = 3;
        Toast.makeText(getApplicationContext(),"Set to Old School Theme", Toast.LENGTH_SHORT).show();
    }

    public void changeSpeed(View v){
        if(!speedup){
            GameView.MOVE_SCALE = 14;
            Toast.makeText(getApplicationContext(),"Speedup 2x!", Toast.LENGTH_SHORT).show();
            speedup = true;
        }
        else{
            GameView.MOVE_SCALE = 7;
            Toast.makeText(getApplicationContext(),"Default Speed", Toast.LENGTH_SHORT).show();
            speedup = false;
        }
    }
}
