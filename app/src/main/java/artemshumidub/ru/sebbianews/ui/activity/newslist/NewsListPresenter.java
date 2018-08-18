package artemshumidub.ru.sebbianews.ui.activity.newslist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import artemshumidub.ru.sebbianews.R;
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

    private static final String ALL_NEWS_GOT = "Все новости загружены";
    private INewsListContract.IView view;
    private RemoteRepository remoteRepository;
    private List<ShortNews> list;
    private boolean isLatsNewsGot = false;
    private Context context;

    NewsListPresenter(INewsListContract.IView view){
        context = (NewsListActivity)view;
        attachView(view);
        list = new ArrayList<>();
    }

    @Override
    public void attachView(INewsListContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {   }

    @Override
    public void onStart() {    }

    @Override
    public void onStop() {   }

    @Override
    public void onResume() {   }

    @Override
    public void onPause() {   }

    @Override
    public void getFirstPageOfNewsList(long idCategory) {
        view.startProgress();
        if (!SebbiaNewsApp.getConnectionUtil().checkInternetConnection()){
            view.showInternetError();
            return;
        }
        if (remoteRepository == null) {
            remoteRepository = new RemoteRepository((NewsListActivity) view);
        }
        Observable<NewsListByCategoryResponse> observable = remoteRepository.getNewsList(idCategory, 0);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsListByCategoryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(NewsListByCategoryResponse response) {
                        view.stopProgress();
                        isLatsNewsGot = response.getList().size() < NewsListActivity.NEWS_PER_PAGE;
                        if (isLatsNewsGot) view.showMessage(ALL_NEWS_GOT);
                        view.setPage(0);
                        list.clear();
                        list.addAll(response.getList());
                        if (response.getList().size()>=NewsListActivity.NEWS_PER_PAGE){
                            view.setPage(view.getPage()+1);
                        }
                        if (list.isEmpty()) view.showEmptyContentMessage();
                        else view.setNewsList(response.getList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setPage(0);
                        if (e instanceof ServerErrorException) {
                            view.showServerError();
                        } else if (e instanceof NoInternetException) {
                            view.showInternetError();
                        } else {
                        view.showUnknownError();
                    }
                        ((NewsListActivity) view).setNewsListGetting(false);
                    }

                    @Override
                    public void onComplete() {
                        view.stopProgress();
                        ((NewsListActivity) view).setNewsListGetting(false);
                    }
                });
    }

    @Override
    public void getNextPageOfNewsList(long idCategory, int page) {
        if (!SebbiaNewsApp.getConnectionUtil().checkInternetConnection()){
            view.showMessage(context.getResources().getString(R.string.internet_error));
            return;
        }
        if (isLatsNewsGot) return;
        if (remoteRepository == null) {
            remoteRepository = new RemoteRepository((NewsListActivity) view);
        }
        Observable<NewsListByCategoryResponse> observable = remoteRepository.getNewsList(idCategory, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsListByCategoryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {  }

                    @Override
                    public void onNext(NewsListByCategoryResponse response) {

                        if (response.getList().size() < NewsListActivity.NEWS_PER_PAGE){
                            isLatsNewsGot = true;
                            view.showMessage(ALL_NEWS_GOT);
                        }

                        if (page == 0) {
                            list.clear();
                        }

                        list.addAll(response.getList());

                        if (response.getList().size()>=NewsListActivity.NEWS_PER_PAGE){
                            view.setPage(view.getPage()+1);
                        }

                        if (list.isEmpty()) view.showEmptyContentMessage();
                        else view.addNewsList(list);
                        //todo set plural
//                        view.showMessage("Добавлено новостей: " +  list.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setPage(0);
                        if (e instanceof ServerErrorException) {
                            //todo dagger + app context + delete casting view
                            view.showMessage(context.getResources().getString(R.string.server_error));
                        } else if (e instanceof NoInternetException) {
                           view.showMessage(context.getResources().getString(R.string.internet_error));
                        } else {
                            view.showMessage(context.getResources().getString(R.string.unknown_error));
                    }
                        ((NewsListActivity) view).setNewsListGetting(false);
                    }

                    @Override
                    public void onComplete() {
                        ((NewsListActivity) view).setNewsListGetting(false);
                    }
                });
    }
}
