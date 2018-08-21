package artemshumidub.ru.news;

import android.app.Application;
import android.content.Context;
import artemshumidub.ru.news.injection.component.ApplicationComponent;
import artemshumidub.ru.news.injection.component.DaggerApplicationComponent;
import artemshumidub.ru.news.injection.module.ApplicationModule;

public class NewsApp extends Application {

    private ApplicationComponent mApplicationComponent;

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

}
