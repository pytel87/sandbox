package u3k.cleancode.utils;

import android.util.Log;

import u3k.cleancode.BuildConfig;


/**
 * Created by Vladimir Skoupy.
 */

public class Logg {

    public static void d(String tag, String message) {
        if (BuildConfig.DEBUGGING) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (BuildConfig.DEBUGGING) {
            Log.e(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (BuildConfig.DEBUGGING) {
            Log.i(tag, message);
        }
    }

    public static void ex(String tag, Throwable e) {
        if (BuildConfig.DEBUGGING) {
            Log.e(tag, e.getMessage(), e);
        }
    }


}
