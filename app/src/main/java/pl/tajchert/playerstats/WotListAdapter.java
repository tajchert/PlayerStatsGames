package pl.tajchert.playerstats;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.tajchert.playerstats.api.ApiWotStats;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class WotListAdapter extends RecyclerView.Adapter<WotListAdapter.GenericHolder> {
    private ArrayList<ApiWotStats.WotUserStats> mDataset;
    private Context context;


    public WotListAdapter(ArrayList<ApiWotStats.WotUserStats> myDataset, Context context) {
        this.context = context;
        mDataset = myDataset;
        if(mDataset.size() == 1) {
            mDataset.add(mDataset.get(0));
            mDataset.add(mDataset.get(0));
            mDataset.add(mDataset.get(0));
        }
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if(viewType == 0) {
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wot_stats_top, parent, false);
            return new CardWotTop(itemView);
        } else if(viewType == 1) {
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wot_stats_ratio, parent, false);
            return new CardWotRatio(itemView);
        } else if(viewType == 2) {
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wot_stats_nations, parent, false);
            return new CardWotNations(itemView);
        } else if(viewType == 3) {
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wot_stats_tank_types, parent, false);
            return new CardWotTankTypes(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wot_stats_top, parent, false);
            return new GenericHolder(itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(GenericHolder userStatsCard, int i) {
        ApiWotStats.WotUserStats user = mDataset.get(i);
        if(user != null) {
            if( userStatsCard instanceof CardWotTop) {
                ((CardWotTop) userStatsCard).rating.setText(user.rating);
                ((CardWotTop) userStatsCard).avgDamage.setText(user.avgDamage);
                ((CardWotTop) userStatsCard).avgExp.setText(user.avgExp);
                ((CardWotTop) userStatsCard).battles.setText(user.battles);
                ((CardWotTop) userStatsCard).winRatio.setText(user.winRatio);
            } else if( userStatsCard instanceof CardWotRatio) {
                ((CardWotRatio) userStatsCard).ratioDamage.setText(user.damageRatio);
                ((CardWotRatio) userStatsCard).ratioDamageDetails.setText(user.damageDetails);
                ((CardWotRatio) userStatsCard).ratioTanks.setText(user.killRatio);
                ((CardWotRatio) userStatsCard).ratioTanksDetails.setText(user.killDetails);
            } else if( userStatsCard instanceof CardWotNations) {
                ((CardWotNations) userStatsCard).nationBritain.setText(user.nationUk);
                ((CardWotNations) userStatsCard).nationChina.setText(user.nationChina);
                ((CardWotNations) userStatsCard).nationFrance.setText(user.nationFrance);
                ((CardWotNations) userStatsCard).nationGermany.setText(user.nationGermany);
                ((CardWotNations) userStatsCard).nationJapan.setText(user.nationJapan);
                ((CardWotNations) userStatsCard).nationUsa.setText(user.nationUsa);
                ((CardWotNations) userStatsCard).nationUssr.setText(user.nationUssr);
            } else if( userStatsCard instanceof CardWotTankTypes) {
                ((CardWotTankTypes) userStatsCard).light.setText(user.tanksLight);
                ((CardWotTankTypes) userStatsCard).heavy.setText(user.tanksHeavy);
                ((CardWotTankTypes) userStatsCard).medium.setText(user.tanksMed);
                ((CardWotTankTypes) userStatsCard).td.setText(user.tankTd);
                ((CardWotTankTypes) userStatsCard).arty.setText(user.tanksSpg);
            }
            //userStatsCard.infoText.setText(user.toString());
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class GenericHolder extends RecyclerView.ViewHolder {

        public GenericHolder(View itemView) {
            super(itemView);
        }
    }

    public class CardWotTop extends GenericHolder {

        @InjectView(R.id.wot_rating)
        TextView rating;

        @InjectView(R.id.wot_battles)
        TextView battles;

        @InjectView(R.id.wot_win_ratio)
        TextView winRatio;

        @InjectView(R.id.wot_avg_exp)
        TextView avgExp;

        @InjectView(R.id.wot_avg_damage)
        TextView avgDamage;

        public CardWotTop(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public class CardWotRatio extends GenericHolder {

        @InjectView(R.id.wot_ratio_tanks)
        TextView ratioTanks;

        @InjectView(R.id.wot_ratio_damage)
        TextView ratioDamage;

        @InjectView(R.id.wot_ratio_damge_detail)
        TextView ratioDamageDetails;

        @InjectView(R.id.wot_ratio_tanks_detail)
        TextView ratioTanksDetails;


        public CardWotRatio(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public class CardWotNations extends GenericHolder {

        @InjectView(R.id.wot_nation_britain)
        TextView nationBritain;

        @InjectView(R.id.wot_nation_china)
        TextView nationChina;

        @InjectView(R.id.wot_nation_france)
        TextView nationFrance;

        @InjectView(R.id.wot_nation_germany)
        TextView nationGermany;

        @InjectView(R.id.wot_nation_japan)
        TextView nationJapan;

        @InjectView(R.id.wot_nation_usa)
        TextView nationUsa;

        @InjectView(R.id.wot_nation_ussr)
        TextView nationUssr;

        public CardWotNations(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public class CardWotTankTypes extends GenericHolder {

        @InjectView(R.id.wot_tanks_light)
        TextView light;

        @InjectView(R.id.wot_tanks_medium)
        TextView medium;

        @InjectView(R.id.wot_tanks_heavy)
        TextView heavy;

        @InjectView(R.id.wot_tanks_arty)
        TextView arty;

        @InjectView(R.id.wot_tanks_td)
        TextView td;


        public CardWotTankTypes(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
