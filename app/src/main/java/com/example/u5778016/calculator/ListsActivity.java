package com.example.u5778016.calculator;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class ListsActivity extends ListActivity {

    public final static String EXTRA_HIS_ID = "CALCULATE";

    private HistoryDB _db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        _db = new HistoryDB(this);

        String[] dataColumns = new String[] {"title", "content", "created"};
        int[] viewIds = new int[] { R.id.formula, R.id.result, R.id.time};

        Cursor cursor = _db.getAllHisCursor();

        // Creates the backing adapter for the ListView.
        SimpleCursorAdapter adapter
                = new SimpleCursorAdapter(
                this,                             // The Context for the ListView
                R.layout.itemlist,           // Points to the XML for a list item
                cursor,                           // The cursor to get items from
                dataColumns,
                viewIds
        );
        setListAdapter(adapter);
    }

    private void reloadNote() {
        SimpleCursorAdapter adapter = (SimpleCursorAdapter)getListAdapter();
        if (adapter != null) {
            Cursor cursor = _db.getAllHisCursor();
            adapter.swapCursor(cursor);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadNote();
    }

    //realize menu function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Top menu bar
        switch (item.getItemId()) {
            case R.id.action_add:		//add
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_exit:	//exit
                Intent intent3 = new Intent(Intent.ACTION_MAIN);
                intent3.addCategory(Intent.CATEGORY_HOME);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                android.os.Process.killProcess(android.os.Process.myPid());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
