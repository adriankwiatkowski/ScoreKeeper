package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RugbyActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    static String RUGBY_SHARED_PREFERENCES_KEY = "rugbySharedPreferences";
    static String RUGBY_TEAM_A_SCORE = "rugbyScoreA";
    static String RUGBY_TEAM_A_CONVERSION = "rugbyConversionA";
    static String RUGBY_TEAM_A_PENALTY = "rugbyPenaltyA";
    static String RUGBY_TEAM_A_DROP_GOAL = "rugbyDropGoalA";
    static String RUGBY_TEAM_A_TRY = "rugbyTryA";
    static String RUGBY_TEAM_B_SCORE = "rugbyScoreB";
    static String RUGBY_TEAM_B_CONVERSION = "rugbyConversionB";
    static String RUGBY_TEAM_B_PENALTY = "rugbyPenaltyB";
    static String RUGBY_TEAM_B_DROP_GOAL = "rugbyDropGoalB";
    static String RUGBY_TEAM_B_TRY = "rugbyTryB";

    int scoreTeamA, scoreTeamB, conversionTeamA, conversionTeamB, penaltyTeamA, penaltyTeamB
            ,dropGoalTeamA, dropGoalTeamB, tryTeamA, tryTeamB;

    Button buttonReset;
    Button buttonLeftConversion, buttonLeftPenalty, buttonLeftDropGoal, buttonLeftTry;
    Button buttonRightConversion, buttonRightPenalty, buttonRightDropGoal, buttonRightTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rugby);

        loadDataFromPreferences();

        buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(this);
        buttonLeftConversion = findViewById(R.id.button_conversion_a);
        buttonLeftConversion.setOnClickListener(this);
        buttonLeftPenalty = findViewById(R.id.button_penalty_a);
        buttonLeftPenalty.setOnClickListener(this);
        buttonLeftDropGoal = findViewById(R.id.button_drop_goal_a);
        buttonLeftDropGoal.setOnClickListener(this);
        buttonLeftTry = findViewById(R.id.button_try_a);
        buttonLeftTry.setOnClickListener(this);
        buttonRightConversion = findViewById(R.id.button_conversion_b);
        buttonRightConversion.setOnClickListener(this);
        buttonRightPenalty = findViewById(R.id.button_penalty_b);
        buttonRightPenalty.setOnClickListener(this);
        buttonRightDropGoal = findViewById(R.id.button_drop_goal_b);
        buttonRightDropGoal.setOnClickListener(this);
        buttonRightTry = findViewById(R.id.button_try_b);
        buttonRightTry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_conversion_a:
                conversionTeamA++;
                scoreTeamA += 2;
                break;
            case R.id.button_penalty_a:
                penaltyTeamA++;
                scoreTeamA += 3;
                break;
            case R.id.button_drop_goal_a:
                dropGoalTeamA++;
                scoreTeamA += 3;
                break;
            case R.id.button_try_a:
                tryTeamA++;
                scoreTeamA += 5;
                break;
            case R.id.button_conversion_b:
                conversionTeamB++;
                scoreTeamB += 2;
                break;
            case R.id.button_penalty_b:
                penaltyTeamB++;
                scoreTeamB += 3;
                break;
            case R.id.button_drop_goal_b:
                dropGoalTeamB++;
                scoreTeamB += 3;
                break;
            case R.id.button_try_b:
                tryTeamB++;
                scoreTeamB += 5;
                break;
            case R.id.button_reset:
                resetScore();
                break;
        }
    }

    private void loadDataFromPreferences() {
        mSharedPreferences = getSharedPreferences(RugbyActivity.RUGBY_SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        scoreTeamA = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_SCORE, 0);
        conversionTeamA = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_CONVERSION, 0);
        penaltyTeamA = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_PENALTY, 0);
        dropGoalTeamA = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_DROP_GOAL, 0);
        tryTeamA = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_TRY, 0);
        scoreTeamB = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_SCORE, 0);
        conversionTeamB = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_CONVERSION, 0);
        penaltyTeamB = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_PENALTY, 0);
        dropGoalTeamB = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_DROP_GOAL, 0);
        tryTeamB = mSharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_TRY, 0);
    }

    private void resetScore() {
        scoreTeamA = 0;
        conversionTeamA = 0;
        penaltyTeamA = 0;
        dropGoalTeamA = 0;
        tryTeamA = 0;
        scoreTeamB = 0;
        conversionTeamB = 0;
        penaltyTeamB = 0;
        dropGoalTeamB = 0;
        tryTeamB = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(RUGBY_TEAM_A_SCORE, scoreTeamA);
        mEditor.putInt(RUGBY_TEAM_A_CONVERSION, conversionTeamA);
        mEditor.putInt(RUGBY_TEAM_A_PENALTY, penaltyTeamA);
        mEditor.putInt(RUGBY_TEAM_A_DROP_GOAL, dropGoalTeamA);
        mEditor.putInt(RUGBY_TEAM_A_TRY, tryTeamA);
        mEditor.putInt(RUGBY_TEAM_B_SCORE, scoreTeamB);
        mEditor.putInt(RUGBY_TEAM_B_CONVERSION, conversionTeamB);
        mEditor.putInt(RUGBY_TEAM_B_PENALTY, penaltyTeamB);
        mEditor.putInt(RUGBY_TEAM_B_DROP_GOAL, dropGoalTeamB);
        mEditor.putInt(RUGBY_TEAM_B_TRY, tryTeamB);
        mEditor.apply();
    }
}
