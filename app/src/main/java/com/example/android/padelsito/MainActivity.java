package com.example.android.padelsito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Current game score.
    private int teamA = 0;
    private int teamB = 0;
    private boolean advantageA = false;
    private boolean advantageB = false;
    // Total games score.
    private int gameA = 0;
    private int gameB = 0;
    // Total sets score.
    private int setA = 0;
    private int setB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Add point to Team A.
    public void addPointA(View view) {
        if (teamA < 30) {
            teamA += 15;
        } else if (teamA == 30) {
            teamA = 40;
        } else if (teamA == 40) {
            if (teamB != 40) {
                // Team A wins.
                gameA++;
                newGame();
                checkGame();
                Toast.makeText(this, "Team A won a game!", Toast.LENGTH_SHORT).show();
            } else {
                if (advantageA) {
                    // Team A wins.
                    gameA++;
                    newGame();
                    checkGame();
                    Toast.makeText(this, "Team A won a game!", Toast.LENGTH_SHORT).show();
                } else if (!advantageB) {
                    // Advantage for team A
                    advantageA = true;
                } else if (advantageB) {
                    // Forty all
                    advantageB = false;
                }
            }
            displayScoreB();
        }
        displayScoreA();
    }

    // Add point to Team B.
    public void addPointB(View view) {
        if (teamB < 30) {
            teamB += 15;
        } else if (teamB == 30) {
            teamB = 40;
        } else if (teamB == 40) {
            if (teamA != 40) {
                // Team B wins.
                gameB++;
                newGame();
                checkGame();
                Toast.makeText(this, "Team B won a game!", Toast.LENGTH_SHORT).show();
            } else {
                if (advantageB) {
                    // Team B wins.
                    gameB++;
                    newGame();
                    checkGame();
                    Toast.makeText(this, "Team B won a game!", Toast.LENGTH_SHORT).show();
                } else if (!advantageA) {
                    // Advantage for team B.
                    advantageB = true;
                } else if (advantageA) {
                    // Forty all.
                    advantageA = false;
                }
            }
            displayScoreA();
        }
        displayScoreB();
    }

    // Remove point from Team A (In case of error).
    public void removePointA(View view) {
        if ((teamA > 0) && (teamA <= 30)) {
            teamA += -15;
        } else if (teamA == 40) {
            if (advantageA) {
               advantageA = false;
            } else {
                teamA = 30;
            }
        }
        displayScoreA();
    }

    // Remove point from Team B (In case of error).
    public void removePointB(View view) {
        if ((teamB > 0) && (teamB <= 30)) {
            teamB += -15;
        } else if (teamB == 40) {
            if (advantageB) {
                advantageB = false;
            } else {
                teamB = 30;
            }
        }
        displayScoreB();
    }

    // Display Team A score.
    private void displayScoreA() {
        TextView scoreView = (TextView) findViewById(R.id.score_a_text_view);
        if (teamA == 40 && advantageA) {
            scoreView.setText("A");
        } else {
            scoreView.setText(String.valueOf(teamA));
        }
    }

    // Display Team B score.
    private void displayScoreB() {
        TextView scoreView = (TextView) findViewById(R.id.score_b_text_view);
        if (teamB == 40 && advantageB) {
            scoreView.setText("A");
        } else {
            scoreView.setText(String.valueOf(teamB));
        }
    }

    // Reset scores.
    public void reset(View view) {
        teamA = 0;
        teamB = 0;
        advantageA = false;
        advantageB = false;
        gameA = 0;
        gameB = 0;
        setA = 0;
        setB = 0;
        displayScoreA();
        displayScoreB();
        displayGames();
        displaySets();
    }

    // New game.
    private void newGame() {
        teamA = 0;
        teamB = 0;
        advantageA = false;
        advantageB = false;
    }

    // Check game.
    private void checkGame() {
        if ((gameA >= 6) && ((gameA - gameB) >= 2)) {
            // Team A wins.
            setA++;
            displaySets();
            gameA = 0;
            gameB = 0;
            Toast.makeText(this, "Team A won a set. Congrats!", Toast.LENGTH_LONG).show();
        } else if ((gameB >= 6) && ((gameB - gameA) >= 2)) {
            // Team B wins.
            setB++;
            displaySets();
            gameA = 0;
            gameB = 0;
            Toast.makeText(this, "Team B won a set. Congrats!", Toast.LENGTH_LONG).show();
        }
        displayGames();
    }

    // Display games.
    private void displayGames() {
        TextView gameViewA = (TextView) findViewById(R.id.game_a_text_view);
        gameViewA.setText(String.valueOf(gameA));

        TextView gameViewB = (TextView) findViewById(R.id.game_b_text_view);
        gameViewB.setText(String.valueOf(gameB));
    }

    // Display sets.
    private void displaySets() {
        TextView setViewA = (TextView) findViewById(R.id.set_a_text_view);
        setViewA.setText("Total sets won: " + setA);

        TextView setViewB = (TextView) findViewById(R.id.set_b_text_view);
        setViewB.setText("Total sets won: " + setB);
    }

}