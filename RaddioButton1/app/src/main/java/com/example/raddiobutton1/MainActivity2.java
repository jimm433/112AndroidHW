package com.example.raddiobutton1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView shopThingLbl = findViewById(R.id.shopthinglbl);
        Button backToShopBtn = findViewById(R.id.backtoshopbtn);
        Button clearCarBtn = findViewById(R.id.clearcarbtn);
        Button buyBtn = findViewById(R.id.buybtn);

        Intent intent = getIntent();
        shopThingLbl.setText(MainActivity.shoppingCartContent);

        // 返回商店按鈕
        backToShopBtn.setOnClickListener(v -> finish());

        // 清空購物車按鈕
        clearCarBtn.setOnClickListener(v -> {
            shopThingLbl.setText("");  // 清空 TextView 顯示
            MainActivity.shoppingCartContent = "";  // 清空全局購物車內容
        });

        // 確認購買按鈕
        buyBtn.setOnClickListener(v -> {
            if (!MainActivity.shoppingCartContent.isEmpty()) {
                new AlertDialog.Builder(MainActivity2.this)
                        .setTitle("確認購買")
                        .setMessage("您即將購買以下商品：\n" + MainActivity.shoppingCartContent)
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                shopThingLbl.setText("");
                                MainActivity.shoppingCartContent = "";
                                showPurchaseSuccessDialog();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            } else {
                new AlertDialog.Builder(MainActivity2.this)
                        .setMessage("購物車為空")
                        .setPositiveButton("確定", null)
                        .show();
            }
        });
    }

    private void showPurchaseSuccessDialog() {
        new AlertDialog.Builder(MainActivity2.this)
                .setMessage("購買成功！")
                .setPositiveButton("確定", (dialog, which) -> finish())  // 購買成功後關閉此活動
                .show();
    }
}
