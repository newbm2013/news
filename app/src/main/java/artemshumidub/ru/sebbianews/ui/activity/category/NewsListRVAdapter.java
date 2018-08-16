package artemshumidub.ru.sebbianews.ui.activity.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import artemshumidub.ru.sebbianews.R;
import artemshumidub.ru.sebbianews.data.entity.Category;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListRVAdapter extends RecyclerView.Adapter<NewsListRVAdapter.Holder> {

    //todo change type
    private final List<Category> list;
    private final Context context;

    NewsListRVAdapter(Context context, List<Category> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemTextView.setText(list.get(position).getName());
        holder.itemTextView.setTag(list.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView itemTextView;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
