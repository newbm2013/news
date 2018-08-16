package artemshumidub.ru.sebbianews.ui.activity.category;

import java.util.List;

import artemshumidub.ru.sebbianews.data.entity.Category;
import artemshumidub.ru.sebbianews.ui.activity.base.BasePresenter;
import artemshumidub.ru.sebbianews.ui.activity.base.IViewContract;

public interface ICategoriesContract  {

    interface IView extends IViewContract{

        void setCategories(List<Category> list);

        void goToNewsList(int idCategory);
    }

    interface IPresenter extends BasePresenter<ICategoriesContract.IView> {

        void getCategories();

    }
}
