package com.example.orderhw;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        TextView orderSummary = findViewById(R.id.order_summary);

        // 接收來自 MainActivity 的訂單資訊
        String orderDetails = getIntent().getStringExtra("ORDER_DETAILS");
        orderSummary.setText(orderDetails);
    }
}
