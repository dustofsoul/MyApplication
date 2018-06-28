package com.example.lhz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;

public class preActivity extends Activity {

    Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==250){
                Intent intent=new Intent(preActivity.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);
        WaitThread thread=new WaitThread();
        thread.start();
    }
    public class WaitThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            Message message =new Message();
            message.what=250;
            message.arg1=1;
            handler.sendMessage(message);
        }
    }
}