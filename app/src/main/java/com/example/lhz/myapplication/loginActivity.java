package com.example.lhz.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    // 帐号和密码
    private EditText edname;
    private EditText edpassword;
    private Button btregister;
    private Button btlogin;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edname = (EditText) findViewById(R.id.edname);
        edpassword = (EditText) findViewById(R.id.edpassword);
        btregister = (Button) findViewById(R.id.btregister);
        btlogin = (Button) findViewById(R.id.btlogin);
        db = SQLiteDatabase.openOrCreateDatabase(loginActivity.this.getFilesDir().toString()
                + "/test.dbs", null);

        btregister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(loginActivity.this, registActivity.class);
                startActivity(intent);
            }
        });
        btlogin.setOnClickListener(new LoginListener());
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        db.close();
    }


    class LoginListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String name = edname.getText().toString();
            String password = edpassword.getText().toString();
            if (name.equals("") || password.equals("")) {

                new AlertDialog.Builder(loginActivity.this).setTitle("提示信息")
                        .setMessage("帐号或密码不能为空").setPositiveButton("确定", null)
                        .show();
            } else {
                if(isUserinfo(name, password))
                    finish();
            }
        }


        public Boolean isUserinfo(String name, String pwd) {
            try {
                String str = "select * from tb_user where name=? and password=?";
                Cursor cursor = db.rawQuery(str, new String[]{name, pwd});
                if (cursor.getCount() <= 0) {
                    new AlertDialog.Builder(loginActivity.this).setTitle("提示信息")
                            .setMessage("帐号或密码错误！").setPositiveButton("确定", null)
                            .show();
                    return false;
                } else {
                    new AlertDialog.Builder(loginActivity.this).setTitle("提示信息")
                            .setMessage("登录成功！").setPositiveButton("确定", null)
                            .show();
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }

            } catch (SQLiteException e) {
                createDb();
            }
            return false;
        }

    }

    public void createDb() {
        db.execSQL("create table tb_user( name varchar(30) primary key,password varchar(30))");
    }
}