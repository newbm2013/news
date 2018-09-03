package artemshumidub.ru.news.ui.activity.news;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import artemshumidub.ru.news.R;
import artemshumidub.ru.news.data.entity.FullNews;
import artemshumidub.ru.news.ui.activity.base.BaseActivity;


public class NewsActivity extends BaseActivity implements  INewsContract.IView{

    @BindView(R.id.ll_content_news)
    LinearLayout contentView;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_full_news)
    TextView tvFullNews;

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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_fulltext_title)
    TextView tvExpandedToolbarTitle;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Inject
    NewsPresenter presenter;

    public static final String ID_NEWS_KEY = "idNews";
    private long idNews = 0;
    private String news_title = "";
    private static final String NEWS_TITLE_COLLAPSED = "Новость";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        if (getIntent().getExtras()!=null){
            idNews = getIntent().getExtras().getLong(ID_NEWS_KEY, 0);
        }
        presenter.attachView(this);
        setSupportActionBar(toolbar);
        setAppBarLayoutExpandable(false);
        collapsingToolbarLayout.setTitle(NEWS_TITLE_COLLAPSED);
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBarLayout.addOnOffsetChangedListener(getOnOffsetChangedListener());
        presenter.getNews(idNews);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
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
        contentView.setVisibility(View.INVISIBLE);
        emptyContentLayout.setVisibility(View.INVISIBLE);
        internetErrorLayout.setVisibility(View.INVISIBLE);
        serverErrorLayout.setVisibility(View.INVISIBLE);
        unknownErrorLayout.setVisibility(View.INVISIBLE);
        stopProgress();
    }

    @Override
    public void startProgress() {
        clearScreen();
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void setNews(FullNews news) {
        news_title = news.getTitle();
        tvExpandedToolbarTitle.setText(news_title);
        setAppBarLayoutExpandable(true);
        contentView.setVisibility(View.VISIBLE);
        tvFullNews.setText(Html.fromHtml(news.getFullDescription()));
        tvFullNews.setMovementMethod(LinkMovementMethod.getInstance());
        tvDate.setText(news.getDate());
    }

    private void setAppBarLayoutExpandable(boolean isExpandable) {
        if (!isExpandable) {
            appBarLayout.setExpanded(false, false);
            appBarLayout.setActivated(false);
            CoordinatorLayout.LayoutParams lp =
                    (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
            lp.height = (int) getResources().getDimension(R.dimen.app_bar_collapsed_weight);
        } else {
            appBarLayout.setExpanded(true, false);
            appBarLayout.setActivated(true);
            CoordinatorLayout.LayoutParams lp =
                    (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
            lp.height = (int) getResources().getDimension(R.dimen.app_bar_expanded_weight);
            lp.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT;
        }
    }

    private AppBarLayout.OnOffsetChangedListener getOnOffsetChangedListener(){
        return new AppBarLayout.OnOffsetChangedListener() {
             boolean isShow = false;
             int scrollRange = -1;

             @Override
             public void onOffsetChanged(AppBarLayout appBarLayout1, int verticalOffset) {
                 if (scrollRange == -1) {
                     scrollRange = appBarLayout1.getTotalScrollRange();
                 }
                 if (scrollRange + verticalOffset == 0) {
                     collapsingToolbarLayout.setTitle(" ");
                     tvExpandedToolbarTitle.setVisibility(View.VISIBLE);
                     isShow = true;
                 } else if (isShow) {
                     collapsingToolbarLayout.setTitle(news_title);
                     tvExpandedToolbarTitle.setVisibility(View.INVISIBLE);
                     isShow = false;
                 }
             }
         };
    }
}
