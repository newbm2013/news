package artemshumidub.ru.sebbianews.data.remote.response;

import com.google.gson.annotations.SerializedName;

import artemshumidub.ru.sebbianews.data.entity.News;

public class NewsResponse {
    @SerializedName("code")
    private Long code;

    @SerializedName("news") // new и остальные поля надо переписать
    private News news;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
