package artemshumidub.ru.sebbianews.ui.activity.newslist;

import java.util.List;

import artemshumidub.ru.sebbianews.data.entity.ShortNews;
import artemshumidub.ru.sebbianews.ui.activity.base.BasePresenter;
import artemshumidub.ru.sebbianews.ui.activity.base.IProgressViewContract;
import artemshumidub.ru.sebbianews.ui.activity.base.IViewContract;

public interface INewsListContract  {
    interface IView extends IProgressViewContract, IViewContract {

        void setNewsList(List<ShortNews> list);

        void goToNews(long idNews);

        void setPage(int page);

        int getPage();

    }

    interface IPresenter extends BasePresenter<INewsListContract.IView> {
        void getNewsList(long idCategory, int page);
    }
}