package winston.gameLibrary;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
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

    ArrayList<GamesGlobalActivity> gamesDetail;

    //fatboyslim menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        //reference for searchable
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchmenu).getActionView();
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
            Cursor cursorSearch = GameSQLiteDatabaseHelper.getInstance(MainActivity.this).searchLibrary(query);
            //second swapCursor on the intent
            DatabaseUtils.dumpCursor(cursorSearch);
            adapter.swapCursor(cursorSearch);

        //if(cursorSearch.getCount() == 0){
        //  cursorSearch = GameSQLiteDatabaseHelper.getInstance(MainActivity.this).listGames(query);
        //  Toast.makeText(MainActivity.this,"Invalid input. Your Princess is in another castle." Toast.LENGTH_LONG).show();
        // }
        }
    }//end intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.game_List_View);

        //cursed cursors
        Cursor cursorSearch = GameSQLiteDatabaseHelper.getInstance(this).listGames();

        gamesDetail = new ArrayList<GamesGlobalActivity>();

        for (cursorSearch.moveToFirst();!cursorSearch.isAfterLast();cursorSearch.moveToNext()){
            GamesGlobalActivity addGame = new GamesGlobalActivity();
                addGame.setmName(cursorSearch.getString(cursorSearch.getColumnIndex("name")));
                addGame.setmGenre(cursorSearch.getString(cursorSearch.getColumnIndex("genre")));
                addGame.setmRelease(cursorSearch.getString(cursorSearch.getColumnIndex("release")));
                addGame.setmBlurb(cursorSearch.getString(cursorSearch.getColumnIndex("blurb")));

            gamesDetail.add(addGame);
        }
        //curse these cursoradapters
        if (adapter == null){
            adapter = new CursorAdapter(this, cursorSearch, 0) {
                @Override
                public View newView(Context context, Cursor cursorSearch, ViewGroup viewGroup) {
                    //fatboyslim xml
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View view = layoutInflater.inflate(R.layout.activity_games, viewGroup, false);
                    return view;
                }

                @Override
                //CustomLayout
                public void bindView(View view, Context context, Cursor cursorSearch) {
                    TextView gameName = (TextView) view.findViewById(R.id.GameName);
                    gameName.setTextSize(14.0f);
//                    gameName.setTextColor(Color.parseColor("#000000"));
//                    gameName.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    gameName.setTextColor(Color.WHITE);
                    //gameName.setBackgroundColor(Color.LTGRAY);
                    String games = cursorSearch.getString(cursorSearch.getColumnIndex("name"));
                    gameName.setText(games);
                }
            };

            listView.setAdapter(adapter);
        }else{
            //Swap in a new Cursor, returning the old Cursor.
            adapter.swapCursor(cursorSearch);
        }

        //Listen for clicky, clicky, tappy, tappy onItem
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Cursor cursorSearch = adapter.getCursor();
                cursorSearch.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name", cursorSearch.getString(cursorSearch.getColumnIndex(GameSQLiteDatabaseHelper.COL_NAME)));
                intent.putExtra("genre", cursorSearch.getString(cursorSearch.getColumnIndex(GameSQLiteDatabaseHelper.COL_GENRE)));
                intent.putExtra("release", cursorSearch.getString(cursorSearch.getColumnIndex(GameSQLiteDatabaseHelper.COL_RELEASE)));
                intent.putExtra("blurb", cursorSearch.getString(cursorSearch.getColumnIndex(GameSQLiteDatabaseHelper.COL_BLURB)));

                startActivity(intent);
            }
        });

        handleIntent(getIntent());

        //Dialog Interface Attempt
/*        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.dialog_title)
                    .setMessage(R.string.dialog_message);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();*/

    }//end onCreate




}

