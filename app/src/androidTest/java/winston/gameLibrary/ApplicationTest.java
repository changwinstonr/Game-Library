package winston.gameLibrary;

import android.app.Application;
import android.test.ApplicationTestCase;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

/*    private Context context;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = getSystemContext();
    }

    @SmallTest
    public void test() {
        int cursor = GameSQLiteDatabaseHelper.getInstance(context).searchLibrary().getCount();
        assertEquals(89, cursor);
    }
    }*/

}