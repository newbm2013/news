package artemshumidub.ru.news.ui.activity.category;

import java.util.List;

import artemshumidub.ru.news.data.entity.Category;
import artemshumidub.ru.news.ui.activity.base.BasePresenter;
import artemshumidub.ru.news.ui.activity.base.IProgressViewContract;
import artemshumidub.ru.news.ui.activity.base.IViewContract;

public interface ICategoriesContract  {

    interface IView extends IProgressViewContract, IViewContract {

        void setCategories(List<Category> list);

        void goToNewsList(long idCategory);
    }

    interface IPresenter extends BasePresenter<ICategoriesContract.IView> {

        void getCategories();

    }
}
