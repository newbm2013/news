package artemshumidub.ru.sebbianews.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import artemshumidub.ru.sebbianews.data.entity.Category;

public class CategoryResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("list")
    private List<Category> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }
}
