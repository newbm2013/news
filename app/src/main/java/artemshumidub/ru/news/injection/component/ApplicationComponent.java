package artemshumidub.ru.news.injection.component;

import android.content.Context;

import javax.inject.Singleton;
import artemshumidub.ru.news.NewsApp;
import artemshumidub.ru.news.data.util.ConnectionUtil;
import artemshumidub.ru.news.injection.module.ApplicationModule;
import dagger.Component;

/**
 * Component : Application
 *
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @SuppressWarnings("unused")
    Context context();

    @SuppressWarnings("unused")
    NewsApp application();

    ConnectionUtil connectionUtil();

}