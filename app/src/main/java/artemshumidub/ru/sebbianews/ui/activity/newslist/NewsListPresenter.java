package artemshumidub.ru.sebbianews.ui.activity.newslist;

import java.util.ArrayList;
import java.util.List;

import artemshumidub.ru.sebbianews.SebbiaNewsApp;
import artemshumidub.ru.sebbianews.data.entity.ShortNews;
import artemshumidub.ru.sebbianews.data.exception.NoInternetException;
import artemshumidub.ru.sebbianews.data.exception.ServerErrorException;
import artemshumidub.ru.sebbianews.data.remote.response.NewsListByCategoryResponse;
import artemshumidub.ru.sebbianews.data.repository.RemoteRepository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter implements INewsListContract.IPresenter  {

    private INewsListContract.IView view;
    private RemoteRepository remoteRepository;
    private List<ShortNews> list;

    NewsListPresenter(INewsListContract.IView view){
        attachView(view);
        list = new ArrayList<ShortNews>();
    }

    @Override
    public void attachView(INewsListContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void getNewsList(int idCategory, int page) {
        view.startProgress();
        if (!SebbiaNewsApp.getConnectionUtil().checkInternetConnection()) view.showInternetError();
        else {

            if (remoteRepository == null) {
                remoteRepository = new RemoteRepository((NewsListActivity) view);
            }

            Observable<NewsListByCategoryResponse> observable = remoteRepository.getNewsList(idCategory, page);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsListByCategoryResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(NewsListByCategoryResponse response) {
                            view.stopProgress();

                            if (response.getList().size()!=0){
                                view.setPage(view.getPage()+1);
                                list.addAll(response.getList());
                            }
                            if (list.size() == 0) view.showEmptyContentMessage();
                            else view.setNewsList(list);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.setPage(0);
                            if (e instanceof ServerErrorException) {
                                view.showServerError();
                            } else if (e instanceof NoInternetException) {
                                view.showInternetError();
                            }
                        }

                        @Override
                        public void onComplete() {
                            view.stopProgress();
                        }
                    });

        }

    }
}
