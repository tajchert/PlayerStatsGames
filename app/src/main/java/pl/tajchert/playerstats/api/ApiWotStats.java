package pl.tajchert.playerstats.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class ApiWotStats {
    public static final String TAG = "ApiWotStats";
    @SerializedName("results")
    public List<WotUserStats> results = new ArrayList<WotUserStats>();

    public class WotUserStats {
        @SerializedName("kill_ratio")
        public String killRatio;
        
        @SerializedName("vehicles_destroyed")
        public String vehiclesDestroyed;
        
        @SerializedName("kill_details")
        public String killDetails;
        
        @SerializedName("tanks_light")
        public String tanksLight;
        @SerializedName("nation_china")        
        public String nationChina;
        @SerializedName("nation_ussr")        
        public String nationUssr;
        @SerializedName("battles_survived")        
        public String battlesSurvived;
        @SerializedName("nation_france")        
        public String nationFrance;
        @SerializedName("win_ratio")        
        public String winRatio;
        @SerializedName("nation_germany")        
        public String nationGermany;
        
        public String name;

        @SerializedName("experience_max")        
        public String experienceMax;
        
        public String experience;

        @SerializedName("nation_usa")        
        public String nationUsa;
        
        public String detected;
        @SerializedName("capture_points_defense")        
        public String capturePointsDefense;
        @SerializedName("caputre_points")        
        public String caputrePoints;
        @SerializedName("damage_caused")        
        public String damageCaused;
        @SerializedName("damage_details")        
        public String damageDetails;
        @SerializedName("damage_ratio")        
        public String damageRatio;
        @SerializedName("avg_exp")        
        public String avgExp;
        @SerializedName("tanks_spg")        
        public String tanksSpg;
        @SerializedName("avg_damage")        
        public String avgDamage;
        @SerializedName("nation_uk")        
        public String nationUk;
        @SerializedName("hit_ratio")        
        public String hitRatio;
        @SerializedName("nation_japan")        
        public String nationJapan;
        @SerializedName("tank_td")        
        public String tankTd;
        
        public String rating;
        
        public String battles;
        @SerializedName("tanks_heavy")        
        public String tanksHeavy;
        @SerializedName("tanks_med")        
        public String tanksMed;

        @Override
        public String toString() {
            return "WotUserStats{" +
                    "killRatio='" + killRatio + '\'' +
                    ", vehiclesDestroyed='" + vehiclesDestroyed + '\'' +
                    ", killDetails='" + killDetails + '\'' +
                    ", tanksLight='" + tanksLight + '\'' +
                    ", nationChina='" + nationChina + '\'' +
                    ", nationUssr='" + nationUssr + '\'' +
                    ", battlesSurvived='" + battlesSurvived + '\'' +
                    ", nationFrance='" + nationFrance + '\'' +
                    ", winRatio='" + winRatio + '\'' +
                    ", nationGermany='" + nationGermany + '\'' +
                    ", name='" + name + '\'' +
                    ", experienceMax='" + experienceMax + '\'' +
                    ", experience='" + experience + '\'' +
                    ", nationUsa='" + nationUsa + '\'' +
                    ", detected='" + detected + '\'' +
                    ", capturePointsDefense='" + capturePointsDefense + '\'' +
                    ", caputrePoints='" + caputrePoints + '\'' +
                    ", damageCaused='" + damageCaused + '\'' +
                    ", damageDetails='" + damageDetails + '\'' +
                    ", damageRatio='" + damageRatio + '\'' +
                    ", avgExp='" + avgExp + '\'' +
                    ", tanksSpg='" + tanksSpg + '\'' +
                    ", avgDamage='" + avgDamage + '\'' +
                    ", nationUk='" + nationUk + '\'' +
                    ", hitRatio='" + hitRatio + '\'' +
                    ", nationJapan='" + nationJapan + '\'' +
                    ", tankTd='" + tankTd + '\'' +
                    ", rating='" + rating + '\'' +
                    ", battles='" + battles + '\'' +
                    ", tanksHeavy='" + tanksHeavy + '\'' +
                    ", tanksMed='" + tanksMed + '\'' +
                    '}';
        }
    }
}
