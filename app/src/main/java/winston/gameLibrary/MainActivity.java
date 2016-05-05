package winston.gameLibrary;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private CursorAdapter mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCursorAdapter = null;

        GameSQLiteDatabaseHelper dbHelper = new GameSQLiteDatabaseHelper(MainActivity.this);
        dbHelper.getReadableDatabase();

        handleIntent(getIntent());

        //set ListView on
/*        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent  = new Intent(MainActivity.this, DetailActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("id",cursor.getInt(cursor.getColumnIndex(GameSQLiteDatabaseHelper.COL_ID)));
                startActivity(intent);
            }
        }//end OnItemClickListener*/
    }

    //begins intent... May the 4th be with me.
    @Override
    public void onNewIntent(Intent intent){handleIntent(intent);}

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY); //deep search?

            Cursor cursor = GameSQLiteDatabaseHelper.getInstance(MainActivity.this).getGameList(query);
            DatabaseUtils.dumpCursor(cursor);
            ListView gamesListView = (ListView)findViewById(R.id.game_List_View);

            if(mCursorAdapter == null) {
                mCursorAdapter = new SimpleCursorAdapter
                        (MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                cursor, new String[]{GameSQLiteDatabaseHelper.COL_NAME},
                                new int[]{android.R.id.text1}, 1);
                gamesListView.setAdapter(mCursorAdapter);
            }else{
                mCursorAdapter.swapCursor(cursor);
            }
        }
    }

    //fat boys' function
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //adds to the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


}

