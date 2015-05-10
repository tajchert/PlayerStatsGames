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
            //userStatsCard.infoText.setText(user.toString());
            try {
                userStatsCard.vicArcade.setText(user.getVictories().get(0));
                userStatsCard.vicHis.setText(user.getVictories().get(1));
                userStatsCard.vicReal.setText(user.getVictories().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userStatsCard.missonArcade.setText(user.getMissions().get(0));
                userStatsCard.missionHis.setText(user.getMissions().get(1));
                userStatsCard.missionReal.setText(user.getMissions().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userStatsCard.winRatioArcade.setText(user.getWinRatio().get(0));
                userStatsCard.winRatioHis.setText(user.getWinRatio().get(1));
                userStatsCard.winRatioReal.setText(user.getWinRatio().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userStatsCard.flyoutsArcade.setText(user.getFlyouts().get(0));
                userStatsCard.flyoutsHis.setText(user.getFlyouts().get(1));
                userStatsCard.flyoutsReal.setText(user.getFlyouts().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userStatsCard.deathsArcade.setText(user.getDeaths().get(0));
                userStatsCard.deathsHis.setText(user.getDeaths().get(1));
                userStatsCard.deathsReal.setText(user.getDeaths().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userStatsCard.fighterArcade.setText(user.getBattleTime().get(0));
                userStatsCard.fighterHis.setText(user.getBattleTime().get(1));
                userStatsCard.fighterReal.setText(user.getBattleTime().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userStatsCard.attackerArcade.setText(user.getAttackerTime().get(0));
                userStatsCard.attackerHis.setText(user.getAttackerTime().get(1));
                userStatsCard.attackerReal.setText(user.getAttackerTime().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userStatsCard.bomberArcade.setText(user.getBomberTime().get(0));
                userStatsCard.bomberHis.setText(user.getBomberTime().get(1));
                userStatsCard.bomberReal.setText(user.getBomberTime().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                userStatsCard.airArcade.setText(user.getAirTargets().get(0));
                userStatsCard.airHis.setText(user.getAirTargets().get(1));
                userStatsCard.airReal.setText(user.getAirTargets().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                userStatsCard.groundArcade.setText(user.getGroundTargets().get(0));
                userStatsCard.groundHis.setText(user.getGroundTargets().get(1));
                userStatsCard.groundReal.setText(user.getGroundTargets().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                userStatsCard.lionsArcade.setText(user.getLions().get(0));
                userStatsCard.lionsHis.setText(user.getLions().get(1));
                userStatsCard.lionsReal.setText(user.getLions().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                userStatsCard.xpArcade.setText(user.getXp().get(0));
                userStatsCard.xpHis.setText(user.getXp().get(1));
                userStatsCard.xpReal.setText(user.getXp().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                userStatsCard.timeArcade.setText(user.getPlayTime().get(0));
                userStatsCard.timeHis.setText(user.getPlayTime().get(1));
                userStatsCard.timeReal.setText(user.getPlayTime().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class UserStatsCard extends RecyclerView.ViewHolder {
        @InjectView(R.id.vicArcade)
        TextView vicArcade;
        @InjectView(R.id.vicHis)
        TextView vicHis;
        @InjectView(R.id.vicReal)
        TextView vicReal;

        @InjectView(R.id.missonArcade)
        TextView missonArcade;
        @InjectView(R.id.missionHis)
        TextView missionHis;
        @InjectView(R.id.missionReal)
        TextView missionReal;

        @InjectView(R.id.winRatioArcade)
        TextView winRatioArcade;
        @InjectView(R.id.winRatioHis)
        TextView winRatioHis;
        @InjectView(R.id.winRatioReal)
        TextView winRatioReal;

        @InjectView(R.id.flyoutsArcade)
        TextView flyoutsArcade;
        @InjectView(R.id.flyoutsHis)
        TextView flyoutsHis;
        @InjectView(R.id.flyoutsReal)
        TextView flyoutsReal;

        @InjectView(R.id.deathsArcade)
        TextView deathsArcade;
        @InjectView(R.id.deathsHis)
        TextView deathsHis;
        @InjectView(R.id.deathsReal)
        TextView deathsReal;

        @InjectView(R.id.fighterArcade)
        TextView fighterArcade;
        @InjectView(R.id.fighterHis)
        TextView fighterHis;
        @InjectView(R.id.fighterReal)
        TextView fighterReal;

        @InjectView(R.id.bomberArcade)
        TextView bomberArcade;
        @InjectView(R.id.bomberHis)
        TextView bomberHis;
        @InjectView(R.id.bomberReal)
        TextView bomberReal;

        @InjectView(R.id.attackerArcade)
        TextView attackerArcade;
        @InjectView(R.id.attackerHis)
        TextView attackerHis;
        @InjectView(R.id.attackerReal)
        TextView attackerReal;

        @InjectView(R.id.airArcade)
        TextView airArcade;
        @InjectView(R.id.airHis)
        TextView airHis;
        @InjectView(R.id.airReal)
        TextView airReal;

        @InjectView(R.id.groundArcade)
        TextView groundArcade;
        @InjectView(R.id.groundHis)
        TextView groundHis;
        @InjectView(R.id.groundReal)
        TextView groundReal;

        @InjectView(R.id.lionsArcade)
        TextView lionsArcade;
        @InjectView(R.id.lionsHis)
        TextView lionsHis;
        @InjectView(R.id.lionsReal)
        TextView lionsReal;

        @InjectView(R.id.xpArcade)
        TextView xpArcade;
        @InjectView(R.id.xpHis)
        TextView xpHis;
        @InjectView(R.id.xpReal)
        TextView xpReal;

        @InjectView(R.id.timeArcade)
        TextView timeArcade;
        @InjectView(R.id.timeHis)
        TextView timeHis;
        @InjectView(R.id.timeReal)
        TextView timeReal;

        public UserStatsCard(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
