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
        PAE=(EditText) findViewById(R.id.PAE);
        DPE=(EditText) findViewById(R.id.DPE);
        IRE=(EditText) findViewById(R.id.IRE);
        LTE=(EditText) findViewById(R.id.LTE);
        EMI=(Button) findViewById(R.id.EMI);
        EMI.setOnClickListener(this);
        EMIA=(TextView) findViewById(R.id.EMIA);
    }

    @Override
    public void onClick(View v) {
        try {
            DecimalFormat formatter = new DecimalFormat("#0.00");
            double PrincipalAmount = Double.parseDouble(PAE.getText().toString());
            double DownPayment = Double.parseDouble(DPE.getText().toString());
            PrincipalAmount = PrincipalAmount-DownPayment;
            double InterestRate = Double.parseDouble(IRE.getText().toString());
            InterestRate = InterestRate/(12*100);
            double LoanTerm = Double.parseDouble(LTE.getText().toString());
            // FORMULA FOR EMI CALCULATION -> [E = P * (r(1+r)n)/((1+r)n-1)]
            double EMI = PrincipalAmount*(InterestRate*Math.pow((1+InterestRate),LoanTerm))/
                    (Math.pow((1+InterestRate),LoanTerm)-1);
            EMIA.setText(formatter.format(EMI));
        }

        catch (Exception e)
        {
            Toast.makeText(getBaseContext(),"Invalid Input",Toast.LENGTH_LONG).show();
        }
    }
}