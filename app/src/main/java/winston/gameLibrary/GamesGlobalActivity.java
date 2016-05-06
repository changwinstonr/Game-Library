package winston.gameLibrary;

import android.support.v7.app.AppCompatActivity;

public class GamesGlobalActivity extends AppCompatActivity{
    //per Graeme's advice. variables globalized
    private String m_id;
    private String mName;
    private String mGenre;
    private String mRelease;
    private String mBlurb;

    //generate get-set
    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String getmRelease() {
        return mRelease;
    }

    public void setmRelease(String mRelease) {
        this.mRelease = mRelease;
    }

    public String getmBlurb() {
        return mBlurb;
    }

    public void setmBlurb(String mBlurb) {
        this.mBlurb = mBlurb;
    }
}
