package artemshumidub.ru.sebbianews.ui.activity.newslist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    @BindView(R.id.ll_progress_bar_news_item)
    LinearLayout smallProgressBar;

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

    private long idCategory = 0;
    private int page = 0;
    private static final String TITLE_TEXT = "Список новостей" ;

    private NewsListPresenter presenter;
    private NewsListRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        if ( getIntent().getExtras()!=null) idCategory = getIntent().getExtras()
                .getLong(CategoriesActivity.ID_CATEGORY_KEY, 0);

        if (getSupportActionBar()!= null){
            ActionBar actionBar;
            actionBar = getSupportActionBar();
            actionBar.setTitle(TITLE_TEXT);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        presenter = new NewsListPresenter();
        presenter.attachView(this);
        presenter.getFirstPageOfNewsList(idCategory);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        swipeRefreshLayout.setOnRefreshListener(() ->
                presenter.getFirstPageOfNewsList(idCategory));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) this.finish();
        return true;
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
        hideSmallProgressBar();
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
    public void hideSmallProgressBar(){
        smallProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSmallProgressBar() {
        smallProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNewsList(List<ShortNews> list) {
        adapter = new NewsListRVAdapter(list);
        adapter.setOnItemListener(this::goToNews);
        adapter.setOnLastPosition((List<ShortNews> oldList)->
            presenter.getNextPageOfNewsList(idCategory, page));
        if (recyclerView!=null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }
        hideSmallProgressBar();
    }

    @Override
    public void setNextNewsList(List<ShortNews> list) {
       for (ShortNews news: list){
           if (!adapter.getList().contains(news)) adapter.getList().add(news);
       }
       adapter.notifyDataSetChanged();
       if (recyclerView!=null) {
           recyclerView.setVisibility(View.VISIBLE);
        }
       hideSmallProgressBar();
    }

    @Override
    public void goToNews(long idNews) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra(NewsActivity.ID_NEWS_KEY, idNews);
        startActivity(intent);
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void setNewsListGetting(boolean newsListGetting) {
        if (recyclerView.getAdapter()!=null){
            ((NewsListRVAdapter)recyclerView.getAdapter())
                    .setLastPositionCallbackEnable(newsListGetting);
        }
    }
}