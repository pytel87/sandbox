package u3k.cleancode.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Collection;
import java.util.Random;

/**
 * Created by Vladimir Skoupy.
 */

public class Utils {

    public static long ANIM_DURATION = 300;

    /**
     * Checks if the given collection is empty.
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        boolean result = false;
        if (collection == null || collection.isEmpty()) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if the given text is empty.
     *
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        boolean result = false;
        if (text == null || text.length() == 0) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if the given collection is not empty.
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * Checks if the given text is empty.
     *
     * @param text
     * @return
     */
    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getRandomString(int length) {
        final String characters = "ABCDEFGHIJLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        while (length > 0) {
            Random rand = new Random();
            result.append(characters.charAt(rand.nextInt(characters.length())));
            length--;
        }
        return result.toString();
    }

    public static boolean atLeastVersion(int sdkVersion) {
        return Build.VERSION.SDK_INT >= sdkVersion;
    }

    public static boolean atLeastLollipop() {
        return atLeastVersion(Build.VERSION_CODES.LOLLIPOP);
    }

    public static boolean atLeastMarshmallow() {
        return atLeastVersion(Build.VERSION_CODES.M);
    }

    public static boolean atLeastKitKat() {
        return atLeastVersion(Build.VERSION_CODES.KITKAT);
    }

    public static boolean atLeastJellyBean() {
        return atLeastVersion(Build.VERSION_CODES.JELLY_BEAN);
    }

    /**
     * Get the network info.
     *
     * @param context
     * @return
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity.
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    /**
     * Check if there is any connectivity to a wifi network.
     *
     * @param context
     * @return
     */
    public static boolean isConnectedWifi(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network.
     *
     * @param context
     * @return
     */
    public static boolean isConnectedMobile(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    public static int getActionBarHeight(Context context) {
        int result = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return result;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public static void appendText(StringBuilder stringBuilder, String text, String divider) {
        if (stringBuilder != null) {
            if (isNotEmpty(text)) {
                if (stringBuilder.length() > 0) {
                    if (isNotEmpty(divider)) {
                        stringBuilder.append(divider);
                    }
                }
                stringBuilder.append(text);
            }
        }
    }

    public static int dpToPx(float dp, Resources resources) {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }


    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static int getThemeColor(Context context, int colorResAttr) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{colorResAttr});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    public static String getThemeString(Context context, int colorResAttr) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{colorResAttr});
        String text = a.getString(0);
        a.recycle();
        return text;
    }

    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


}
