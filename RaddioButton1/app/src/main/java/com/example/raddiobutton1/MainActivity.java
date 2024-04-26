//A111222007
package com.example.raddiobutton1;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.raddiobutton1.MainActivity2;

public class MainActivity extends AppCompatActivity {
    public static String shoppingCartContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnOK = findViewById(R.id.Btn_OK);
        final RadioButton rdbBoy = findViewById(R.id.rdbBoy);
        final RadioButton rdbGirl = findViewById(R.id.rdbGirl);
        final RadioGroup rgType = findViewById(R.id.rgType);
        final EditText ticketNumber = findViewById(R.id.Tickets);
        final TextView lblOutput = findViewById(R.id.lblOutput);

        //男生
        rdbBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTicketInfo(rdbBoy, rgType, ticketNumber, lblOutput);
            }
        });
        //女生
        rdbGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTicketInfo(rdbBoy, rgType, ticketNumber, lblOutput);
            }
        });
        //票種
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateTicketInfo(rdbBoy, rgType, ticketNumber, lblOutput);
            }
        });
        //張數
        ticketNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updateTicketInfo(rdbBoy, rgType, ticketNumber, lblOutput);
            }
        });
        //確認按鈕
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTicketInfo(rdbBoy, rgType, ticketNumber, lblOutput);

                if (lblOutput.getText().toString().isEmpty() || lblOutput.getText().toString().contains("票的張數未填寫或格式不正確")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("錯誤")
                            .setMessage("請先填寫訊息確認！")
                            .setPositiveButton("確定", null)
                            .show();
                } else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("PurchaseDetails", lblOutput.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    //購票訊息
    private void updateTicketInfo(RadioButton rdbBoy, RadioGroup rgType, EditText ticketNumber, TextView lblOutput) {
        String gender = rdbBoy.isChecked() ? getResources().getString(R.string.male) : getResources().getString(R.string.female);
        int price = 0;
        String ticketType = "";
        int total = 0;
        int numberOfTickets = 0;
        boolean validTickets = false;

        try {
            numberOfTickets = Integer.parseInt(ticketNumber.getText().toString());
            validTickets = true;
        } catch (NumberFormatException e) {
            validTickets = false;
        }

        int checkedId = rgType.getCheckedRadioButtonId();
        if (checkedId == R.id.rdbAdult) {
            price = 500;
            ticketType = getResources().getString(R.string.regularticket);
        } else if (checkedId == R.id.rdbStudent) {
            price = 250;
            ticketType = getResources().getString(R.string.childticket);
        } else if (checkedId == R.id.rdbChild) {
            price = 400;
            ticketType = getResources().getString(R.string.studentticket);
        }

        String outputStr = String.format("%s\n%s", gender, ticketType);
        if (validTickets) {
            total = price * numberOfTickets;
            outputStr += String.format(" %d 張\n金額 %d 元", numberOfTickets, total);
        } else {
            outputStr += "\n票的張數未填寫或格式不正確";
        }

        lblOutput.setText(outputStr);
    }
}
