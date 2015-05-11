package pl.tajchert.playerstats;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.tajchert.playerstats.api.ApiWarThunder;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class WtListAdapter extends RecyclerView.Adapter<WtListAdapter.GenericHolder> {
    private ArrayList<ApiWarThunder.Result> mDataset;
    private Context context;

    public WtListAdapter(ArrayList<ApiWarThunder.Result> myDataset, Context context) {
        this.context = context;
        mDataset = myDataset;
        if(mDataset.size() == 1) {
            mDataset.add(mDataset.get(0));
            mDataset.add(mDataset.get(0));
        }
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if(viewType == 0) {
            //profile card
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wt_stats_profile, parent, false);
            return new WtProfileCard(itemView);
        } else if (viewType == 1) {
            //planes
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wt_stats_planes, parent, false);
            return new WtPlanesCard(itemView);
        } else {
            //detail
            itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_wt_stats_detail, parent, false);
            return new WtUserDetailStats(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(GenericHolder holder, int i) {
        ApiWarThunder.Result user = mDataset.get(i);
        if(holder.getItemViewType() == 0) {
            WtProfileCard wtProfileCard = (WtProfileCard) holder;
            if(user.getName() != null) {
                wtProfileCard.userName.setText(user.getName());
            }
            if(user.getClan() != null) {
                wtProfileCard.clan.setText(user.getClan());
            }
            if(user.getRegistration() != null) {
                wtProfileCard.registerDate.setText(user.getRegistration() + "");
            }
            if(user.getIcon() != null && user.getIcon().length() > 0) {
                Glide.with(context).load(user.getIcon()).fitCenter().placeholder(R.drawable.ic_pilot).into(wtProfileCard.profilePic);
            }
        } else if(holder.getItemViewType() == 1) {
            WtPlanesCard wtPlanesCard = (WtPlanesCard) holder;
            try {
                wtPlanesCard.usaPlanes.setText(user.getUsa().get(0));
                wtPlanesCard.usaPlanesElite.setText(user.getUsa().get(1));
                wtPlanesCard.usaMedals.setText(user.getUsa().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                wtPlanesCard.ussrPlanes.setText(user.getUssr().get(0));
                wtPlanesCard.ussrPlanesElite.setText(user.getUssr().get(1));
                wtPlanesCard.ussrMedals.setText(user.getUssr().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                wtPlanesCard.germanyPlanes.setText(user.getGermany().get(0));
                wtPlanesCard.germanyPlanesElite.setText(user.getGermany().get(1));
                wtPlanesCard.germanyMedals.setText(user.getGermany().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                wtPlanesCard.japanPlanes.setText(user.getJapan().get(0));
                wtPlanesCard.japanPlanesElite.setText(user.getJapan().get(1));
                wtPlanesCard.japanMedals.setText(user.getJapan().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                wtPlanesCard.britainPlanes.setText(user.getBritain().get(0));
                wtPlanesCard.britainPlanesElite.setText(user.getBritain().get(1));
                wtPlanesCard.britainMedals.setText(user.getBritain().get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(holder.getItemViewType() == 2 ) {
            if (user != null) {

                WtUserDetailStats wtUserDetailStats = (WtUserDetailStats) holder;
                //wtUserDetailStats.infoText.setText(user.toString());
                try {
                    wtUserDetailStats.vicArcade.setText(user.getVictories().get(0));
                    wtUserDetailStats.vicHis.setText(user.getVictories().get(1));
                    wtUserDetailStats.vicReal.setText(user.getVictories().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    wtUserDetailStats.missonArcade.setText(user.getMissions().get(0));
                    wtUserDetailStats.missionHis.setText(user.getMissions().get(1));
                    wtUserDetailStats.missionReal.setText(user.getMissions().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    wtUserDetailStats.winRatioArcade.setText(user.getWinRatio().get(0));
                    wtUserDetailStats.winRatioHis.setText(user.getWinRatio().get(1));
                    wtUserDetailStats.winRatioReal.setText(user.getWinRatio().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    wtUserDetailStats.flyoutsArcade.setText(user.getFlyouts().get(0));
                    wtUserDetailStats.flyoutsHis.setText(user.getFlyouts().get(1));
                    wtUserDetailStats.flyoutsReal.setText(user.getFlyouts().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    wtUserDetailStats.deathsArcade.setText(user.getDeaths().get(0));
                    wtUserDetailStats.deathsHis.setText(user.getDeaths().get(1));
                    wtUserDetailStats.deathsReal.setText(user.getDeaths().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    wtUserDetailStats.fighterArcade.setText(user.getBattleTime().get(0));
                    wtUserDetailStats.fighterHis.setText(user.getBattleTime().get(1));
                    wtUserDetailStats.fighterReal.setText(user.getBattleTime().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    wtUserDetailStats.attackerArcade.setText(user.getAttackerTime().get(0));
                    wtUserDetailStats.attackerHis.setText(user.getAttackerTime().get(1));
                    wtUserDetailStats.attackerReal.setText(user.getAttackerTime().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    wtUserDetailStats.bomberArcade.setText(user.getBomberTime().get(0));
                    wtUserDetailStats.bomberHis.setText(user.getBomberTime().get(1));
                    wtUserDetailStats.bomberReal.setText(user.getBomberTime().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    wtUserDetailStats.airArcade.setText(user.getAirTargets().get(0));
                    wtUserDetailStats.airHis.setText(user.getAirTargets().get(1));
                    wtUserDetailStats.airReal.setText(user.getAirTargets().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    wtUserDetailStats.groundArcade.setText(user.getGroundTargets().get(0));
                    wtUserDetailStats.groundHis.setText(user.getGroundTargets().get(1));
                    wtUserDetailStats.groundReal.setText(user.getGroundTargets().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    wtUserDetailStats.lionsArcade.setText(user.getLions().get(0));
                    wtUserDetailStats.lionsHis.setText(user.getLions().get(1));
                    wtUserDetailStats.lionsReal.setText(user.getLions().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    wtUserDetailStats.xpArcade.setText(user.getXp().get(0));
                    wtUserDetailStats.xpHis.setText(user.getXp().get(1));
                    wtUserDetailStats.xpReal.setText(user.getXp().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    wtUserDetailStats.timeArcade.setText(user.getPlayTime().get(0));
                    wtUserDetailStats.timeHis.setText(user.getPlayTime().get(1));
                    wtUserDetailStats.timeReal.setText(user.getPlayTime().get(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class WtUserDetailStats extends GenericHolder {
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

        public WtUserDetailStats(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public class GenericHolder extends RecyclerView.ViewHolder {

        public GenericHolder(View itemView) {
            super(itemView);
        }
    }

    public class WtProfileCard extends GenericHolder {

        @InjectView(R.id.profilePic)
        ImageView profilePic;

        @InjectView(R.id.wt_userName)
        TextView userName;

        @InjectView(R.id.wt_clan)
        TextView clan;

        @InjectView(R.id.wt_register)
        TextView registerDate;

        public WtProfileCard(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public class WtPlanesCard extends GenericHolder {

        @InjectView(R.id.usa_planes)
        TextView usaPlanes;
        @InjectView(R.id.usa_planes_elite)
        TextView usaPlanesElite;
        @InjectView(R.id.usa_medals)
        TextView usaMedals;

        @InjectView(R.id.ussr_planes)
        TextView ussrPlanes;
        @InjectView(R.id.ussr_planes_elite)
        TextView ussrPlanesElite;
        @InjectView(R.id.ussr_medals)
        TextView ussrMedals;

        @InjectView(R.id.britain_planes)
        TextView britainPlanes;
        @InjectView(R.id.britain_planes_elite)
        TextView britainPlanesElite;
        @InjectView(R.id.britain_medals)
        TextView britainMedals;

        @InjectView(R.id.germany_planes)
        TextView germanyPlanes;
        @InjectView(R.id.germany_planes_elite)
        TextView germanyPlanesElite;
        @InjectView(R.id.germany_medals)
        TextView germanyMedals;

        @InjectView(R.id.japan_planes)
        TextView japanPlanes;
        @InjectView(R.id.japan_planes_elite)
        TextView japanPlanesElite;
        @InjectView(R.id.japan_medals)
        TextView japanMedals;


        public WtPlanesCard(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
