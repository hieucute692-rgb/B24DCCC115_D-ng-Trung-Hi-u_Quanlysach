package com.example.quanlysach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    private EditText edtUsername;
    private EditText edtPassword;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtError = findViewById(R.id.txtLoginError);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            txtError.setText("Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
            txtError.setVisibility(View.VISIBLE);
            return;
        }

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Không quay lại màn hình đăng nhập bằng nút Back.
        } else {
            txtError.setText("Sai tên đăng nhập hoặc mật khẩu.");
            txtError.setVisibility(View.VISIBLE);
        }
    }
}
