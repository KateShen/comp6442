package com.example.u5778016.calculator;

/**
 * Created by u5721067 on 20/04/16.
 */
public class History {
    private int	_id;			//ID
    private String formula;		//Calculate formula
    private String result;	//Calculate result
    private long	_created;	//created time

    public History(String formula, String result) {
        this.formula = formula;
        this.result = result;
        _created = System.currentTimeMillis();
    }

    public History(int id, String formula, String result, long created) {
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

    public long getCreated() {
        return _created;	//Get created time
    }

    public void setCreated(long created) {
        _created = created;
    }

}
