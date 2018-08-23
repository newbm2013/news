package artemshumidub.ru.news.data.remote.api;

import artemshumidub.ru.news.data.remote.response.CategoryResponse;
import artemshumidub.ru.news.data.remote.response.NewsListByCategoryResponse;
import artemshumidub.ru.news.data.remote.response.NewsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {

    /**
    * Получить список категорий
    *
    */
    @Headers("Content-Type: application/json")
    @GET("news/categories")
    Observable<CategoryResponse> getListOfCategories();


    /**
     * Получить список новостей по id категории
     *
     */
    @Headers("Content-Type: application/json")
    @GET("news/categories/{id}/news")
    Observable<NewsListByCategoryResponse> getNewsList(@Path("id") long idCategory, @Query("page") int page);

    /**
     * Получить новость по id новости
     *
     */
    @Headers("Content-Type: application/json")
    @GET("news/details")
    Observable<NewsResponse> getNews(@Query("id") long idNews);

}
