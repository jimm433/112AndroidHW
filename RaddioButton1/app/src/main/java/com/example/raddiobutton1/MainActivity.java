package com.example.raddiobutton1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String shoppingCartContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonAddToCart = findViewById(R.id.Btn_addshopcar);
        final Button buttonViewCart = findViewById(R.id.Btn_shopcar);
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rdbBoy = findViewById(R.id.rdbBoy);
                RadioButton rdbGirl = findViewById(R.id.rdbGirl);
                RadioGroup rgType = findViewById(R.id.rgType);
                EditText ticketNumber = findViewById(R.id.Tickets);
                TextView lblOutput = findViewById(R.id.lblOutput);

                // 處理性別選擇
                String gender = rdbBoy.isChecked() ? getResources().getString(R.string.male) : getResources().getString(R.string.female);

                // 初始化價格和票種
                int price = 0;
                String ticketType = "";
                int checkedId = rgType.getCheckedRadioButtonId();

                // 判斷選中的票種
                if (checkedId == R.id.rdbAdult) {
                    price = 500;
                    ticketType = getResources().getString(R.string.regularticket);
                } else if (checkedId == R.id.rdbChild) {
                    price = 400;
                    ticketType = getResources().getString(R.string.childticket);
                } else if (checkedId == R.id.rdbStudent) {
                    price = 250;
                    ticketType = getResources().getString(R.string.studentticket);
                }

                // 檢查票數是否為空
                if (ticketNumber.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("請填寫票的張數")
                            .setPositiveButton("確定", null)
                            .show();
                    return;
                }

                // 獲取票數並計算總價
                int numberOfTickets = Integer.parseInt(ticketNumber.getText().toString());
                int total = price * numberOfTickets;

                // 構建輸出字符串
                String outputStr = String.format("%s\n%s %d 張\n金額 %d 元", gender, ticketType, numberOfTickets, total);
                lblOutput.setText(outputStr);

                // 彈出確認對話框
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("加入購物車")
                        .setMessage("是否將以下商品加入購物車？\n" + outputStr)
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                shoppingCartContent += outputStr + "\n";
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
            }
        });

        buttonViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 創建一個意圖以啟動 MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                // 把購物車內容作為字符串附加到意圖
                intent.putExtra("cartContent", shoppingCartContent);
                startActivity(intent);  // 啟動 MainActivity2
            }
        });
    }
}
