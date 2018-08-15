package artemshumidub.ru.sebbianews.ui.activity.category;

import java.util.List;

import artemshumidub.ru.sebbianews.ui.activity.base.BasePresenter;
import artemshumidub.ru.sebbianews.ui.activity.base.IViewContract;

public interface ICategoriesContract  {

    interface IView extends IViewContract{

        //todo change generic type
        void setCategories(List<String> list);
    }

    interface IPresenter extends BasePresenter<ICategoriesContract.IView> {

        void getCategories();

    }
}
