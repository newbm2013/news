package artemshumidub.ru.sebbianews.data.repository;

import artemshumidub.ru.sebbianews.data.entity.ShortNews;
import artemshumidub.ru.sebbianews.data.remote.response.CategoryResponse;
import artemshumidub.ru.sebbianews.data.remote.response.NewsListByCategoryResponse;
import artemshumidub.ru.sebbianews.data.remote.response.NewsResponse;
import io.reactivex.Observable;

public interface IRemoteRepositoryContract  {

    Observable<CategoryResponse> getCategory();
    Observable<NewsListByCategoryResponse> getNewsList(int categoryId, int page);
    Observable<NewsResponse> getNews(int ibNews);

}
