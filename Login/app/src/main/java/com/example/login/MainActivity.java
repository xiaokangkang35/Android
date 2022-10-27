package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText name,pwd;
        Button btnLogin;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = this.findViewById(R.id.name);
        pwd = this.findViewById(R.id.pwd);
        btnLogin = this.findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "MainActivity";
//            弹窗时长
            private static final int LENGTH_SHORT = 2000;

            @Override
            public void onClick(View view) {
                String un = "123456";
                String pw = "123";
                String username = name.getText().toString();
                String password = pwd.getText().toString();
                if (username.equals(un) && password.equals(pw)){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"密码或账号错误！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}