package com.example.u5778016.calculator;

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
import com.example.u5778016.calculator.Parsing.Tree;

public class OctalActivity extends Activity {
    private EditText input;
    private String inputString;
    private HistoryDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_octal);

        input = (EditText)findViewById(R.id.field);
        input.setBackgroundColor(Color.parseColor("#ffeeaa"));
        db = new HistoryDB(this);

        int idList[] = {R.id.bt_0, R.id.bt_1, R.id.bt_2, R.id.bt_3, R.id.bt_4, R.id.bt_5, R.id.bt_6, R.id.bt_7,  R.id.bt_div, R.id.bt_mul, R.id.bt_minus, R.id.bt_plus, R.id.bt_left, R.id.bt_right, R.id.bt_point, R.id.bt_mod, R.id.bt_power};
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

    public void analysisInput(View view){
        inputString = input.getText().toString();
        if (inputString.equals("")){
            Toast.makeText(this, "Nothing Input", Toast.LENGTH_SHORT).show();
        }
        else {
            double result = Tree.generate(inputString, 8).evaluate();
            input.setText(BaseConversion.Decimal_to_Octal(result));
            save();
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
