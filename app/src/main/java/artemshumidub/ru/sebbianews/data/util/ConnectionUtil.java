package artemshumidub.ru.sebbianews.data.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Connection Util
 */

public class ConnectionUtil{

    private final Context context;

    public ConnectionUtil(Context context) {
        this.context = context;
    }

    @SuppressWarnings({"BooleanMethodIsAlwaysInverted", "unused"})
    public boolean checkInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
