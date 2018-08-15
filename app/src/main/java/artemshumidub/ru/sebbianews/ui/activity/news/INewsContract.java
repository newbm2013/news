package artemshumidub.ru.sebbianews.ui.activity.news;

import artemshumidub.ru.sebbianews.ui.activity.base.BasePresenter;
import artemshumidub.ru.sebbianews.ui.activity.base.IViewContract;

public interface INewsContract {

    interface IView extends IViewContract{}

    interface IPresenter extends BasePresenter<IView> {}
}
