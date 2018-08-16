package artemshumidub.ru.sebbianews.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import artemshumidub.ru.sebbianews.R;
import butterknife.BindView;
import butterknife.ButterKnife;

//todo заменить настоящим адаптером и удалить фейковый + ресурсы
public class FakeRVAdapter extends RecyclerView.Adapter<FakeRVAdapter.Holder> {

    private final List<String> items;
    private final Context context;

    FakeRVAdapter(Context context, List<String> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fake_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemTextView.setText(holder.itemTextView.getText().toString() + " " + position);
    }

    @Override
    public int getItemCount() {
        return 50;
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
