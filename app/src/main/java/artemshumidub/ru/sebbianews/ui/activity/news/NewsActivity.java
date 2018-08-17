package artemshumidub.ru.sebbianews.ui.activity.news;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import artemshumidub.ru.sebbianews.R;
import artemshumidub.ru.sebbianews.data.entity.FullNews;
import artemshumidub.ru.sebbianews.ui.activity.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

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

    private NewsPresenter presenter;


    public static final String ID_NEWS_KEY = "idNews";
    long idNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        idNews = getIntent().getExtras().getLong(ID_NEWS_KEY);

        presenter= new NewsPresenter(this);
        presenter.getNews(idNews);

        getSupportActionBar().setTitle("Новость");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) this.finish();
        return true;
    }

    @Override
    public void setNews(FullNews news) {
        contentView.setVisibility(View.VISIBLE);
        tvFullNews.setText(Html.fromHtml(news.getFullDescription()));
        tvFullNews.setMovementMethod(LinkMovementMethod.getInstance());
        tvDate.setText(news.getDate());
        getSupportActionBar().setTitle(news.getTitle());
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
        contentView.setVisibility(View.INVISIBLE);
        emptyContentLayout.setVisibility(View.INVISIBLE);
        internetErrorLayout.setVisibility(View.INVISIBLE);
        serverErrorLayout.setVisibility(View.INVISIBLE);
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
}
