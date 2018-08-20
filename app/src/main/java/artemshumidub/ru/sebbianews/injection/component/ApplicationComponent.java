package artemshumidub.ru.sebbianews.injection.component;

import android.content.Context;

import javax.inject.Singleton;
import artemshumidub.ru.sebbianews.NewsApp;
import artemshumidub.ru.sebbianews.data.util.ConnectionUtil;
import artemshumidub.ru.sebbianews.injection.module.ApplicationModule;
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