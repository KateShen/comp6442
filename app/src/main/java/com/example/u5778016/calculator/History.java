package com.example.u5778016.calculator;

import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;

/**
 * Created by u5721067 on 20/04/16.
 */
public class History {
    private int	_id;			//ID
    private String formula;		//Calculate formula
    private String result;	//Calculate result
    private String	_created;	//created time

    public String getTime() {
        Long date = Long.valueOf(System.currentTimeMillis());
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }

    public History(String formula, String result) {
        this.formula = formula;
        this.result = result;
        _created = getTime();
    }

    public History(int id, String formula, String result, String created) {
        _id = id;
        this.formula = formula;
        this.result = result;
        _created = created;
    }

    public int getId() {
        return _id; //Get ID
    }

    public String getFormula() {
        return formula;	//Get calculate formula
    }

    public String getResult() {
        return result;	//Get calculate result
    }

    public String getCreated() {
        return _created;	//Get created time
    }

    public void setCreated(String created) {
        _created = created;
    }

}
