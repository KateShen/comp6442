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

    @Override
    public void onBackPressed() {
        // do something on back.
    }
}
