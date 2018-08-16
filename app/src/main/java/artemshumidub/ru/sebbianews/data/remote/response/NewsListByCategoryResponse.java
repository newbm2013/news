package artemshumidub.ru.sebbianews.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import artemshumidub.ru.sebbianews.data.entity.News;

public class NewsListByCategoryResponse {

    @SerializedName("code")
    private Long code;

    @SerializedName("list")
    private List<News> list;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }
}
