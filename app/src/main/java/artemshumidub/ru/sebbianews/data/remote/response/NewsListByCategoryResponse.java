package artemshumidub.ru.sebbianews.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import artemshumidub.ru.sebbianews.data.entity.ShortNews;

public class NewsListByCategoryResponse {

    @SerializedName("code")
    private Long code;

    @SerializedName("list")
    private List<ShortNews> list;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<ShortNews> getList() {
        return list;
    }

    public void setList(List<ShortNews> list) {
        this.list = list;
    }
}
