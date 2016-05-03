//Commenting out until the db is populated.
/*
package winston.gameLibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

*/
/**
 * Created by 4th3ist on 5/2/2016.
 *//*

public class DatabaseHelper extends SQLiteOpenHelper {
    // Define the database name and version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GameLibrary.db";

    //Creates databases
    public static final String SQL_CREATE_GAMELIB_TABLE =
            "CREATE TABLE gamesLib ( id INTEGER PRIMARY KEY, gameName TEXT, gameIcon BLOB )";
    public static final String SQL_CREATE_GAMEDESCRIP_TABLE =
            "CREATE TABLE gamesLib ( id INTEGER PRIMARY KEY, gameName TEXT, gameGenre TEXT, gamePlatform TEXT, gameRelease TEXT)";
    //Delete databases
    public static final String SQL_DROP_GAMELIB_TABLE = "DROP TABLE IF EXISTS gamesLib";
    public static final String SQL_DROP_GAMEDESCRIP_TABLE = "DROP TABLE IF EXISTS gameDescrip";


    // override the SQLiteDatabase's constructor, onCreate, and onUpgrade
    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Game table on data create
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GAMELIB_TABLE);
        db.execSQL(SQL_CREATE_GAMEDESCRIP_TABLE);
    }

    //Updates when data us upgraded and then deletes the movies table and recreates it
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_GAMELIB_TABLE);
        db.execSQL(SQL_DROP_GAMEDESCRIP_TABLE);
        onCreate(db);
    }

    public void gameTitles(int id, String gameName, Blob gameIcon){
        SQLiteDatabase db = getWritableDatabase();

        //Applies Game Values
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("game", gameName);
        values.put("gameIcon", String.valueOf(gameIcon));

        db.insert("game", null, values);
    }

    public void gameDescription(int id, String gameName, String gameGenre, String gamePlatform, String gameRelease){
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("game", gameName);
        //values.put("genre", gameGenre );
        //values.put("platform", gamePlatform);
        //values.put("release", gameRelease);

        // Insert the row into the movies table
        db.insert("gameDescription", null, values);
    }
}
*/
