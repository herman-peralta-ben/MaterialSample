package example.com.materialsample.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {

    protected final int itemLayout;
    protected List<String> items;
    protected ListClickListener listClickListener;

    /**
     * @param itemLayout - root must be TextView
     */
    public StringAdapter(@LayoutRes final int itemLayout, ListClickListener listClickListener) {
        this.itemLayout = itemLayout;
        this.listClickListener = listClickListener;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new StringViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(items.get(position));
    }

    public void setItems(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public interface ListClickListener {
        void onItemClicked(final int position);
    }

    class StringViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        StringViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listClickListener != null) {
                int position = getAdapterPosition();
                listClickListener.onItemClicked(position);
            }
        }
    }
}
