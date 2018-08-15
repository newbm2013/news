package artemshumidub.ru.sebbianews.ui.activity.category;

import artemshumidub.ru.sebbianews.ui.activity.base.BasePresenter;
import artemshumidub.ru.sebbianews.ui.activity.base.IViewContract;
import artemshumidub.ru.sebbianews.ui.activity.news.INewsContract;

public interface ICategoriesContract  {
    interface IView extends IViewContract{}

    interface IPresenter extends BasePresenter<INewsContract.IView> {}
}
