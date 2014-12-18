package au.com.taeho.app.mygolfscorecard;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DBHelper.tableName;
    private SQLiteDatabase newDB;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();

        displayResultList();

        Look at this link::::ß
        http://stackoverflow.com/questions/13119935/android-how-to-start-new-activity-for-onitemclick-of-list-view-that-uses-conten

        list = displayResultList();

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int pos, long arg3) {
                Intent i= new Intent(currentClass.this,secondActivity.class);
                i.putExtra("string",Yourlist.get(pos).sms);
                startActivity(i);
                finish();
            }
        });
    }

    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("This data is retrieved from the database.");
        getListView().addHeaderView(tView);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }
    private void openAndQueryDatabase() {
        try {
            DBHelper dbHelper = new DBHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT GolfCourse, Suburb, State, Country FROM " +
                    tableName, null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        String golfCourse = c.getString(c.getColumnIndex("GolfCourse"));
                        String suburb = c.getString(c.getColumnIndex("Suburb"));
                        String state = c.getString(c.getColumnIndex("State"));
                        results.add(golfCourse + " - " + suburb + " | " + state);
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }

    }

}
