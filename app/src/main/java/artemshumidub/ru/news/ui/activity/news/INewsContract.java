package artemshumidub.ru.news.ui.activity.news;

import artemshumidub.ru.news.data.entity.FullNews;
import artemshumidub.ru.news.ui.activity.base.BasePresenter;
import artemshumidub.ru.news.ui.activity.base.IProgressViewContract;
import artemshumidub.ru.news.ui.activity.base.IViewContract;

public interface INewsContract {

    interface IView extends IViewContract, IProgressViewContract{

        void setNews(FullNews news);

    }

    interface IPresenter extends BasePresenter<IView> {

        void getNews(long idNews);

    }
}
