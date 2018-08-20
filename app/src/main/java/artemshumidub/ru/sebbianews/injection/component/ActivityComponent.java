package artemshumidub.ru.sebbianews.injection.component;

import artemshumidub.ru.sebbianews.injection.module.ActivityModule;
import artemshumidub.ru.sebbianews.injection.scope.PerActivity;
import artemshumidub.ru.sebbianews.ui.activity.category.CategoriesActivity;
import artemshumidub.ru.sebbianews.ui.activity.news.NewsActivity;
import artemshumidub.ru.sebbianews.ui.activity.newslist.NewsListActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(CategoriesActivity categoriesActivity);

    void inject(NewsListActivity newsListActivity);

    void inject(NewsActivity newsActivity);

}
