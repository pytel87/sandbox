package u3k.cleancode.base;

import android.app.Application;

/**
 * Created by Vladimir Skoupy.
 */

public class BaseApp extends Application {

    private static BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApp getInstance() {
        return instance;
    }


}
