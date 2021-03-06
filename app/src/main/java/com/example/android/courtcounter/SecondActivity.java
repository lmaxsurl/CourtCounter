package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null){
            ((TextView)findViewById(R.id.team_A)).setText(arguments.getString("teamA"));
            ((TextView)findViewById(R.id.team_B)).setText(arguments.getString("teamB"));
            ((TextView)findViewById(R.id.teamA_points)).setHint(arguments.getString("teamA"));
            ((TextView)findViewById(R.id.teamB_points)).setHint(arguments.getString("teamB"));
            time = arguments.getLong("time");
        }
    }

    LinkedList<Score> scoresList = new LinkedList<>();
    int score_A = 0;
    int score_B = 0;
    final int TREE_POINTS = 3;
    final int TWO_POINTS = 2;
    final int ONE_POINT = 1;
    long time;

    public void reset(View view){
        score_A = score_B = 0;
        display_A(0);
        display_B(0);
        scoresList.clear();
    }

    public void returnScore(View view){
        if(!scoresList.isEmpty()) {
            Score previousScore = scoresList.getLast();
            previousScore.getTextView().setText("" + previousScore.scores);
            score_A = Integer.parseInt(((TextView)findViewById(R.id.teamA_points)).getText().toString());
            score_B = Integer.parseInt(((TextView)findViewById(R.id.teamB_points)).getText().toString());
            scoresList.removeLast();
        } else {
            Toast.makeText(this, "All changes are cancelled!", Toast.LENGTH_SHORT).show();
        }
    }

    public void press_3_points_A(View view){
        display_A(TREE_POINTS);
    }

    public void press_2_points_A(View view){
        display_A(TWO_POINTS);
    }

    public void press_1_point_A(View view){
        display_A(ONE_POINT);
    }

    public void press_3_points_B(View view){
        display_B(TREE_POINTS);
    }

    public void press_2_points_B(View view){
        display_B(TWO_POINTS);
    }

    public void press_1_point_B(View view){
        display_B(ONE_POINT);
    }

    private void display_A(int points){
        TextView textView = (TextView)findViewById(R.id.teamA_points);
        scoresList.add(new Score(score_A, textView, System.currentTimeMillis() - time));
        score_A += points;
        textView.setText("" + score_A);
    }

    private void display_B(int points){
        TextView textView = (TextView)findViewById(R.id.teamB_points);
        scoresList.add(new Score(score_B, textView, System.currentTimeMillis() - time));
        score_B += points;
        textView.setText("" + score_B);
    }

    class Score{
        private final TextView textView;
        private final int scores;
        private final long time;

        Score(int scores, TextView textView, long time){
            this.scores = scores;
            this.textView = textView;
            this.time = time;
        }

        @Override
        public String toString(){
            Score score = scoresList.getFirst();
            return "" + score.getTextView().getHint() + " : " + score.getScores() + "; " + score.getTime();
        }

        public TextView getTextView(){
            return textView;
        }

        public int getScores(){
            return scores;
        }

        public long getTime(){
            return time;
        }
    }
}
