package artemshumidub.ru.sebbianews.data.repository;

import artemshumidub.ru.sebbianews.data.remote.response.CategoryResponse;
import io.reactivex.Observable;

public interface IRemoteRepositoryContract  {

    Observable<CategoryResponse> getCategory();

}
