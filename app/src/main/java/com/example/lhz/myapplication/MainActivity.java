package com.example.lhz.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout= (GridLayout) findViewById(R.id.root);
        gridLayout.setColumnCount(2);
        gridLayout.setRowCount(4);

        bt1=(Button)findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

        bt2=(Button)findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

        bt3=(Button)findViewById(R.id.bt3);
        bt4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

        bt4=(Button)findViewById(R.id.bt4);
        bt4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

        bt5=(Button)findViewById(R.id.bt5);
        bt5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

        bt6=(Button)findViewById(R.id.bt6);
        bt6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

        bt7=(Button)findViewById(R.id.bt7);
        bt7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

        bt8=(Button)findViewById(R.id.bt8);
        bt8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        });

    }
}
