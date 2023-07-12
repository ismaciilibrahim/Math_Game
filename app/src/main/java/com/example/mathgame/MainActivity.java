package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView score,life,time,question;
    int userscore,userlife=3;
    EditText answer;
    int useranswer;
    Button ok,next;
    Random random = new Random();
    int num1;
    int num2;
    int realasnwer;
    CountDownTimer timer;
    public static final long Start_time = 30000;
    long time_left = Start_time;
    boolean timeruning ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.textViewscore2);
        life = findViewById(R.id.textViewllifescore);
        time = findViewById(R.id.Time2);
        question = findViewById(R.id.textViewquestion);
        answer = findViewById(R.id.editTextAnswer);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonnextquestion);

        gameCountinue();
        StartTime();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useranswer = Integer.parseInt(answer.getText().toString());
                if (useranswer == realasnwer){
                    question.setText("congratulation your answer is right");
                    userscore = userscore +10;
                    score.setText(""+userscore);
                    pauseTime();
                }else {
                    question.setText("your answer is wrong");
                    userlife = userlife-1;
                    life.setText(""+userlife);
                    pauseTime();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameCountinue();
                time_left = Start_time;
                updateText();


            }
        });

    }



    private void gameCountinue() {
        num1 = random.nextInt(20);
        num2 = random.nextInt(20);
        realasnwer = num1 + num2;
        question.setText(""+num1 + " + " +num2);

    }

    private void StartTime() {
        timer = new CountDownTimer(time_left,1000) {
            @Override
            public void onTick(long l) {
                time_left = l;
                updateText();

            }

            @Override
            public void onFinish() {
                timeruning = false;
                pauseTime();
                resetTime();
                updateText();

            }
        }.start();
        timeruning = true;

    }
    private void resetTime() {
        time_left = Start_time;
        updateText();
    }

    private void updateText() {
        //code waxu meilisco ubadlaya minutes

        //code waxu meilisco ubadlaya seconds
        int second = (int) (time_left/1000) % 68;

        String time22 = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time22);

    }

    public  void  pauseTime(){
        timer.cancel();
        timeruning = false;
    }



}