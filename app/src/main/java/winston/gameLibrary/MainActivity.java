package winston.gameLibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameDatabaseHelper db = new GameDatabaseHelper(this);


/*
        mStringList = new LinkedList<>();
        mStringList.add("Read");
        mStringList.add("Learn");
        mStringList.add("Buy");

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList);

        ListView listName = (ListView) (findViewById(R.id.listView));
        listName.setAdapter(mAdapter);*/
    }
}
