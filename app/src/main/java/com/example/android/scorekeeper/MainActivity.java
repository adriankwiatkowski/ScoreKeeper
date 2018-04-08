package com.example.android.scorekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button basketballButton, footballButton, rugbyButton, scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basketballButton = findViewById(R.id.basketball_button);
        basketballButton.setOnClickListener(this);
        footballButton = findViewById(R.id.football_button);
        footballButton.setOnClickListener(this);
        rugbyButton = findViewById(R.id.rugby_button);
        rugbyButton.setOnClickListener(this);
        scoreButton = findViewById(R.id.score_button);
        scoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basketball_button:
                Intent basketballIntent = new Intent(this, BasketballActivity.class);
                startActivity(basketballIntent);
                break;
            case R.id.football_button:
                Intent footballIntent = new Intent(this, FootballActivity.class);
                startActivity(footballIntent);
                break;
            case R.id.rugby_button:
                Intent rugbyIntent = new Intent(this, RugbyActivity.class);
                startActivity(rugbyIntent);
                break;
            case R.id.score_button:
                Intent scoreIntent = new Intent(this, StatisticsActivity.class);
                startActivity(scoreIntent);
                break;
        }
    }
}
