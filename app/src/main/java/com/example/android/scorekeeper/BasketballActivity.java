package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BasketballActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    static String BASKETBALL_SHARED_PREFERENCES_KEY = "basketballSharedPreferences";
    static String BASKETBALL_TEAM_A_SCORE = "basketballScoreA";
    static String BASKETBALL_TEAM_A_FOUL = "basketballFoulA";
    static String BASKETBALL_TEAM_A_FREE_THROW = "basketballFreeThrowA";
    static String BASKETBALL_TEAM_B_SCORE = "basketballScoreB";
    static String BASKETBALL_TEAM_B_FOUL = "basketballFoulB";
    static String BASKETBALL_TEAM_B_FREE_THROW = "basketballFreeThrowB";

    int scoreTeamA, foulTeamA, freeThrowTeamA;
    int scoreTeamB, foulTeamB, freeThrowTeamB;

    Button buttonReset;
    Button buttonLeftFreeThrow, buttonLeftTwo, buttonLeftThree, buttonLeftFoul;
    Button buttonRightFreeThrow, buttonRightTwo, buttonRightThree, buttonRightFoul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);

        loadDataFromPreferences();

        buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(this);
        buttonLeftFreeThrow = findViewById(R.id.button_free_throw_a);
        buttonLeftFreeThrow.setOnClickListener(this);
        buttonLeftTwo = findViewById(R.id.button_two_points_a);
        buttonLeftTwo.setOnClickListener(this);
        buttonLeftThree = findViewById(R.id.button_three_points_a);
        buttonLeftThree.setOnClickListener(this);
        buttonLeftFoul = findViewById(R.id.button_foul_a);
        buttonLeftFoul.setOnClickListener(this);
        buttonRightFreeThrow = findViewById(R.id.button_free_throw_b);
        buttonRightFreeThrow.setOnClickListener(this);
        buttonRightTwo = findViewById(R.id.button_two_points_b);
        buttonRightTwo.setOnClickListener(this);
        buttonRightThree = findViewById(R.id.button_three_points_b);
        buttonRightThree.setOnClickListener(this);
        buttonRightFoul = findViewById(R.id.button_foul_b);
        buttonRightFoul.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_free_throw_a:
                scoreTeamA++;
                freeThrowTeamA++;
                break;
            case R.id.button_two_points_a:
                scoreTeamA += 2;
                break;
            case R.id.button_three_points_a:
                scoreTeamA += 3;
                break;
            case R.id.button_foul_a:
                foulTeamA++;
                break;
            case R.id.button_free_throw_b:
                scoreTeamB++;
                freeThrowTeamB++;
                break;
            case R.id.button_two_points_b:
                scoreTeamB += 2;
                break;
            case R.id.button_three_points_b:
                scoreTeamB += 3;
                break;
            case R.id.button_foul_b:
                foulTeamB++;
                break;
            case R.id.button_reset:
                resetScore();
                break;
        }
    }

    private void loadDataFromPreferences() {
        mSharedPreferences = getSharedPreferences(BASKETBALL_SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        scoreTeamA = mSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_A_SCORE, 0);
        freeThrowTeamA = mSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_A_FREE_THROW, 0);
        foulTeamA = mSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_A_FOUL, 0);
        scoreTeamB = mSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_B_SCORE, 0);
        freeThrowTeamB = mSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_B_FREE_THROW, 0);
        foulTeamB = mSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_B_FOUL, 0);
    }

    private void resetScore() {
        scoreTeamA = 0;
        foulTeamA = 0;
        freeThrowTeamA = 0;
        scoreTeamB = 0;
        foulTeamB = 0;
        freeThrowTeamB = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(BASKETBALL_TEAM_A_SCORE, scoreTeamA);
        mEditor.putInt(BASKETBALL_TEAM_A_FOUL, foulTeamA);
        mEditor.putInt(BASKETBALL_TEAM_A_FREE_THROW, freeThrowTeamA);
        mEditor.putInt(BASKETBALL_TEAM_B_SCORE, scoreTeamB);
        mEditor.putInt(BASKETBALL_TEAM_B_FOUL, foulTeamB);
        mEditor.putInt(BASKETBALL_TEAM_B_FREE_THROW, freeThrowTeamB);
        mEditor.apply();
    }
}
