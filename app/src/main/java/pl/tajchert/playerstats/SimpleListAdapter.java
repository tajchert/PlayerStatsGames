package pl.tajchert.playerstats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.SimpleHolder> {

    public SimpleListAdapter() {

    }

    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_simple, parent, false);
            return new SimpleHolder(itemView);

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(SimpleHolder userStatsCard, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class SimpleHolder extends RecyclerView.ViewHolder {

        public SimpleHolder(View itemView) {
            super(itemView);
        }
    }
}
