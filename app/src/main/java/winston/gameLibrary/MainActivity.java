package winston.gameLibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;

public class MainActivity extends AppCompatActivity {
    private CursorAdapter mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}

