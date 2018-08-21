package artemshumidub.ru.news.injection.component;

import artemshumidub.ru.news.injection.module.ActivityModule;
import artemshumidub.ru.news.injection.scope.PerActivity;
import artemshumidub.ru.news.ui.activity.category.CategoriesActivity;
import artemshumidub.ru.news.ui.activity.news.NewsActivity;
import artemshumidub.ru.news.ui.activity.newslist.NewsListActivity;
import dagger.Component;

/**
* Component : Activity
*
*/
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(CategoriesActivity categoriesActivity);

    void inject(NewsListActivity newsListActivity);

    void inject(NewsActivity newsActivity);

}
