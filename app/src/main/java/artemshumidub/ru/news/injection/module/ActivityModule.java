package artemshumidub.ru.news.injection.module;

import android.app.Activity;
import android.content.Context;
import artemshumidub.ru.news.data.util.ConnectionUtil;
import artemshumidub.ru.news.ui.activity.category.CategoriesPresenter;
import artemshumidub.ru.news.ui.activity.category.ICategoriesContract;
import artemshumidub.ru.news.ui.activity.news.INewsContract;
import artemshumidub.ru.news.ui.activity.news.NewsPresenter;
import artemshumidub.ru.news.ui.activity.newslist.INewsListContract;
import artemshumidub.ru.news.ui.activity.newslist.NewsListPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Module : Activity
 *
 */
@Module
public class ActivityModule {

    @SuppressWarnings("unused")
    final private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    ICategoriesContract.IPresenter provideCategoryPresenter(Context appContext, ConnectionUtil connectionUtil){
        return new CategoriesPresenter(appContext, connectionUtil);
    }

    @Provides
    INewsListContract.IPresenter provideNewsListPresenter(Context appContext, ConnectionUtil connectionUtil){
        return new NewsListPresenter(appContext, connectionUtil);
    }

    @Provides
    INewsContract.IPresenter provideNewsPresenter(Context appContext, ConnectionUtil connectionUtil){
        return new NewsPresenter(appContext, connectionUtil);
    }

}
