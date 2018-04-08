package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FootballActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    static String FOOTBALL_SHARED_PREFERENCES_KEY = "footballSharedPreferences";
    static String FOOTBALL_TEAM_A_SCORE = "footballScoreA";
    static String FOOTBALL_TEAM_A_FOUL = "footballFoulA";
    static String FOOTBALL_TEAM_A_CORNER_KICK = "footballCornerKickA";
    static String FOOTBALL_TEAM_A_OFFSIDE = "footballOffsideA";
    static String FOOTBALL_TEAM_A_YELLOW_CARD = "footballYellowCardA";
    static String FOOTBALL_TEAM_A_RED_CARD = "footballRedCardA";
    static String FOOTBALL_TEAM_A_GOALKEEPER_SAVE = "footballGoalkeeperSaveA";
    static String FOOTBALL_TEAM_B_SCORE = "footballScoreB";
    static String FOOTBALL_TEAM_B_FOUL = "footballFoulB";
    static String FOOTBALL_TEAM_B_CORNER_KICK = "footballCornerKickB";
    static String FOOTBALL_TEAM_B_OFFSIDE = "footballOffsideB";
    static String FOOTBALL_TEAM_B_YELLOW_CARD = "footballYellowCardB";
    static String FOOTBALL_TEAM_B_RED_CARD = "footballRedCardB";
    static String FOOTBALL_TEAM_B_GOALKEEPER_SAVE = "footballGoalkeeperSaveB";

    int scoreTeamA;
    int foulTeamA;
    int cornerKickTeamA;
    int offsideTeamA;
    int yellowCardTeamA;
    int redCardTeamA;
    int goalkeeperSaveTeamA;
    int scoreTeamB;
    int foulTeamB;
    int cornerKickTeamB;
    int offsideTeamB;
    int yellowCardTeamB;
    int redCardTeamB;
    int goalkeeperSaveTeamB;

    Button buttonReset;
    Button goalButtonA, foulButtonA, cornerKickButtonA, offsideButtonA, yellowCardButtonA, redCardButtonA, goalkeeperSaveButtonA;
    Button goalButtonB, foulButtonB, cornerKickButtonB, offsideButtonB, yellowCardButtonB, redCardButtonB, goalkeeperSaveButtonB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        loadDataFromPreferences();

        buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(this);
        goalButtonA = findViewById(R.id.button_goal_a);
        goalButtonA.setOnClickListener(this);
        foulButtonA = findViewById(R.id.button_foul_a);
        foulButtonA.setOnClickListener(this);
        cornerKickButtonA = findViewById(R.id.button_corner_a);
        cornerKickButtonA.setOnClickListener(this);
        offsideButtonA = findViewById(R.id.button_offside_a);
        offsideButtonA.setOnClickListener(this);
        yellowCardButtonA = findViewById(R.id.button_yellow_card_a);
        yellowCardButtonA.setOnClickListener(this);
        redCardButtonA = findViewById(R.id.button_red_card_a);
        redCardButtonA.setOnClickListener(this);
        goalkeeperSaveButtonA = findViewById(R.id.button_goalkeeper_save_a);
        goalkeeperSaveButtonA.setOnClickListener(this);
        goalButtonB = findViewById(R.id.button_goal_b);
        goalButtonB.setOnClickListener(this);
        foulButtonB = findViewById(R.id.button_foul_b);
        foulButtonB.setOnClickListener(this);
        cornerKickButtonB = findViewById(R.id.button_corner_b);
        cornerKickButtonB.setOnClickListener(this);
        offsideButtonB = findViewById(R.id.button_offside_b);
        offsideButtonB.setOnClickListener(this);
        yellowCardButtonB = findViewById(R.id.button_yellow_card_b);
        yellowCardButtonB.setOnClickListener(this);
        redCardButtonB = findViewById(R.id.button_red_card_b);
        redCardButtonB.setOnClickListener(this);
        goalkeeperSaveButtonB = findViewById(R.id.button_goalkeeper_save_b);
        goalkeeperSaveButtonB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_goal_a:
                scoreTeamA++;
                break;
            case R.id.button_foul_a:
                foulTeamA++;
                break;
            case R.id.button_corner_a:
                cornerKickTeamA++;
                break;
            case R.id.button_offside_a:
                offsideTeamA++;
                break;
            case R.id.button_yellow_card_a:
                yellowCardTeamA++;
                break;
            case R.id.button_red_card_a:
                redCardTeamA++;
                break;
            case R.id.button_goalkeeper_save_a:
                goalkeeperSaveTeamA++;
                break;
            case R.id.button_goal_b:
                scoreTeamB++;
                break;
            case R.id.button_foul_b:
                foulTeamB++;
                break;
            case R.id.button_corner_b:
                cornerKickTeamB++;
                break;
            case R.id.button_offside_b:
                offsideTeamB++;
                break;
            case R.id.button_yellow_card_b:
                yellowCardTeamB++;
                break;
            case R.id.button_red_card_b:
                redCardTeamB++;
                break;
            case R.id.button_goalkeeper_save_b:
                goalkeeperSaveTeamB++;
                break;
            case R.id.button_reset:
                resetScore();
                break;
        }
    }

    private void loadDataFromPreferences() {
        mSharedPreferences = getSharedPreferences(FootballActivity.FOOTBALL_SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        scoreTeamA = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_SCORE, 0);
        foulTeamA = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_FOUL, 0);
        cornerKickTeamA = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_CORNER_KICK, 0);
        offsideTeamA = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_OFFSIDE, 0);
        yellowCardTeamA = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_YELLOW_CARD, 0);
        redCardTeamA = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_RED_CARD, 0);
        goalkeeperSaveTeamA = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_GOALKEEPER_SAVE, 0);
        scoreTeamB = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_SCORE, 0);
        foulTeamB = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_FOUL, 0);
        cornerKickTeamB = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_CORNER_KICK, 0);
        offsideTeamB = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_OFFSIDE, 0);
        yellowCardTeamB = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_YELLOW_CARD, 0);
        redCardTeamB = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_RED_CARD, 0);
        goalkeeperSaveTeamB = mSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_GOALKEEPER_SAVE, 0);
    }

    private void resetScore() {
        scoreTeamA = 0;
        foulTeamA = 0;
        cornerKickTeamA = 0;
        offsideTeamA = 0;
        yellowCardTeamA = 0;
        redCardTeamA = 0;
        goalkeeperSaveTeamA = 0;
        scoreTeamB = 0;
        foulTeamB = 0;
        cornerKickTeamB = 0;
        offsideTeamB = 0;
        yellowCardTeamB = 0;
        redCardTeamB = 0;
        goalkeeperSaveTeamB = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(FOOTBALL_TEAM_A_SCORE, scoreTeamA);
        mEditor.putInt(FOOTBALL_TEAM_A_FOUL, foulTeamA);
        mEditor.putInt(FOOTBALL_TEAM_A_CORNER_KICK, cornerKickTeamA);
        mEditor.putInt(FOOTBALL_TEAM_A_OFFSIDE, offsideTeamA);
        mEditor.putInt(FOOTBALL_TEAM_A_YELLOW_CARD, yellowCardTeamA);
        mEditor.putInt(FOOTBALL_TEAM_A_RED_CARD, redCardTeamA);
        mEditor.putInt(FOOTBALL_TEAM_A_GOALKEEPER_SAVE, goalkeeperSaveTeamA);
        mEditor.putInt(FOOTBALL_TEAM_B_SCORE, scoreTeamB);
        mEditor.putInt(FOOTBALL_TEAM_B_FOUL, foulTeamB);
        mEditor.putInt(FOOTBALL_TEAM_B_CORNER_KICK, cornerKickTeamB);
        mEditor.putInt(FOOTBALL_TEAM_B_OFFSIDE, offsideTeamB);
        mEditor.putInt(FOOTBALL_TEAM_B_YELLOW_CARD, yellowCardTeamB);
        mEditor.putInt(FOOTBALL_TEAM_B_RED_CARD, redCardTeamB);
        mEditor.putInt(FOOTBALL_TEAM_B_GOALKEEPER_SAVE, goalkeeperSaveTeamB);
        mEditor.apply();
    }
}
