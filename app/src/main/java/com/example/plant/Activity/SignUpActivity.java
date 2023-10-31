package com.example.plant.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plant.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText accountEditText, passwordEditText, snameEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 初始化视图
        accountEditText = findViewById(R.id.SignUpAccount);
        passwordEditText = findViewById(R.id.SignUpPassword);
        snameEditText = findViewById(R.id.SignUpSname);
        signUpButton = findViewById(R.id.SignUpButton);

        // 设置注册按钮点击事件
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理注册逻辑
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String sname = snameEditText.getText().toString();

                // TODO: 执行注册操作

                // 示例：输出用户输入的账号、密码和姓名
                System.out.println("账号：" + account);
                System.out.println("密码：" + password);
                System.out.println("姓名：" + sname);
            }
        });
    }
}

