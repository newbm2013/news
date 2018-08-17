package artemshumidub.ru.sebbianews.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import artemshumidub.ru.sebbianews.R;
import artemshumidub.ru.sebbianews.data.entity.ShortNews;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListRVAdapter extends RecyclerView.Adapter<NewsListRVAdapter.Holder> {

    private final List<ShortNews> list;
    private final Context context;
    private OnItemListener onItemlistener;

    public NewsListRVAdapter(Context context, List<ShortNews> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvHead.setText(list.get(position).getTitle());
        holder.tvDate.setText(list.get(position).getDate());
        holder.tvShortDesc.setText(list.get(position).getShortDescription());
        long idNews = list.get(position).getId();
        holder.llNewsItem.setOnClickListener((v)->onItemlistener.onItemClick(idNews));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_news_item)
        LinearLayout llNewsItem;

        @BindView(R.id.tv_head)
        TextView tvHead;

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.tv_short_desc)
        TextView tvShortDesc;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemlistener(OnItemListener onItemlistener) {
        this.onItemlistener = onItemlistener;
    }

    public interface OnItemListener{
        void onItemClick(long id);
    }

}
