package com.example.u5778016.calculator;

/**
 * Author: Dong Luo (u5319900)
 * File: Number.java
 * Description: Hex number calculate only
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.u5778016.calculator.Parsing.BaseConversion;
import com.example.u5778016.calculator.Parsing.Expression;
import com.example.u5778016.calculator.Parsing.Invalid;
import com.example.u5778016.calculator.Parsing.Tree;

public class HexActivity extends Activity {
    private EditText input;
    private String inputString;
    private HistoryDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex);

        //set the input field background color
        input = (EditText)findViewById(R.id.field);
        input.setBackgroundColor(Color.parseColor("#ffeeaa"));
        db = new HistoryDB(this);

        //button function, when you press a button, it will appear in the text field
        int idList[] = {R.id.bt_0, R.id.bt_1, R.id.bt_2, R.id.bt_3, R.id.bt_4, R.id.bt_5, R.id.bt_6, R.id.bt_7,
                R.id.bt_8, R.id.bt_9, R.id.bt_a, R.id.bt_b, R.id.bt_c, R.id.bt_d, R.id.bt_e, R.id.bt_f, R.id.bt_div, R.id.bt_mul,
                R.id.bt_minus, R.id.bt_plus, R.id.bt_left, R.id.bt_right, R.id.bt_point, R.id.bt_mod, R.id.bt_power};
        for(int id : idList) {
            View v = findViewById(id);
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String str = input.getText().toString();
                    switch (v.getId()) {
                        case R.id.bt_point:
                            input.setText(str + ".");
                            break;
                        case R.id.bt_power:
                            input.setText(str + "^");
                            break;
                        case R.id.bt_mod:
                            input.setText(str + "%");
                            break;
                        case R.id.bt_left:
                            input.setText(str + "(");
                            break;
                        case R.id.bt_right:
                            input.setText(str + ")");
                            break;
                        case R.id.bt_minus:
                            input.setText(str + "-");
                            break;
                        case R.id.bt_plus:
                            input.setText(str + "+");
                            break;
                        case R.id.bt_mul:
                            input.setText(str + "*");
                            break;
                        case R.id.bt_div:
                            input.setText(str + "/");
                            break;
                        case R.id.bt_0:
                            input.setText(str + "0");
                            break;
                        case R.id.bt_1:
                            input.setText(str + "1");
                            break;
                        case R.id.bt_2:
                            input.setText(str + "2");
                            break;
                        case R.id.bt_3:
                            input.setText(str + "3");
                            break;
                        case R.id.bt_4:
                            input.setText(str + "4");
                            break;
                        case R.id.bt_5:
                            input.setText(str + "5");
                            break;
                        case R.id.bt_6:
                            input.setText(str + "6");
                            break;
                        case R.id.bt_7:
                            input.setText(str + "7");
                            break;
                        case R.id.bt_8:
                            input.setText(str + "8");
                            break;
                        case R.id.bt_9:
                            input.setText(str + "9");
                            break;
                        case R.id.bt_a:
                            input.setText(str + "A");
                            break;
                        case R.id.bt_b:
                            input.setText(str + "B");
                            break;
                        case R.id.bt_c:
                            input.setText(str + "C");
                            break;
                        case R.id.bt_d:
                            input.setText(str + "D");
                            break;
                        case R.id.bt_e:
                            input.setText(str + "E");
                            break;
                        case R.id.bt_f:
                            input.setText(str + "F");
                            break;

                    }
                }
            });
        }
    }

    //override the menu function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {                 //exit the app
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        } else if(id == R.id.action_view) {         //page jump to history
            Intent intent = new Intent(this, ListsActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_binary){        //page jump to binary calculator
            Intent intent = new Intent(this, BinaryActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_logical) {     //page jump to logical calculator
            Intent intent = new Intent(this, LogicalActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_octal) {       //page jump to octal calculator
            Intent intent = new Intent(this, OctalActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_hex) {        //page jump to hex calculator
            Intent intent = new Intent(this, HexActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void analysisInput(View view){
        //get the string that you input
        inputString = input.getText().toString();
        if (inputString.equals("") || inputString==null){
            //if this string is null, it will show "Noting Input"
            Toast.makeText(HexActivity.this,"Nothing Input",Toast.LENGTH_LONG).show();
        } else {
            Expression result = Tree.generate(inputString, 16);
            if (result instanceof Invalid) {
                //if the expression which generated by tree is invalid, then it will show you "Input Error"
                Toast.makeText(HexActivity.this, "Input Error", Toast.LENGTH_SHORT).show();
                input.setText("");
            } else {
                //if the expression is correct, then it will be calculated
                double aftercalcu = result.evaluate();
                input.setText(BaseConversion.Decimal_to_Hex(aftercalcu));
            }

        }
    }

    public void clearAll(View view) {
        input.setText("");
    }

    public void viewHistory(View view) {
        Intent intent = new Intent(this, ListsActivity.class);
        startActivity(intent);
    }

    public void save() {
        String formula = inputString;
        String result = input.getText().toString();
        /*judge whether formula or result is null
          If yes, it will give a toast. If no, it will put formula and result insert into database.
          */
        if(formula.equals("")||result.equals(""))
        {
            Toast.makeText(this,"Formula or result is null", Toast.LENGTH_SHORT).show();
        }else
        {
            ToDatabase();	//Insert to database
            Toast.makeText(this,"Save success", Toast.LENGTH_SHORT).show();
        }
    }

    public final int ToDatabase()
    {
        //put formula and result to the database
        String formula = inputString;
        String result = input.getText().toString();
        int newId = -1;
        History newNote = new History(formula, result);
        newId = db.addHis(newNote);
        return newId;
    }
}
