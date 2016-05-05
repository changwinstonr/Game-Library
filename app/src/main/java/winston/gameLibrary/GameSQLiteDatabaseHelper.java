package winston.gameLibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/*
import java.sql.Blob;
*/

/**
 * Created by 4th3ist on 5/2/2016.
 */

public class GameSQLiteDatabaseHelper extends SQLiteOpenHelper {
    //Define DB: name
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "game_library";
    public static final String TABLE_NAME = "Game_Table";

    //DB Columns
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_GENRE = "genre";
    public static final String COL_RELEASE = "release";
    public static final String COL_BLURB = "blurb";

/*
    //04-week/05-sql-helper-class version || 04/05-shc-ver
    public static final String SQL_CREATE_GAMELIB_TABLE =
            "CREATE TABLE gameLibrary (_id INTEGER PRIMARY KEY AUTOINCREMENT, gameName TEXT, gameGenre TEXT, gameRelease TEXT, gameDescrip TEXT)";
*/
    public static final String[] GAME_COLUMNS = {COL_ID, COL_NAME, COL_GENRE, COL_RELEASE, COL_BLURB};

    private static final String CREATE_GAME_TABLE
            = "CREATE TABLE "+ TABLE_NAME +"("+COL_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_NAME+" TEXT, "
            +COL_GENRE+" TEXT, "
            +COL_RELEASE+" TEXT, "
            +COL_BLURB+" TEXT )";

    private static GameSQLiteDatabaseHelper mInstance;

    public static GameSQLiteDatabaseHelper getInstance(Context context){
        if(mInstance==null){
            mInstance = new GameSQLiteDatabaseHelper(context);
        }
        return mInstance;
    }
    //04/05-shc-ver
    public GameSQLiteDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        setForcedUpgrade();
    }

    @Override
    //04/05-shc-ver
    // Create the games table when the database is created
    public void onCreate (SQLiteDatabase db){
        db.execSQL(CREATE_GAME_TABLE);
        setDefaultData(db);
    }

    // Delete old DB, creates new DB
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        //04/05-shc-ver
        // public final String SQL_DROP_GAMELIB_TABLE = "DROP TABLE IF EXISTS gameLibrary";
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        this.onCreate(db);
    }//end onUpgrade

    public void setDefaultData(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, "game");
        db.insert(TABLE_NAME, null, values);
    }

    public Cursor getGameList(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, GAME_COLUMNS, COL_NAME, null, null, null, null);
        return cursor;
    }


/*  //04/05-shc-ver
    public void insert(int _id,
                       String game_name,
                       String game_genre,
                       String game_release,
                       String game_blurb){
        //Ref: DB
        SQLiteDatabase db = getWritableDatabase();

        //Create/Store: Content values
        ContentValues values = new ContentValues();
            values.put("id", _id);
            values.put("name", game_name);
            values.put("genre", game_genre);
            values.put("release", game_release);
            values.put("description", game_blurb);
        //Insert row into gameLib table
        db.insert("gameLibrary", null, values);
    }//end insert

    public GameLib getGameLib(int _id){


        // Define a projection
        String[] projection = new String[]{"id","name","genre","release","description"};

        // Define a selection
        String selection = "id = ?";

        // Define the selection values.
        String[] selectionArgs = new String[]{String.valueOf(_id)};


        // Make the query, getting the cursor object
        Cursor cursor = db.query("gameLibrary", projection, selection, selectionArgs, null, null, null, null);

        // With the cursor, create a new game object and return it
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex("name"));
        String genre = cursor.getString(cursor.getColumnIndex("genre"));
        String release = cursor.getString(cursor.getColumnIndex("release"));
        String description = cursor.getString(cursor.getColumnIndex("description"));

        return new GameLib (_id, name, genre, release, description);
    }
*/

}//end DatabaseHelper
