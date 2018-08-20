package artemshumidub.ru.sebbianews.ui.activity.newslist;

import java.util.List;
import artemshumidub.ru.sebbianews.data.entity.ShortNews;
import artemshumidub.ru.sebbianews.ui.activity.base.BasePresenter;
import artemshumidub.ru.sebbianews.ui.activity.base.IProgressViewContract;
import artemshumidub.ru.sebbianews.ui.activity.base.IViewContract;

public interface INewsListContract  {
    interface IView extends IProgressViewContract, IViewContract {

        void setNewsList(List<ShortNews> list);

        void setNextNewsList(List<ShortNews> list);

        void goToNews(long idNews);

        void showMessage(String message);

        void setNewsListGetting(boolean newsListGetting);

        void hideSmallProgressBar();

        void showSmallProgressBar();

        void setPage(int page);

        int getPage();
    }

    interface IPresenter extends BasePresenter<INewsListContract.IView> {
        void getFirstPageOfNewsList(long idCategory);
        void getNextPageOfNewsList(long idCategory, int page);
        void goToNews(long idNews);
    }
}