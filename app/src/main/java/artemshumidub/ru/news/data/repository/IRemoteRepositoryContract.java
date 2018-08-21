package artemshumidub.ru.news.data.repository;

import artemshumidub.ru.news.data.entity.ShortNews;
import artemshumidub.ru.news.data.remote.response.CategoryResponse;
import artemshumidub.ru.news.data.remote.response.NewsListByCategoryResponse;
import artemshumidub.ru.news.data.remote.response.NewsResponse;
import io.reactivex.Observable;

public interface IRemoteRepositoryContract  {

    Observable<CategoryResponse> getCategory();
    Observable<NewsListByCategoryResponse> getNewsList(long categoryId, int page);
    Observable<NewsResponse> getNews(long ibNews);

}
