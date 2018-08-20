package artemshumidub.ru.sebbianews.data.entity;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ShortNews {

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("date")
    private String date;

    @SerializedName("shortDescription")
    private String shortDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        try {
            @SuppressLint("SimpleDateFormat")
            Date d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS").parse(date);
            return new SimpleDateFormat("hh:mm dd.MM.yyyy", Locale.getDefault()).format(d);
        } catch (Exception e){
            return date;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
