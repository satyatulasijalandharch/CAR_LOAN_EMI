package com.stj.car_emi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    EditText PAE,DPE,IRE,LTE;
    //PAE -> Principal Amount Edit Text
    //DPE -> Down Payment Edit Text
    //IRE -> Interest Rate Edit Text
    //LTE -> Loam Term Edit Text
    Button EMI;
    //EMI -> Calculate EMI Button
    TextView EMIA;
    //EMIA -> EMI Text view


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PAE= findViewById(R.id.PAE);
        DPE= findViewById(R.id.DPE);
        IRE= findViewById(R.id.IRE);
        LTE= findViewById(R.id.LTE);
        EMI= findViewById(R.id.EMI);
        EMI.setOnClickListener(this);
        EMIA= findViewById(R.id.EMIA);
    }

    @Override
    public void onClick(View v) {
        try {
            double PrincipalAmount = Double.parseDouble(PAE.getText().toString());
            double DownPayment = Double.parseDouble(DPE.getText().toString());
            double InterestRate = Double.parseDouble(IRE.getText().toString());
            double LoanTerm = Double.parseDouble(LTE.getText().toString());

            if (DownPayment > PrincipalAmount) {
                Toast.makeText(MainActivity.this, "Maximum down payment: "+ PrincipalAmount, Toast.LENGTH_SHORT).show();
                DPE.requestFocus();
                return;
            }

            double minimumDownPayment = PrincipalAmount * 0.2;
            if (DownPayment < minimumDownPayment) {
                Toast.makeText(MainActivity.this, "Minimum down payment: "+ minimumDownPayment, Toast.LENGTH_SHORT).show();
                DPE.requestFocus();
                return;
            }

            DecimalFormat formatter = new DecimalFormat("#0.00");
            PrincipalAmount = PrincipalAmount-DownPayment;
            InterestRate = InterestRate/(12*100);

            // FORMULA FOR EMI CALCULATION -> [E = P * (r(1+r)n)/((1+r)n-1)]

            double EMI = PrincipalAmount * (InterestRate * Math.pow((1 + InterestRate),LoanTerm))/
                    (Math.pow((1 + InterestRate),LoanTerm)-1);
            EMIA.setText(formatter.format(EMI));
        }

        catch (Exception e)
        {
            Toast.makeText(getBaseContext(),"Invalid Input",Toast.LENGTH_LONG).show();
        }
    }
}