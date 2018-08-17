package artemshumidub.ru.sebbianews.ui.activity.newslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import artemshumidub.ru.sebbianews.R;
import artemshumidub.ru.sebbianews.data.entity.ShortNews;
import artemshumidub.ru.sebbianews.ui.activity.base.BaseActivity;
import artemshumidub.ru.sebbianews.ui.activity.category.CategoriesActivity;
import artemshumidub.ru.sebbianews.ui.activity.news.NewsActivity;
import artemshumidub.ru.sebbianews.ui.adapter.NewsListRVAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListActivity extends BaseActivity implements INewsListContract.IView{

    @BindView(R.id.news_list_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.news_list_rv)
    RecyclerView recyclerView;

    @BindView(R.id.progress_layout)
    FrameLayout progressLayout;

    @BindView(R.id.empty_content_layout)
    FrameLayout emptyContentLayout;

    @BindView(R.id.internet_error_layout)
    FrameLayout internetErrorLayout;

    @BindView(R.id.server_error_layout)
    FrameLayout serverErrorLayout;

    private NewsListPresenter presenter;

    private long idCategory;
    private int page;
    private static final int NEWS_PER_PAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        idCategory = getIntent().getExtras().getLong(CategoriesActivity.ID_CATEGORY_KEY);
        page=0;

        getSupportActionBar().setTitle("Список новостей");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new NewsListPresenter(this);
        presenter.getNewsList(idCategory, page);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getNewsList(idCategory, page);
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
    public void setNewsList(List<ShortNews> list) {
        NewsListRVAdapter adapter = new NewsListRVAdapter(this, list);
        adapter.setOnItemlistener(this::goToNews);
        if (recyclerView!=null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.scrollToPosition(page*NEWS_PER_PAGE);
        }
    }

    @Override
    public void goToNews(long idNews) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra(NewsActivity.ID_NEWS_KEY, idNews);
        startActivity(intent);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) this.finish();
        return true;
    }
}
