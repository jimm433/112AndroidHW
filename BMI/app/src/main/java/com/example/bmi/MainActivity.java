package com.example.bmi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtHeight = findViewById(R.id.edtHeight);
        final EditText edtWeight = findViewById(R.id.edtWeight);
        Button btnCalc = findViewById(R.id.btnCalc);
        Button btnClear = findViewById(R.id.btnClear);
        final TextView txvShow = findViewById(R.id.txvShow);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = edtHeight.getText().toString();
                String weightStr = edtWeight.getText().toString();

                // 檢查身高和體重是否已輸入
                if ("".equals(heightStr) || "".equals(weightStr)) {
                    txvShow.setText("請輸入數字"); // 提示請輸入數字
                    txvShow.setTextColor(Color.BLACK); // 使用黑色顯示提示信息
                    return; // 退出此方法
                }

                try {
                    float height = Float.parseFloat(heightStr) / 100;
                    float weight = Float.parseFloat(weightStr);

                    // 檢查體重是否為0
                    if (weight == 0) {
                        txvShow.setText("請輸入正確的體重"); // 提示輸入正確數字
                        txvShow.setTextColor(Color.BLACK); // 使用黑色顯示提示信息
                        return; // 退出此方法
                    }

                    float bmi = weight / (height * height);

                    String resultText = "BMI: " + String.format("%.2f", bmi);
                    txvShow.setText(resultText);

                    if (bmi < 18.5) {
                        txvShow.setTextColor(Color.BLUE);
                    } else if (bmi > 24.9) {
                        txvShow.setTextColor(Color.RED);
                    } else {
                        txvShow.setTextColor(Color.GREEN);
                    }
                } catch (NumberFormatException e) {
                    txvShow.setText("輸入格式錯誤，請輸入數字");
                    txvShow.setTextColor(Color.BLACK);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHeight.setText("");
                edtWeight.setText("");
                txvShow.setText("");
                txvShow.setTextColor(Color.BLACK);
            }
        });
    }
}
