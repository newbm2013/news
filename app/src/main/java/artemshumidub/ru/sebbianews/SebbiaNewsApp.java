package artemshumidub.ru.sebbianews;

import android.annotation.SuppressLint;
import android.app.Application;

import artemshumidub.ru.sebbianews.data.util.ConnectionUtil;

public class SebbiaNewsApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static ConnectionUtil connectionUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        connectionUtil = new ConnectionUtil(this);
    }

    public static ConnectionUtil getConnectionUtil() {
        return connectionUtil;
    }
}
