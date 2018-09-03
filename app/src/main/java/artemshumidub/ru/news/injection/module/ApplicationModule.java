package artemshumidub.ru.news.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import artemshumidub.ru.news.NewsApp;
import artemshumidub.ru.news.data.util.ConnectionUtil;
import dagger.Module;
import dagger.Provides;

/**
 * Module : Application
 *
 */
@Module
public class ApplicationModule {

    protected final NewsApp app;

    public ApplicationModule(NewsApp app) {
        this.app = app;
    }

    @Provides
    NewsApp provideApplication() {
        return app;
    }

    @Provides
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    ConnectionUtil provideConnectionUtil() {
        return new ConnectionUtil(app);
    }

}
