package artemshumidub.ru.sebbianews.injection.module;

import android.app.Activity;

import artemshumidub.ru.sebbianews.data.util.ConnectionUtil;
import artemshumidub.ru.sebbianews.ui.activity.category.CategoriesPresenter;
import artemshumidub.ru.sebbianews.ui.activity.category.ICategoriesContract;
import artemshumidub.ru.sebbianews.ui.activity.news.INewsContract;
import artemshumidub.ru.sebbianews.ui.activity.news.NewsPresenter;
import artemshumidub.ru.sebbianews.ui.activity.newslist.INewsListContract;
import artemshumidub.ru.sebbianews.ui.activity.newslist.NewsListPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @SuppressWarnings("unused")
    final private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    ICategoriesContract.IPresenter provideCategoryPresenter(ConnectionUtil connectionUtil){
        return new CategoriesPresenter(connectionUtil);
    }

    @Provides
    INewsListContract.IPresenter provideNewsListPresenter(ConnectionUtil connectionUtil){
        return new NewsListPresenter(connectionUtil);
    }

    @Provides
    INewsContract.IPresenter provideNewsPresenter(ConnectionUtil connectionUtil){
        return new NewsPresenter(connectionUtil);
    }

}
