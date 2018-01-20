package com.example.admin.colorpiano;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FirstActivity extends AppCompatActivity {

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */

    Button RED, BLUE, GREEN, YELLOW, PINK, VIOLET, ORANGE,WHITE, BLACK;
    TableLayout layout;
    String s;


    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        BLUE = (Button) findViewById(R.id.blue);
        RED = (Button) findViewById(R.id.red);
        GREEN = (Button) findViewById(R.id.green);
        YELLOW = (Button) findViewById(R.id.yellow);
        VIOLET = (Button) findViewById(R.id.violet);
        PINK = (Button) findViewById(R.id.pink);
        ORANGE  = (Button) findViewById(R.id.orange);
        WHITE = (Button) findViewById(R.id.white);
        BLACK  = (Button) findViewById(R.id.black);

        layout = (TableLayout) findViewById(R.id.tableLayout);

        BLUE.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(BLUE, Color.BLUE);
                return true; }});


        RED.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(RED, Color.RED);
                return true; }});


        GREEN.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(GREEN, Color.GREEN);
                return true; }});


        YELLOW.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(YELLOW, Color.YELLOW);
                return true; }});


        WHITE.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(WHITE, Color.WHITE);
                return true; }});


        BLACK.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(BLACK, Color.BLACK);
                return true; }});


        VIOLET.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(VIOLET, Color.parseColor("#EE82EE"));
                return true; }});


        PINK.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(PINK, Color.parseColor("#ff69b4"));
                return true; }});


        ORANGE.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                manageBlinkEffect(ORANGE, Color.parseColor("#FF4500"));
                return true; }});



    }

    private void manageBlinkEffect(Button B, int i) {

        ObjectAnimator anim = ObjectAnimator.ofInt(B, "backgroundColor", Color.WHITE, i ,Color.TRANSPARENT);
        anim.setDuration(500);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(10);

        ObjectAnimator anim1 = ObjectAnimator.ofInt(layout, "backgroundColor", Color.WHITE, i,Color.WHITE);
        anim1.setDuration(500);
        anim1.setEvaluator(new ArgbEvaluator());
        anim1.setRepeatMode(ValueAnimator.REVERSE);
        anim1.setRepeatCount(10);
        anim.start();
        anim1.start();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    public void click(View view) {


        switch (view.getId()) {

            case R.id.blue:
                BLUE.setBackgroundColor(Color.BLUE);
                break;
            case R.id.red:
                RED.setBackgroundColor(Color.RED);
                // do something else
                break;
            case R.id.green:
                GREEN.setBackgroundColor(Color.GREEN);

                break;

            case R.id.yellow:
                YELLOW.setBackgroundColor(Color.YELLOW);

                break;

            case R.id.violet:
                VIOLET.setBackgroundColor(Color.parseColor("#EE82EE"));

                break;

            case R.id.orange:
                ORANGE.setBackgroundColor(Color.parseColor("#FF4500"));

                break;

            case R.id.white:
                WHITE.setBackgroundColor(Color.WHITE);

                break;

            case R.id.black:
                BLACK.setBackgroundColor(Color.BLACK);
                BLACK.setTextColor(Color.WHITE);

                break;

            case R.id.pink:
                PINK.setBackgroundColor(Color.parseColor("#ff69b4"));

                break;


        }
        //call(s);
    }

    public void call(String id){
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

    }
}
