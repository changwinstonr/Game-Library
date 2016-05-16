package winston.gameLibrary;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
//Adds search to DetailActivity
    CursorAdapter adapter;

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
            Cursor cursorSearch = GameSQLiteDatabaseHelper.getInstance(DetailActivity.this).searchLibrary(query);
            //second swapCursor on the intent
            DatabaseUtils.dumpCursor(cursorSearch);
            adapter.swapCursor(cursorSearch);
        }
    }//end intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_detail);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TextView name = (TextView) findViewById(R.id.game_name);
        TextView genre = (TextView) findViewById(R.id.game_genre);
        TextView release = (TextView) findViewById(R.id.game_release);
        TextView blurb = (TextView) findViewById(R.id.game_blurb);

        Intent intent = getIntent();

        String mName = intent.getStringExtra("name");
        String mGenre = intent.getStringExtra("genre");
        String mRelease = intent.getStringExtra("release");
        String mBlurb = intent.getStringExtra("blurb");

        name.setText(mName);
        genre.setText(mGenre);
        release.setText(mRelease);
        blurb.setText(mBlurb);

        blurb.setMovementMethod(new ScrollingMovementMethod());



    }
}
