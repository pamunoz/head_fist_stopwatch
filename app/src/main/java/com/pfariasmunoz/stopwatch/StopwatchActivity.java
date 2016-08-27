package com.pfariasmunoz.stopwatch;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    // Start the stopwatch running when the Start button is clicked
    public void onClickStart(View view) {
        running = true;
    }

    // Stop the stopwatch running when the Stop button is clicked
    public void onClickStop(View view) {
        running = false;
    }

    // Reset the stopwatch when the Reset button is clicked
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    // Set the number of seconds on the timer.
    private void runTimer() {
        final TextView timerView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d: %02d: %02d", hours, minutes, secs);
                timerView.setText(time);
                if (running) {
                    seconds++;
                }
                //System.out.println("hours = seconds / 3600: " + hours + " = " + seconds + " / 3600");
                System.out.println("(seconds % 3600) / 60 # " + minutes + " = (" + seconds + " % 3600) / 60 ");
                System.out.println("(seconds % 3600): " + (seconds % 3600));
                handler.postDelayed(this, 1000);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }
}
