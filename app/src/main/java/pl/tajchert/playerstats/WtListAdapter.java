package pl.tajchert.playerstats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.tajchert.playerstats.api.ApiWarThunder;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class WtListAdapter extends RecyclerView.Adapter<WtListAdapter.UserStatsCard> {
    private ArrayList<ApiWarThunder.Result> mDataset;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public WtListAdapter(ArrayList<ApiWarThunder.Result> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UserStatsCard onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_wt_stats, parent, false);
        return new UserStatsCard(itemView);
    }

    @Override
    public void onBindViewHolder(UserStatsCard userStatsCard, int i) {
        ApiWarThunder.Result user = mDataset.get(i);
        if(user != null) {
            userStatsCard.infoText.setText(user.toString());
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class UserStatsCard extends RecyclerView.ViewHolder {
        @InjectView(R.id.info_text)
        TextView infoText;

        public UserStatsCard(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
