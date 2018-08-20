package artemshumidub.ru.sebbianews;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import artemshumidub.ru.sebbianews.data.util.ConnectionUtil;
import artemshumidub.ru.sebbianews.injection.component.ApplicationComponent;
import artemshumidub.ru.sebbianews.injection.component.DaggerApplicationComponent;
import artemshumidub.ru.sebbianews.injection.module.ApplicationModule;

public class NewsApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @SuppressLint("StaticFieldLeak")
    private static ConnectionUtil connectionUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        connectionUtil = new ConnectionUtil(this);
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    public static NewsApp get(Context context) {
        return (NewsApp) context.getApplicationContext();
    }

    //todo delete
    public static ConnectionUtil getConnectionUtil() {
        return connectionUtil;
    }
}
