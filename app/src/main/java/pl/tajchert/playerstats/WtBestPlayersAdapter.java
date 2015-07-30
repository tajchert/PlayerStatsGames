package pl.tajchert.playerstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.tajchert.playerstats.api.ApiWarThunderBests;

/**
 * Created by Michal Tajchert on 2015-05-14.
 */
public class WtBestPlayersAdapter extends ArrayAdapter<ApiWarThunderBests.Result> {

    @InjectView(R.id.row_wot_battles)
    TextView battles;
    @InjectView(R.id.row_wot_deaths)
    TextView deaths;
    @InjectView(R.id.row_wot_exp)
    TextView exp;
    @InjectView(R.id.row_wot_lions)
    TextView lions;
    @InjectView(R.id.row_wot_name)
    TextView name;
    @InjectView(R.id.row_wot_targets_air)
    TextView targetsAir;
    @InjectView(R.id.row_wot_targets_ground)
    TextView targetsGround;
    @InjectView(R.id.row_wot_win_ratio)
    TextView winRatio;
    @InjectView(R.id.row_wot_wins)
    TextView wins;

    private ArrayList<ApiWarThunderBests.Result> objects;

    public WtBestPlayersAdapter(Context context, int textViewResourceId, ArrayList<ApiWarThunderBests.Result> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row_wot_best, null);
        }

        ApiWarThunderBests.Result result = objects.get(0);

        if (result != null && position < result.victories.size()) {
            ButterKnife.inject(this, v);
            wins.setText(result.victories.get(position));
            winRatio.setText(result.victoriesRatio.get(position).replace(" %", ""));
            targetsGround.setText(result.groundTargets.get(position));
            targetsAir.setText(result.airTargets.get(position));
            name.setText(result.names.get(position));
            deaths.setText(result.deaths.get(position));
            lions.setText(result.lions.get(position));
            exp.setText(result.exp.get(position));
            battles.setText(result.battles.get(position));
        }
        return v;

    }

}