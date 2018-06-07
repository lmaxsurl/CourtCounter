package com.example.android.courtcounter;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterTeams(View view){
        TextView teamA = (TextView)findViewById(R.id.teamA_et);
        TextView teamB = (TextView)findViewById(R.id.teamB_et);
        if(teamA.getText().length() <= 0 || teamB.getText().length()<= 0){
            findViewById(R.id.names_error).setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("teamA", teamA.getText().toString());
            intent.putExtra("teamB", teamB.getText().toString());
            intent.putExtra("time", System.currentTimeMillis());
            startActivity(intent);
        }
    }
}

