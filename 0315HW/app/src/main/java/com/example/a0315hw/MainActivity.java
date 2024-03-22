package com.example.a0315hw;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 登入按鈕的點擊事件處理函數
    public void button_Click(View view) {
        // 獲取用戶名和密碼的輸入
        EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        TextView lblOutput = (TextView) findViewById(R.id.lblOutput);

        // 讀取輸入值
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        // 預設的正確帳號和密碼，用於模擬登入過程
        String correctUsername = "admin";
        String correctPassword = "123456";

        // 檢查用戶名和密碼輸入狀況
        if(username.isEmpty()) {
            lblOutput.setText("請輸入帳號");
        } else if(password.isEmpty()) {
            lblOutput.setText("請輸入密碼");
        } else if(!username.equals(correctUsername)) {
            lblOutput.setText("帳號錯誤");
        } else if(!password.equals(correctPassword)) {
            lblOutput.setText("密碼錯誤");
        } else {
            lblOutput.setText("登錄成功");
        }
    }

}
