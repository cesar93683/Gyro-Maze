package com.example.ceamaya.gyromaze;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LevelAdapter extends ArrayAdapter<Integer> {

    private Context context;
    private ArrayList<Integer> bestTimes;

    public LevelAdapter(@NonNull Context context, ArrayList<Integer> bestTimes) {
        super(context, 0, bestTimes);
        this.context = context;
        this.bestTimes = bestTimes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.level_list_item, parent, false);
        }

        int levelNumber = position + 1;

        TextView levelTextView = convertView.findViewById(R.id.level_text_view);
        levelTextView.setText(String.format("Level %d", levelNumber));

        int bestTime = bestTimes.get(position);

        TextView bestTimeTextView = convertView.findViewById(R.id.best_time_text_view);

        switch (bestTime) {
            case LevelActivity.LEVEL_LOCKED:
                bestTimeTextView.setText("Locked.");
                setStars(0, convertView);
                break;
            case LevelActivity.LEVEL_UNLOCKED:
                bestTimeTextView.setText("Not completed yet.");
                setStars(0, convertView);
                break;
            default:
                int numOfStars = Levels.getLevelByNumber(levelNumber).getNumStars(bestTime);
                setStars(numOfStars, convertView);
                double timeInSeconds = (double) bestTime / 1000.0;
                bestTimeTextView.setText(String.format("Best Time: %s seconds", timeInSeconds));
                break;
        }
        return convertView;
    }

    private void setStars(int numYellowStars, View view) {
        ImageView starOne = view.findViewById(R.id.star_one);
        ImageView starTwo = view.findViewById(R.id.star_two);
        ImageView starThree = view.findViewById(R.id.star_three);
        if (numYellowStars >= 1) {
            starOne.setBackgroundResource(R.drawable.ic_star_yellow);
            if (numYellowStars >= 2) {
                starTwo.setBackgroundResource(R.drawable.ic_star_yellow);
            }
            if (numYellowStars >= 3) {
                starThree.setBackgroundResource(R.drawable.ic_star_yellow);
            }
        } else {
            starOne.setBackgroundResource(R.drawable.ic_star_gray);
            starTwo.setBackgroundResource(R.drawable.ic_star_gray);
            starThree.setBackgroundResource(R.drawable.ic_star_gray);
        }
    }
}

