package artemshumidub.ru.news.ui.activity.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;

import javax.inject.Inject;

import artemshumidub.ru.news.R;
import artemshumidub.ru.news.data.entity.Category;
import artemshumidub.ru.news.ui.activity.base.BaseActivity;
import artemshumidub.ru.news.ui.activity.newslist.NewsListActivity;
import artemshumidub.ru.news.ui.adapter.CategoryRVAdapter;

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

    @BindView(R.id.unknoun_error_layout)
    FrameLayout unknownErrorLayout;

    @Inject
    CategoriesPresenter presenter;

    public static final String ID_CATEGORY_KEY = "idCategory";
    private final static String TITLE_TEXT = "Список категорий";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        presenter.attachView(this);
        presenter.getCategories();
        if (getSupportActionBar()!=null) getSupportActionBar().setTitle(TITLE_TEXT);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        swipeRefreshLayout.setOnRefreshListener(() ->
            presenter.getCategories());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
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
    public void showUnknownError() {
        clearScreen();
        unknownErrorLayout.setVisibility(View.VISIBLE);
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
        unknownErrorLayout.setVisibility(View.INVISIBLE);
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
    public void setCategories(List<Category> list) {
        CategoryRVAdapter adapter = new CategoryRVAdapter(this, list);
        adapter.setOnItemListener(this::goToNewsList);
        if (recyclerView!=null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goToNewsList(long idCategory) {
        Intent intent = new Intent(this, NewsListActivity.class);
        intent.putExtra(ID_CATEGORY_KEY, idCategory);
        startActivity(intent);
    }
}
