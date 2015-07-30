package pl.tajchert.playerstats;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.tajchert.playerstats.api.ApiWarThunderBests;
import retrofit.Callback;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class WtBestPlayersCardListAdapter extends RecyclerView.Adapter<WtBestPlayersCardListAdapter.BestPlayersCardHolder> {
    private static final String TAG = "WtBestPlayersCardListAdapter";
    private ArrayList<ApiWarThunderBests.Result> players;
    private Context context;
    private Callback<String> callback;

    public WtBestPlayersCardListAdapter(ArrayList<ApiWarThunderBests.Result> results, Context context, Callback<String> callback) {
        this.context = context;
        for(int i = 0; i < 19; i++) {
            results.add(results.get(0));
        }
        this.players = results;
        this.callback = callback;

    }

    @Override
    public BestPlayersCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wt_best_players, parent, false);
            return new BestPlayersCardHolder(itemView);

    }

    @Override
    public void onBindViewHolder(BestPlayersCardHolder userStatsCard, int i) {
        WtBestPlayersAdapter bestAdapter = new WtBestPlayersAdapter(context, R.layout.row_wot_best, players);
        userStatsCard.listView.setAdapter(bestAdapter);
        userStatsCard.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callback.success(players.get(0).names.get(i), null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class BestPlayersCardHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.listBestPlayers)
        ListView listView;

        public BestPlayersCardHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
