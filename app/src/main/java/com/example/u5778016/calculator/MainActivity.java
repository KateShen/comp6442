package com.example.u5778016.calculator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u5778016.calculator.Parsing.Expression;
import com.example.u5778016.calculator.Parsing.Invalid;
import com.example.u5778016.calculator.Parsing.Tree;

public class MainActivity extends Activity {
    private EditText input;
    private String inputString;
    private String InputforDatabase;
    private HistoryDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.field);
        input.setBackgroundColor(Color.parseColor("#ffeeaa"));

        db = new HistoryDB(this);

        /*GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(2, Color.BLACK);*/


        int idList[] = {R.id.bt_0, R.id.bt_1, R.id.bt_2, R.id.bt_3, R.id.bt_4, R.id.bt_5, R.id.bt_6, R.id.bt_7, R.id.bt_8, R.id.bt_9,
                R.id.bt_div, R.id.bt_mul, R.id.bt_minus, R.id.bt_plus, R.id.bt_left, R.id.bt_right, R.id.bt_point, R.id.bt_lg, R.id.bt_ln,
                R.id.bt_cos, R.id.bt_sin, R.id.bt_tan, R.id.bt_e, R.id.bt_pi, R.id.bt_power, R.id.bt_mod, R.id.bt_root};
        for(int id : idList) {
            View v = findViewById(id);
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String str = input.getText().toString();
                    switch (v.getId()) {
                        case R.id.bt_point:
                            input.setText(str + ".");
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
                        case R.id.bt_sin:
                            input.setText(str + "sin");
                            break;
                        case R.id.bt_cos:
                            input.setText(str + "cos");
                            break;
                        case R.id.bt_tan:
                            input.setText(str + "tan");
                            break;
                        case R.id.bt_lg:
                            input.setText(str + "lg");
                            break;
                        case R.id.bt_ln:
                            input.setText(str + "ln");
                            break;
                        case R.id.bt_power:
                            input.setText(str + "^");
                            break;
                        case R.id.bt_mod:
                            input.setText(str + "%");
                            break;
                        case R.id.bt_root:
                            input.setText(str + "√");
                            break;
                        case R.id.bt_e:
                            input.setText(str + "e");
                            break;
                        case R.id.bt_pi:
                            input.setText(str + "π");
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
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        } else if(id == R.id.action_view) {
            Intent intent = new Intent(this, ListsActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_binary){
            Intent intent = new Intent(this, BinaryActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_logical) {
            Intent intent = new Intent(this, LogicalActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_octal) {
            Intent intent = new Intent(this, OctalActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_hex) {
            Intent intent = new Intent(this, HexActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void analysisinput(View view){
        String str1;
        String str2;
        InputforDatabase=input.getText().toString();
        inputString = input.getText().toString();
        if (inputString.contains("sin")){
            str1="sin";
            str2="s";
            //String inputupdate;
            inputString=inputString.replaceAll(str1,str2);

        }if (inputString.contains("cos")){
            str1="cos";
            str2="c";
            //String inputupdate;
            inputString=inputString.replaceAll(str1,str2);
        }
        if (inputString.contains("tan")){
            str1="tan";
            str2="t";
            //String inputupdate;
            inputString=inputString.replaceAll(str1,str2);
            //System.out.println(inputString);
        }
        if (inputString.contains("lg")){
            str1="lg";
            str2="g";
            //String inputupdate;
            inputString=inputString.replaceAll(str1,str2);
            //System.out.println(inputString);
        }
        if (inputString.contains("ln")){
            str1="ln";
            str2="n";
            //String inputupdate;
            inputString=inputString.replaceAll(str1,str2);
            //System.out.println(inputString);
        }

        if (inputString.equals("") || inputString==null){
            Toast.makeText(MainActivity.this,"Nothing Input",Toast.LENGTH_LONG).show();
        } else {
            Expression result = Tree.generate(inputString, 10);
            if (result instanceof Invalid) {
            Toast.makeText(MainActivity.this, "Input Error", Toast.LENGTH_SHORT).show();
                input.setText("");
            } else {
                double aftercalcu = result.evaluate();
                if(aftercalcu % 1 == 0)
                    input.setText(Integer.toString((int) aftercalcu));
                else
                    input.setText(Double.toString(aftercalcu));
                save();
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
        String formula = InputforDatabase;//a
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
        String formula = InputforDatabase;//a
        String result = input.getText().toString();
        int newId = -1;
        History newNote = new History(formula, result);
        newId = db.addHis(newNote);
        return newId;
    }
}
