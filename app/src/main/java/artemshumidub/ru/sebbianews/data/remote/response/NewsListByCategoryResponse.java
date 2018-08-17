package artemshumidub.ru.sebbianews.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import artemshumidub.ru.sebbianews.data.entity.ShortNews;

public class NewsListByCategoryResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("list")
    private List<ShortNews> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ShortNews> getList() {
        return list;
    }

    public void setList(List<ShortNews> list) {
        this.list = list;
    }
}
