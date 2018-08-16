package artemshumidub.ru.sebbianews.data.remote.api;

import artemshumidub.ru.sebbianews.data.remote.response.CategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    /**
    * Получить список категорий
    *
    */

    @GET("/news/categories")
    Call<CategoryResponse> getListOfCategories();

}
