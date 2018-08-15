package artemshumidub.ru.sebbianews.ui.activity.category;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import artemshumidub.ru.sebbianews.R;
import artemshumidub.ru.sebbianews.ui.activity.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends BaseActivity implements ICategoriesContract.IView{

    @BindView(R.id.category_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.category_rv)
    RecyclerView recyclerView;

    @BindView(R.id.progress_layout)
    FrameLayout progressLayout;

    @BindView(R.id.empty_content_layout)
    FrameLayout emptyContentLayout;

    @BindView(R.id.internet_error_layout)
    FrameLayout internetErrorLayout;

    @BindView(R.id.server_error_layout)
    FrameLayout serverErrorLayout;

    private CategoriesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        presenter = new CategoriesPresenter(this);
        presenter.getCategories();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        swipeRefreshLayout.setOnRefreshListener(() -> {
            startProgress();
            swipeRefreshLayout.setRefreshing(true);
            presenter.getCategories();
        });
    }

    @Override
    public void showInternetError() {
        clearScreen();
        internetErrorLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void showServerError() {
        clearScreen();
        serverErrorLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void showEmptyContentMessage() {
        clearScreen();
        emptyContentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearScreen() {
        recyclerView.setVisibility(View.INVISIBLE);
        emptyContentLayout.setVisibility(View.INVISIBLE);
        internetErrorLayout.setVisibility(View.INVISIBLE);
        serverErrorLayout.setVisibility(View.INVISIBLE);
        stopProgress();
    }

    @Override
    public void startProgress() {
        clearScreen();
        if (recyclerView.getAdapter() == null ||
                recyclerView.getAdapter().getItemCount()==0
                || recyclerView.getVisibility() != View.VISIBLE ){
            progressLayout.setVisibility(View.VISIBLE);
        } else{
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void stopProgress() {
        progressLayout.setVisibility(View.GONE);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void setCategories(List<String> list) {
        //todo change parameter List
        FakeRVAdapter adapter = new FakeRVAdapter(this, new ArrayList());
        if (recyclerView!=null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
