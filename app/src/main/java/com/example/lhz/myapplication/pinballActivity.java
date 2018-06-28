package com.example.lhz.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class pinballActivity extends Activity {
    Random rand = new Random();
    private double xyRate = rand.nextDouble() - 0.5;
    private int tablewidth;
    private int tableheight;
    private int racketx = rand.nextInt(200);
    private int rackety ;
    private int RACKET_WIDTH = 250;
    private int RACKET_HEIGHT = 40;
    private int BALL_SIZE = 30;
    private int ballx = rand.nextInt(200) + 20;
    private int bally = rand.nextInt(10) + 20;
    private int yspeed = 50;
    private int xspped = (int) (yspeed*xyRate*2);
    private boolean isLose = false;
    int oldx;
    int oldy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameView gameView = new GameView(this);
        setContentView(gameView);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);


        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        tablewidth = metrics.widthPixels;
        tableheight = metrics.heightPixels;
        Log.i("mydate" , tablewidth +"  " + tableheight);
        rackety = tableheight - 80;
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    gameView.invalidate();
                }
            }
        };

        gameView.setOnKeyListener(new View.OnKeyListener() { //按键
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getKeyCode()){
                    case KeyEvent.KEYCODE_A:
                        if (racketx > 0) racketx -= 10;
                        break;
                    case KeyEvent.KEYCODE_D:
                        if (racketx < tablewidth - RACKET_WIDTH) racketx += 10;
                        break;
                }
                gameView.invalidate();//控制球拍左右移动

                return true;
            }
        });

        gameView.setOnTouchListener(new View.OnTouchListener() { //触摸
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int nowx = (int) event.getX();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        oldx = nowx;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (nowx > oldx){
                            racketx += 10;
                        } else if (nowx < oldx) {
                            racketx -= 10;
                        }
                        oldx = nowx;
                        break;
                    case MotionEvent.ACTION_UP:
                        oldx = nowx;
                        break;
                }
                gameView.invalidate();
                return true;
            }
        });

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (ballx <= 0 || ballx >= tablewidth - BALL_SIZE){
                    xspped = -xspped;
                }
                if (bally >= rackety - BALL_SIZE && (ballx < racketx || ballx > racketx+RACKET_WIDTH)){
                    timer.cancel();
                    isLose = true;
                }
                else if (bally <= 0 || (bally >= rackety-BALL_SIZE && ballx > racketx && ballx < racketx + RACKET_WIDTH)){
                    yspeed = -yspeed;
                }
                bally += yspeed;
                ballx += xspped;
                handler.sendEmptyMessage(1);

            }
        } , 0 , 100);

    }

    class GameView extends View{
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        public GameView(Context context) {
            super(context);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint1.setStyle(Paint.Style.FILL);
            paint1.setAntiAlias(true);
            if (isLose){
                paint1.setColor(Color.RED);
                paint1.setTextSize(40);
                canvas.drawText("GAME OVER" , tablewidth/2-100 , 200 , paint1);
            } else {
                paint1.setColor(Color.RED);
                paint2.setColor(Color.BLUE);
                canvas.drawCircle(ballx , bally , BALL_SIZE , paint1);
                canvas.drawRect(racketx , rackety , racketx + RACKET_WIDTH , rackety + RACKET_HEIGHT , paint2);//绘制球拍
            }

        }

    }


}