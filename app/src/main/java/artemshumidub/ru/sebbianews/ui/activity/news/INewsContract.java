package artemshumidub.ru.sebbianews.ui.activity.news;

import artemshumidub.ru.sebbianews.data.entity.FullNews;
import artemshumidub.ru.sebbianews.ui.activity.base.BasePresenter;
import artemshumidub.ru.sebbianews.ui.activity.base.IProgressViewContract;
import artemshumidub.ru.sebbianews.ui.activity.base.IViewContract;

public interface INewsContract {

    interface IView extends IViewContract, IProgressViewContract{

        void setNews(FullNews news);

    }

    interface IPresenter extends BasePresenter<IView> {

        void getNews(long idNews);

    }
}
