package winston.gameLibrary;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CursorAdapter adapter;
    ListView listView;

    ArrayList<Games> gamesDetail;

    //fatboyslim menu
    //@Override
    public boolean OnCreateMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        //reference for searchable
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    //begin intents
    @Override
    protected void onNewIntent (Intent intent){
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = GameSQLiteDatabaseHelper.getInstance(MainActivity.this).searchLibrary(query);
            //second swapCursor on the intent
            adapter.swapCursor(cursor);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.game_List_View);

        //cursed cursors
        Cursor cursor = GameSQLiteDatabaseHelper.getInstance(this).listGames();

        gamesDetail = new ArrayList<Games>();

        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Games addGame = new Games();
                addGame.setmName(cursor.getString(cursor.getColumnIndex("name")));
                addGame.setmGenre(cursor.getString(cursor.getColumnIndex("genre")));
                addGame.setmRelease(cursor.getString(cursor.getColumnIndex("release")));
                addGame.setmBlurb(cursor.getString(cursor.getColumnIndex("blurb")));

            gamesDetail.add(addGame);
        }
        //curse these cursoradapters
        if (adapter == null){
            adapter = new CursorAdapter(this, cursor, 1) {
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                    //fatboyslim xml
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View view = layoutInflater.inflate(R.layout.activity_games, viewGroup, false);
                    return view;
                }

                @Override
                //CustomLayout
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView gameName = (TextView) view.findViewById(R.id.GameName);
                    String games = cursor.getString(cursor.getColumnIndex("name"));
                    gameName.setText(games);
                }
            };

            listView.setAdapter(adapter);
        }else{
            //Swap in a new Cursor, returning the old Cursor.
            adapter.swapCursor(cursor);
        }

        //Listen for clicky, clicky, tappy, tappy onItem
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Cursor cursor = adapter.getCursor();
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name", cursor.getString(cursor.getColumnIndex(GameSQLiteDatabaseHelper.COL_NAME)));
                intent.putExtra("genre", cursor.getString(cursor.getColumnIndex(GameSQLiteDatabaseHelper.COL_GENRE)));
                intent.putExtra("release", cursor.getString(cursor.getColumnIndex(GameSQLiteDatabaseHelper.COL_RELEASE)));
                intent.putExtra("blurb", cursor.getString(cursor.getColumnIndex(GameSQLiteDatabaseHelper.COL_BLURB)));

                startActivity(intent);
            }
        });

        handleIntent(getIntent());

    }



}

