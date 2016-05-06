package winston.gameLibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
