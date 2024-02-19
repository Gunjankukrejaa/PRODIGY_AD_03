package com.example.stopwatch;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView timerTextView;
    private Button startButton, stopButton;
    private CountDownTimer countDownTimer;
    private long totalTimeInMillis;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerRunning) {
                    startTimer();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
    }

    private void startTimer() {
        totalTimeInMillis = 60000; // Set your desired total time in milliseconds here (e.g., 1 minute = 60000 milliseconds)
        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                totalTimeInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateTimer();
            }
        }.start();

        timerRunning = true;
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            timerRunning = false;
            updateTimer();
        }
    }

    private void updateTimer() {
        int minutes = (int) (totalTimeInMillis / 1000) / 60;
        int seconds = (int) (totalTimeInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }
}