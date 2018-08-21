package artemshumidub.ru.news.ui.activity.base;

public interface BasePresenter<V extends IViewContract> {

    void attachView(@SuppressWarnings("UnusedParameters") V IViewContract);

    @SuppressWarnings("unused")
    void detachView();

    @SuppressWarnings("unused")
    void onStart();

    @SuppressWarnings("unused")
    void onStop();

    @SuppressWarnings("unused")
    void onResume();

    @SuppressWarnings("unused")
    void onPause();

}
