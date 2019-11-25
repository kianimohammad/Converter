package com.f19.myconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText cadField;
    EditText usdField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadField = findViewById(R.id.cad);
        usdField = findViewById(R.id.usd);
        Button btn = findViewById(R.id.btn);

        cadField.addTextChangedListener(textWatcher_cad);
        usdField.addTextChangedListener(textWatcher_usd);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cadField.getText().toString().isEmpty() && usdField.getText().toString().isEmpty()) {
                    double cadCurrency = Double.valueOf(cadField.getText().toString());
                    usdField.setText(String.format("%.2f", cadCurrency / 1.3));

                } else if (cadField.getText().toString().isEmpty() && !usdField.getText().toString().isEmpty()) {
                    double usdCurrency = Double.valueOf(usdField.getText().toString());
                    cadField.setText(String.format("%.2f", usdCurrency * 1.3));
                } else {
                    Toast.makeText(MainActivity.this, "Provide a value in one field", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    TextWatcher textWatcher_cad = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.i("cad", "onTextChanged: cad changed");
            if (getCurrentFocus() == cadField) {
                if (!cadField.getText().toString().isEmpty()) {
                    double cadCurrency = Double.valueOf(cadField.getText().toString());
                    usdField.setText(String.format("%.2f", cadCurrency / 1.3));
                } else
                    usdField.setText("");
            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher textWatcher_usd = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.i("USD", "onTextChanged: usd changed");
            if (getCurrentFocus() == usdField) {
                if (!usdField.getText().toString().isEmpty()) {
                    double usdCurrency = Double.valueOf(usdField.getText().toString());
                    cadField.setText(String.format("%.2f", usdCurrency * 1.3));
                } else
                    cadField.setText("");
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
