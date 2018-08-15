package artemshumidub.ru.sebbianews.ui.activity.category;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

import artemshumidub.ru.sebbianews.SebbiaNewsApp;

public class CategoriesPresenter implements ICategoriesContract.IPresenter {

    private ICategoriesContract.IView view;

    CategoriesPresenter(ICategoriesContract.IView view){
        attachView(view);
    }

    @Override
    public void attachView(ICategoriesContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    @SuppressWarnings("unused")
    public void onStart() {

    }

    @Override
    public void onStop() {
        detachView();
    }

    @Override
    @SuppressWarnings("unused")
    public void onResume() {

    }

    @Override
    @SuppressWarnings("unused")
    public void onPause() {

    }

    @Override
    public void getCategories() {
        if(!SebbiaNewsApp.getConnectionUtil().checkInternetConnection()) view.showInternetError();
        else {
            // todo get items and change parameter type
            new Thread(
                    ()->{
                        for (int i=0; i<Integer.MAX_VALUE/100; i++){int i2 = i;}
                        ((CategoriesActivity) view).recyclerView.post(()-> {
                            List list = new ArrayList<String>();
                            view.stopProgress();
                            //todo delete random
                            if (list.size() == 0 && SystemClock.currentThreadTimeMillis()%2==0) view.showEmptyContentMessage();
                            else view.setCategories(list);
                        });
                    }).start();
        }
    }
}
