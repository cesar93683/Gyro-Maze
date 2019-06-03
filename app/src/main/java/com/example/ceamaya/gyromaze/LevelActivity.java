package com.example.ceamaya.gyromaze;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LevelActivity extends AppCompatActivity {

    public static final String EXTRA_LEVEL_NUMBER = "EXTRA_LEVEL_NUMBER";
    private ArrayList<Integer> bestTimes;
    public static final int LEVEL_UNLOCKED = -1;
    public static final int LEVEL_LOCKED = -2;
    public static final String BEST_TIMES = "BEST_TIMES";
    private LevelAdapter levelAdapter;
    private final int REQ_CODE_PLAY = 1;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        activity = this;
        bestTimes = new ArrayList<>();

        setUpBestTimes();
        levelAdapter = new LevelAdapter(this, bestTimes);

        ListView levelListView = findViewById(R.id.level_list_view);
        levelListView.setAdapter(levelAdapter);
        levelListView.setOnItemClickListener(levelClickListener());
    }

    @NonNull
    private AdapterView.OnItemClickListener levelClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bestTimes.get(position) != LEVEL_LOCKED) {
                    Intent intent = new Intent(activity, GameActivity.class);
                    intent.putExtra(EXTRA_LEVEL_NUMBER, position + 1);
                    activity.startActivityForResult(intent, REQ_CODE_PLAY);
                } else {
                    Toast.makeText(activity, "Level locked", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private void setUpBestTimes() {
        SharedPreferences prefs = getSharedPreferences(BEST_TIMES, MODE_PRIVATE);
        bestTimes.clear();
        bestTimes.add(prefs.getInt(Integer.toString(1), LEVEL_UNLOCKED));
        for (int i = 2; i <= 10; i++) {
            bestTimes.add(prefs.getInt(Integer.toString(i), LEVEL_LOCKED));
//            bestTimes.add(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setUpBestTimes();
        levelAdapter.notifyDataSetChanged();
    }
}
