package com.example.nks.discgolfscorecard;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.nks.discgolfscorecard.R.id.Par;
import static com.example.nks.discgolfscorecard.R.id.lastHole;

public class MainScreen extends Activity implements View.OnClickListener {

   TextView scoreCount;
    TextView numberOfHole;
    TextView ParValue;
    private Button nextHole;
    private Button lastHole;
    private Button counter;
    private Button reset;
    int hole = 1;
    int holePar = 3;
    int holeLenght = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        nextHole = (Button) findViewById(R.id.nextHole);
        nextHole.setOnClickListener(MainScreen.this);
        lastHole = (Button) findViewById(R.id.lastHole);
        lastHole.setOnClickListener(MainScreen.this);
        counter = (Button) findViewById(R.id.counterUpID);
        counter.setOnClickListener(MainScreen.this);
        reset = (Button) findViewById(R.id.resetBtnID);
        reset.setOnClickListener(MainScreen.this);
        scoreCount = (TextView) findViewById(R.id.scoreCountID);
        numberOfHole = (TextView) findViewById(R.id.numberOfHoleID);
        Button lastHole = (Button) findViewById(R.id.nextHole);
        lastHole.setOnClickListener(this);
        ParValue = (TextView) findViewById(R.id.ParValue);

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

                }
            }
        }

/*public CourseBuilder(int holes, int par, int distance) {
    holes = hole;
    par = holePar;
    distance = holeLenght;

}
*/