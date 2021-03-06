package com.example.lhz.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class registActivity extends Activity {

    private EditText edname1;
    private EditText edpassword1;
    private Button btregister1;
    SQLiteDatabase db;

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        edname1 = (EditText) findViewById(R.id.edname1);
        edpassword1 = (EditText) findViewById(R.id.edpassword1);
        btregister1 = (Button) findViewById(R.id.btregister1);
        btregister1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String name = edname1.getText().toString();
                String password = edpassword1.getText().toString();
                if (!(name.equals("") && password.equals(""))) {
                    if (addUser(name, password)) {
                        DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Intent in = new Intent();
                                in.setClass(registActivity.this,
                                        loginActivity.class);
                                startActivity(in);
                            }
                        };
                        new AlertDialog.Builder(registActivity.this)
                                .setTitle("提示信息").setMessage("注册成功")
                                .setPositiveButton("确定", ss).show();

                    } else {
                        new AlertDialog.Builder(registActivity.this)
                                .setTitle("提示信息").setMessage("注册失败")
                                .setPositiveButton("确定", null);
                    }
                } else {
                    new AlertDialog.Builder(registActivity.this)
                            .setTitle("提示信息").setMessage("帐号密码不能为空")
                            .setPositiveButton("确定", null);
                }

            }
        });

    }

    // 添加用户
    public Boolean addUser(String name, String password) {
        String str = "insert into tb_user values(?,?) ";
        loginActivity main = new loginActivity();
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
                + "/test.dbs", null);
        main.db = db;
        try {
            db.execSQL(str, new String[] { name, password });
            return true;
        } catch (Exception e) {
            main.createDb();
        }
        return false;
    }

}