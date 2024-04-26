package com.example.raddiobutton1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent intent = getIntent();
        String purchaseDetails = intent.getStringExtra("PurchaseDetails");

        TextView shopThingLbl = findViewById(R.id.shopthinglbl);
        shopThingLbl.setText(purchaseDetails);

        Button backToShopBtn = findViewById(R.id.backtoshopbtn);
        backToShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
