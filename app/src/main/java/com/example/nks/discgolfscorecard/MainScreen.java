package com.example.nks.discgolfscorecard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends Activity implements View.OnClickListener {

   TextView scoreCount, numberOfHole, ParValue;
    private Button counter, reset, lastHole, nextHole, courseAdder;
    int hole = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        nextHole = findViewById(R.id.nextHole);
        nextHole.setOnClickListener(MainScreen.this);
        lastHole = findViewById(R.id.lastHole);
        lastHole.setOnClickListener(MainScreen.this);
        counter = findViewById(R.id.counterUpID);
        counter.setOnClickListener(MainScreen.this);
        reset = findViewById(R.id.resetBtnID);
        courseAdder = findViewById(R.id.courseAdder);
        courseAdder.setOnClickListener(MainScreen.this);
        reset.setOnClickListener(MainScreen.this);
        scoreCount = findViewById(R.id.scoreCountID);
        numberOfHole = findViewById(R.id.numberOfHoleID);
        Button lastHole = findViewById(R.id.nextHole);
        lastHole.setOnClickListener(this);
        ParValue = findViewById(R.id.ParValue);

    }
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.counterUpID:
                        String score = scoreCount.getText().toString();
                        int holePar = Integer.parseInt(score);
                        holePar++;
                        scoreCount.setText(String.valueOf(holePar));
                        break;
                    case R.id.resetBtnID:
                        scoreCount.setText("0");
                        break;

                    case R.id.nextHole:
                        if (hole < 18) {
                            hole++;
                            numberOfHole.setText(String.valueOf(hole));
                            holePar = 3;
                            scoreCount.setText(String.valueOf(holePar));
                            ParValue.setText(String.valueOf(holePar));
                            break;
                        }

                    case R.id.lastHole:
                        if (hole > 0) {
                            hole--;
                            numberOfHole.setText(String.valueOf(hole));
                            holePar = 3;
                            scoreCount.setText(String.valueOf(holePar));
                            ParValue.setText(String.valueOf(holePar));
                            break;
                        }
                    case R.id.courseAdder:
                        Intent CourseBuilder = new Intent(MainScreen.this, CourseBuilderActivity.class);
                        startActivity(CourseBuilder);
                }
            }
}


