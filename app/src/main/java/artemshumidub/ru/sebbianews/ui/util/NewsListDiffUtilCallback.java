package artemshumidub.ru.sebbianews.ui.util;

import android.support.v7.util.DiffUtil;

import java.util.List;

import artemshumidub.ru.sebbianews.data.entity.ShortNews;

public class NewsListDiffUtilCallback extends DiffUtil.Callback {

    private final List<ShortNews> oldList;
    private final List<ShortNews> newList;

    public NewsListDiffUtilCallback(List<ShortNews> oldList, List<ShortNews> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

        ShortNews oldNews = oldList.get(oldItemPosition);
        ShortNews newNews = newList.get(newItemPosition);

        return (oldNews.getTitle() == newNews.getTitle()
                & oldNews.getShortDescription() == newNews.getShortDescription()
                & oldNews.getDate() == newNews.getDate());
    }
}
