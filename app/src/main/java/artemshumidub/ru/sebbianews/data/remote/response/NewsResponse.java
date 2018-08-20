package artemshumidub.ru.sebbianews.data.remote.response;

import com.google.gson.annotations.SerializedName;
import artemshumidub.ru.sebbianews.data.entity.FullNews;

public class NewsResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("news")
    private FullNews news;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public FullNews getNews() {
        return news;
    }

    public void setNews(FullNews news) {
        this.news = news;
    }
}
