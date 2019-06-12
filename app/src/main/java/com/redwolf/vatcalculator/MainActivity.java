package com.redwolf.vatcalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    RadioButton rb;
    Button btn1;
    EditText e1, e2;
    TextView t1, t2, t3;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btn1 = (Button) findViewById(R.id.btn1);
        e1 = (EditText) findViewById(R.id.et1);
        e2 = (EditText) findViewById(R.id.et2);
        rg = (RadioGroup) findViewById(R.id.rg);
        t1 = (TextView) findViewById(R.id.tv4);
        t2 = (TextView) findViewById(R.id.tv6);
        t3 = (TextView) findViewById(R.id.tv3);

        btn1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {



            if (e1.getText().length() == 0) {
                e1.setError("Value is required!");
            }

            if (e2.getText().length() == 0) {
                e2.setError("Value is required!");
            } else {
                int selectedId = rg.getCheckedRadioButtonId(); //wo vat 2131492953 w vat 2131492954
                rb = (RadioButton) findViewById(selectedId);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);



                if (rb.getText().length() == 11) {

                    double x = Double.parseDouble(e1.getText().toString().trim());
                    double y = Double.parseDouble(e2.getText().toString().trim());



                    if (y == 0) {
                        t3.setText("VAT Added Price:");
                        t1.setText("" + x);
                        t2.setText("" + y);
                    } else {
                        double va = ((double) y / 100) * x;
                        double t = va + x;
                        t3.setText("VAT Added Price:");
                        t1.setText(String.format("%.2f",t));
                        t2.setText(String.format("%.2f",va));
                    }
                }

                if (rb.getText().length() == 8) {

                    double x = Double.parseDouble(e1.getText().toString().trim());
                    double y = Double.parseDouble(e2.getText().toString().trim());

                    if (y == 0) {
                        t3.setText("VAT Excluded Price:");
                        t1.setText("" + x);
                        t2.setText("" + y);
                    } else {
                        double t = (double) ((x * 100) / (y + 100));
                        double va = (double) (x - t);

                        t3.setText("VAT Excluded Price: ");
                        t1.setText(String.format("%.2f",t));
                        t2.setText(String.format("%.2f",va));
                    }

                }
            }
        }
    }}

