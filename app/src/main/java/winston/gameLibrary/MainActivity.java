package winston.gameLibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStringList = new LinkedList<>();
        mStringList.add("Read");
        mStringList.add("Learn");
        mStringList.add("Buy");

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList);

        ListView listName = (ListView) (findViewById(R.id.listView));
        listName.setAdapter(mAdapter);
    }
}
