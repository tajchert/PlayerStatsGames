package pl.tajchert.playerstats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import pl.tajchert.playerstats.api.ApiWotStats;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class WotListAdapter extends RecyclerView.Adapter<WotListAdapter.UserStatsCard> {
    private ArrayList<ApiWotStats.WotUserStats> mDataset;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public WotListAdapter(ArrayList<ApiWotStats.WotUserStats> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UserStatsCard onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_wot_stats, parent, false);
        return new UserStatsCard(itemView);
    }

    @Override
    public void onBindViewHolder(UserStatsCard userStatsCard, int i) {
        ApiWotStats.WotUserStats user = mDataset.get(i);
        if(user != null) {
            //userStatsCard.infoText.setText(user.toString());
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class UserStatsCard extends RecyclerView.ViewHolder {


        public UserStatsCard(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
