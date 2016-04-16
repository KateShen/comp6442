package com.example.u5778016.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView output;
    String inputString;
    String aftertransform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // weight=op.gethashmap();

        input = (EditText)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);
        //analysisinput();
        //System.out.println(weight.get(0).toString());
    }

    public void analysisinput(View view){
        inputString = input.getText().toString();

        if (inputString.equals("") || inputString==null){Toast.makeText(MainActivity.this,"Nothing Input",Toast.LENGTH_LONG).show();
        }
        else {
            //Transforming transforming=new Transforming(inputString);
            //aftertransform=transforming.doTransform();
            //
            // Formula formula = new Formula();
            double aftercalcu = Formula.getInstnace().formula(inputString);
            //aftercalcu = formula.formula(aftertransform);
            output.setText(Double.toString(aftercalcu));
        }
    }
}
