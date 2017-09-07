package com.example.nks.discgolfscorecard;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainScreen extends AppCompatActivity {

    Button counterUp;
    Button resetBtn;
    Button holeCounterUp;
    TextView scoreCount;
    TextView numberOfHole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        holeCounterUp = (Button) findViewById(R.id.holeCounterUpID);
        counterUp = (Button) findViewById(R.id.counterUpID);
        resetBtn = (Button) findViewById(R.id.resetBtnID);
        scoreCount = (TextView) findViewById(R.id.scoreCountID);
        numberOfHole = (TextView) findViewById(R.id.numberOfHoleID);

        counterUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scoreValue = scoreCount.getText().toString();
                int intScoreValue = Integer.parseInt(scoreValue);
                intScoreValue++;
                scoreCount.setText(String.valueOf(intScoreValue));
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreCount.setText("0");
            }
        });

        holeCounterUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String holeValue = numberOfHole.getText().toString();
                int holeNumberInteger = Integer.parseInt(holeValue);
                holeNumberInteger++;
                numberOfHole.setText("Hole" + Integer.toString(holeNumberInteger));
            }
        });
    }
}