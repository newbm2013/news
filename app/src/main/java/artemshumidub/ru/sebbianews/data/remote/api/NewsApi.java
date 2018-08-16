package artemshumidub.ru.sebbianews.data.remote.api;

import artemshumidub.ru.sebbianews.data.remote.response.CategoryResponse;
import artemshumidub.ru.sebbianews.data.remote.response.NewsListByCategoryResponse;
import artemshumidub.ru.sebbianews.data.remote.response.NewsResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {

    /**
    * Получить список категорий
    *
    */

    @GET("news/categories")
    Observable<CategoryResponse> getListOfCategories();

    @GET("news/categories/{id}/news")
    Observable<NewsListByCategoryResponse> getNewsList(@Path("id") int idCategory, @Query("page") int page);

    @GET("news/details")
    Observable<NewsResponse> getNews(@Query("id") int idNews);

}
