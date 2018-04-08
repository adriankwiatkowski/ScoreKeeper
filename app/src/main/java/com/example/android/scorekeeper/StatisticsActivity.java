package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    int basketballScoreA, basketballFreeThrowA, basketballFoulA, basketballScoreB, basketballFreeThrowB, basketballFoulB;
    int footballScoreA, footballFoulA, footballCornerA, footballOffsideA, footballYellowCardA,
            footballRedCardA, footballGoalkeeperSaveA;
    int footballScoreB, footballFoulB, footballCornerB, footballOffsideB, footballYellowCardB,
            footballRedCardB, footballGoalkeeperSaveB;
    int rugbyScoreA, rugbyConversionA, rugbyPenaltyA, rugbyDropGoalA, rugbyTryA
            ,rugbyScoreB, rugbyConversionB, rugbyPenaltyB, rugbyDropGoalB, rugbyTryB;
    TextView tvBasketScoreA, tvBasketFreeThrowA, tvBasketFoulA, tvBasketScoreB, tvBasketFreeThrowB, tvBasketFoulB;
    TextView tvFootScoreA, tvFootFoulA, tvFootCornerA, tvFootOffsideA, tvFootYellowA, tvFootRedA, tvFootGoalSaveA,
            tvFootScoreB, tvFootFoulB, tvFootCornerB, tvFootOffsideB, tvFootYellowB, tvFootRedB, tvFootGoalSaveB;
    TextView tvRugbyScoreA, tvRugbyConversionA, tvRugbyPenaltyA, tvRugbyDropGoalA, tvRugbyTryA
            ,tvRugbyScoreB, tvRugbyConversionB, tvRugbyPenaltyB, tvRugbyDropGoalB, tvRugbyTryB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tvBasketScoreA = findViewById(R.id.tv_basket_score_a);
        tvBasketFreeThrowA = findViewById(R.id.tv_basket_free_throw_a);
        tvBasketFoulA = findViewById(R.id.tv_basket_foul_a);
        tvBasketScoreB = findViewById(R.id.tv_basket_score_b);
        tvBasketFreeThrowB = findViewById(R.id.tv_basket_free_throw_b);
        tvBasketFoulB = findViewById(R.id.tv_basket_foul_b);

        SharedPreferences basketballSharedPreferences = getSharedPreferences(BasketballActivity.BASKETBALL_SHARED_PREFERENCES_KEY, MODE_PRIVATE);

        basketballScoreA = basketballSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_A_SCORE, 0);
        basketballFreeThrowA = basketballSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_A_FREE_THROW, 0);
        basketballFoulA = basketballSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_A_FOUL, 0);
        basketballScoreB = basketballSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_B_SCORE, 0);
        basketballFreeThrowB = basketballSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_B_FREE_THROW, 0);
        basketballFoulB = basketballSharedPreferences.getInt(BasketballActivity.BASKETBALL_TEAM_B_FOUL, 0);

        tvBasketScoreA.setText(String.valueOf(basketballScoreA));
        tvBasketFreeThrowA.setText(String.valueOf(basketballFreeThrowA));
        tvBasketFoulA.setText(String.valueOf(basketballFoulA));
        tvBasketScoreB.setText(String.valueOf(basketballScoreB));
        tvBasketFreeThrowB.setText(String.valueOf(basketballFreeThrowB));
        tvBasketFoulB.setText(String.valueOf(basketballFoulB));

        tvFootScoreA = findViewById(R.id.tv_foot_score_a);
        tvFootFoulA = findViewById(R.id.tv_foot_foul_a);
        tvFootCornerA = findViewById(R.id.tv_foot_corner_a);
        tvFootOffsideA = findViewById(R.id.tv_foot_offside_a);
        tvFootYellowA = findViewById(R.id.tv_foot_yellow_a);
        tvFootRedA = findViewById(R.id.tv_foot_red_a);
        tvFootGoalSaveA = findViewById(R.id.tv_foot_goalkeeper_save_a);
        tvFootScoreB = findViewById(R.id.tv_foot_score_b);
        tvFootFoulB = findViewById(R.id.tv_foot_foul_b);
        tvFootCornerB = findViewById(R.id.tv_foot_corner_b);
        tvFootOffsideB = findViewById(R.id.tv_foot_offside_b);
        tvFootYellowB = findViewById(R.id.tv_foot_yellow_b);
        tvFootRedB = findViewById(R.id.tv_foot_red_b);
        tvFootGoalSaveB = findViewById(R.id.tv_foot_goalkeeper_save_b);

        SharedPreferences footballSharedPreferences = getSharedPreferences(FootballActivity.FOOTBALL_SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        footballScoreA = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_SCORE, 0);
        footballFoulA = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_FOUL, 0);
        footballCornerA = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_CORNER_KICK, 0);
        footballOffsideA = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_OFFSIDE, 0);
        footballYellowCardA = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_YELLOW_CARD, 0);
        footballRedCardA = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_RED_CARD, 0);
        footballGoalkeeperSaveA = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_A_GOALKEEPER_SAVE, 0);
        footballScoreB = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_SCORE, 0);
        footballFoulB = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_FOUL, 0);
        footballCornerB = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_CORNER_KICK, 0);
        footballOffsideB = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_OFFSIDE, 0);
        footballYellowCardB = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_YELLOW_CARD, 0);
        footballRedCardB = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_RED_CARD, 0);
        footballGoalkeeperSaveB = footballSharedPreferences.getInt(FootballActivity.FOOTBALL_TEAM_B_GOALKEEPER_SAVE, 0);

        tvFootScoreA.setText(String.valueOf(footballScoreA));
        tvFootFoulA.setText(String.valueOf(footballFoulA));
        tvFootCornerA.setText(String.valueOf(footballCornerA));
        tvFootOffsideA.setText(String.valueOf(footballOffsideA));
        tvFootYellowA.setText(String.valueOf(footballYellowCardA));
        tvFootRedA.setText(String.valueOf(footballRedCardA));
        tvFootGoalSaveA.setText(String.valueOf(footballGoalkeeperSaveA));
        tvFootScoreB.setText(String.valueOf(footballScoreB));
        tvFootFoulB.setText(String.valueOf(footballFoulB));
        tvFootCornerB.setText(String.valueOf(footballCornerB));
        tvFootOffsideB.setText(String.valueOf(footballOffsideB));
        tvFootYellowB.setText(String.valueOf(footballYellowCardB));
        tvFootRedB.setText(String.valueOf(footballRedCardB));
        tvFootGoalSaveB.setText(String.valueOf(footballGoalkeeperSaveB));

        tvRugbyScoreA = findViewById(R.id.tv_rugby_score_a);
        tvRugbyConversionA = findViewById(R.id.tv_rugby_conversion_a);
        tvRugbyPenaltyA = findViewById(R.id.tv_rugby_penalty_a);
        tvRugbyDropGoalA = findViewById(R.id.tv_rugby_drop_goal_a);
        tvRugbyTryA = findViewById(R.id.tv_rugby_try_a);
        tvRugbyScoreB = findViewById(R.id.tv_rugby_score_b);
        tvRugbyConversionB = findViewById(R.id.tv_rugby_conversion_b);
        tvRugbyPenaltyB = findViewById(R.id.tv_rugby_penalty_b);
        tvRugbyDropGoalB = findViewById(R.id.tv_rugby_drop_goal_b);
        tvRugbyTryB = findViewById(R.id.tv_rugby_try_b);

        SharedPreferences rugbySharedPreferences = getSharedPreferences(RugbyActivity.RUGBY_SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        rugbyScoreA = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_SCORE, 0);
        rugbyConversionA = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_CONVERSION, 0);
        rugbyPenaltyA = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_PENALTY, 0);
        rugbyDropGoalA = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_DROP_GOAL, 0);
        rugbyTryA = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_A_TRY, 0);
        rugbyScoreB = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_SCORE, 0);
        rugbyConversionB = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_CONVERSION, 0);
        rugbyPenaltyB = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_PENALTY, 0);
        rugbyDropGoalB = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_DROP_GOAL, 0);
        rugbyTryB = rugbySharedPreferences.getInt(RugbyActivity.RUGBY_TEAM_B_TRY, 0);

        tvRugbyScoreA.setText(String.valueOf(rugbyScoreA));
        tvRugbyConversionA.setText(String.valueOf(rugbyConversionA));
        tvRugbyPenaltyA.setText(String.valueOf(rugbyPenaltyA));
        tvRugbyDropGoalA.setText(String.valueOf(rugbyDropGoalA));
        tvRugbyTryA.setText(String.valueOf(rugbyTryA));
        tvRugbyScoreB.setText(String.valueOf(rugbyScoreB));
        tvRugbyConversionB.setText(String.valueOf(rugbyConversionB));
        tvRugbyPenaltyB.setText(String.valueOf(rugbyPenaltyB));
        tvRugbyDropGoalB.setText(String.valueOf(rugbyDropGoalB));
        tvRugbyTryB.setText(String.valueOf(rugbyTryB));
    }
}