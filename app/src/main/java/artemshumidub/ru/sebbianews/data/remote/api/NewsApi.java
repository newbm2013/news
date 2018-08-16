package artemshumidub.ru.sebbianews.data.remote.api;

import artemshumidub.ru.sebbianews.data.remote.response.CategoryResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NewsApi {

    /**
    * Получить список категорий
    *
    */

    @GET("news/categories")
    Observable<CategoryResponse> getListOfCategories();

}
