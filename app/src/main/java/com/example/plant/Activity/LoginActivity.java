package com.example.plant.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plant.R;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEditText, passwordEditText;
    private CheckBox rememberPasswordCheckBox;
    private Button loginButton;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化视图
        accountEditText = findViewById(R.id.account);
        passwordEditText = findViewById(R.id.password);
        rememberPasswordCheckBox = findViewById(R.id.rememberPassword);
        loginButton = findViewById(R.id.button);
        signUpTextView = findViewById(R.id.ClickToSignUp);

        // 设置登录按钮点击事件
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理登录逻辑
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean rememberPassword = rememberPasswordCheckBox.isChecked();

                // TODO: 执行登录验证操作

                // 示例：输出用户输入的账号、密码和记住密码选项
                System.out.println("账号：" + account);
                System.out.println("密码：" + password);
                System.out.println("记住密码：" + rememberPassword);
            }
        });

        // 设置注册文本点击事件
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理跳转到注册页面的逻辑
                // TODO: 跳转到注册页面
            }
        });
    }
}

