package pl.tajchert.playerstats.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiWarThunder {

    private Integer offset;
    private List<Result> results = new ArrayList<Result>();
    private List<String> cookies = new ArrayList<String>();
    private String connectorVersionGuid;
    private String connectorGuid;
    private String pageUrl;

    /**
     *
     * @return
     * The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The cookies
     */
    public List<String> getCookies() {
        return cookies;
    }

    /**
     *
     * @param cookies
     * The cookies
     */
    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }

    /**
     *
     * @return
     * The connectorVersionGuid
     */
    public String getConnectorVersionGuid() {
        return connectorVersionGuid;
    }

    /**
     *
     * @param connectorVersionGuid
     * The connectorVersionGuid
     */
    public void setConnectorVersionGuid(String connectorVersionGuid) {
        this.connectorVersionGuid = connectorVersionGuid;
    }

    /**
     *
     * @return
     * The connectorGuid
     */
    public String getConnectorGuid() {
        return connectorGuid;
    }

    /**
     *
     * @param connectorGuid
     * The connectorGuid
     */
    public void setConnectorGuid(String connectorGuid) {
        this.connectorGuid = connectorGuid;
    }

    /**
     *
     * @return
     * The pageUrl
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     *
     * @param pageUrl
     * The pageUrl
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }


    public class Result {
        private String clan;
        private String name;
        private String registration;
        private List<String> usa = new ArrayList<String>();
        private List<String> britain = new ArrayList<String>();
        private List<String> japan = new ArrayList<String>();
        private List<String> germany = new ArrayList<String>();
        private List<String> ussr = new ArrayList<String>();
        @SerializedName("ground_targets")
        private List<String> groundTargets = new ArrayList<String>();
        @SerializedName("win_ratio")
        private List<String> winRatio = new ArrayList<String>();
        @SerializedName("battle_time")
        private List<String> battleTime = new ArrayList<String>();
        private List<String> victories = new ArrayList<String>();
        @SerializedName("attacker_time")
        private List<String> attackerTime = new ArrayList<String>();
        @SerializedName("play_time")
        private List<String> playTime = new ArrayList<String>();
        @SerializedName("air_targets")
        private List<String> airTargets = new ArrayList<String>();
        @SerializedName("bomber_time")
        private List<String> bomberTime = new ArrayList<String>();
        private List<String> lions = new ArrayList<String>();
        private List<String> missions = new ArrayList<String>();
        private List<String> xp = new ArrayList<String>();
        private List<String> flyouts = new ArrayList<String>();
        private List<String> deaths = new ArrayList<String>();

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The registration
         */
        public String getRegistration() {
            return registration;
        }

        /**
         *
         * @param registration
         * The registration
         */
        public void setRegistration(String registration) {
            this.registration = registration;
        }

        /**
         *
         * @return
         * The usa
         */
        public List<String> getUsa() {
            return usa;
        }

        /**
         *
         * @param usa
         * The usa
         */
        public void setUsa(List<String> usa) {
            this.usa = usa;
        }

        /**
         *
         * @return
         * The britain
         */
        public List<String> getBritain() {
            return britain;
        }

        /**
         *
         * @param britain
         * The britain
         */
        public void setBritain(List<String> britain) {
            this.britain = britain;
        }

        /**
         *
         * @return
         * The japan
         */
        public List<String> getJapan() {
            return japan;
        }

        /**
         *
         * @param japan
         * The japan
         */
        public void setJapan(List<String> japan) {
            this.japan = japan;
        }

        /**
         *
         * @return
         * The germany
         */
        public List<String> getGermany() {
            return germany;
        }

        /**
         *
         * @param germany
         * The germany
         */
        public void setGermany(List<String> germany) {
            this.germany = germany;
        }

        /**
         *
         * @return
         * The ussr
         */
        public List<String> getUssr() {
            return ussr;
        }

        /**
         *
         * @param ussr
         * The ussr
         */
        public void setUssr(List<String> ussr) {
            this.ussr = ussr;
        }

        /**
         *
         * @return
         * The groundTargets
         */
        public List<String> getGroundTargets() {
            return groundTargets;
        }

        /**
         *
         * @param groundTargets
         * The ground_targets
         */
        public void setGroundTargets(List<String> groundTargets) {
            this.groundTargets = groundTargets;
        }

        /**
         *
         * @return
         * The winRatio
         */
        public List<String> getWinRatio() {
            return winRatio;
        }

        /**
         *
         * @param winRatio
         * The win_ratio
         */
        public void setWinRatio(List<String> winRatio) {
            this.winRatio = winRatio;
        }

        /**
         *
         * @return
         * The battleTime
         */
        public List<String> getBattleTime() {
            return battleTime;
        }

        /**
         *
         * @param battleTime
         * The battle_time
         */
        public void setBattleTime(List<String> battleTime) {
            this.battleTime = battleTime;
        }

        /**
         *
         * @return
         * The victories
         */
        public List<String> getVictories() {
            return victories;
        }

        /**
         *
         * @param victories
         * The victories
         */
        public void setVictories(List<String> victories) {
            this.victories = victories;
        }

        /**
         *
         * @return
         * The attackerTime
         */
        public List<String> getAttackerTime() {
            return attackerTime;
        }

        /**
         *
         * @param attackerTime
         * The attacker_time
         */
        public void setAttackerTime(List<String> attackerTime) {
            this.attackerTime = attackerTime;
        }

        /**
         *
         * @return
         * The playTime
         */
        public List<String> getPlayTime() {
            return playTime;
        }

        /**
         *
         * @param playTime
         * The play_time
         */
        public void setPlayTime(List<String> playTime) {
            this.playTime = playTime;
        }

        /**
         *
         * @return
         * The airTargets
         */
        public List<String> getAirTargets() {
            return airTargets;
        }

        /**
         *
         * @param airTargets
         * The air_targets
         */
        public void setAirTargets(List<String> airTargets) {
            this.airTargets = airTargets;
        }

        /**
         *
         * @return
         * The bomberTime
         */
        public List<String> getBomberTime() {
            return bomberTime;
        }

        /**
         *
         * @param bomberTime
         * The bomber_time
         */
        public void setBomberTime(List<String> bomberTime) {
            this.bomberTime = bomberTime;
        }

        /**
         *
         * @return
         * The lions
         */
        public List<String> getLions() {
            return lions;
        }

        /**
         *
         * @param lions
         * The lions
         */
        public void setLions(List<String> lions) {
            this.lions = lions;
        }

        /**
         *
         * @return
         * The missions
         */
        public List<String> getMissions() {
            return missions;
        }

        /**
         *
         * @param missions
         * The missions
         */
        public void setMissions(List<String> missions) {
            this.missions = missions;
        }

        /**
         *
         * @return
         * The xp
         */
        public List<String> getXp() {
            return xp;
        }

        /**
         *
         * @param xp
         * The xp
         */
        public void setXp(List<String> xp) {
            this.xp = xp;
        }

        /**
         *
         * @return
         * The flyouts
         */
        public List<String> getFlyouts() {
            return flyouts;
        }

        /**
         *
         * @param flyouts
         * The flyouts
         */
        public void setFlyouts(List<String> flyouts) {
            this.flyouts = flyouts;
        }

        /**
         *
         * @return
         * The deaths
         */
        public List<String> getDeaths() {
            return deaths;
        }

        /**
         *
         * @param deaths
         * The deaths
         */
        public void setDeaths(List<String> deaths) {
            this.deaths = deaths;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "clan='" + clan + '\'' +
                    ", name='" + name + '\'' +
                    ", registration='" + registration + '\'' +
                    ", usa=" + usa +
                    ", britain=" + britain +
                    ", japan=" + japan +
                    ", germany=" + germany +
                    ", ussr=" + ussr +
                    ", groundTargets='" + groundTargets + '\'' +
                    ", winRatio='" + winRatio + '\'' +
                    ", battleTime='" + battleTime + '\'' +
                    ", victories=" + victories +
                    ", attackerTime='" + attackerTime + '\'' +
                    ", playTime='" + playTime + '\'' +
                    ", airTargets=" + airTargets +
                    ", bomberTime='" + bomberTime + '\'' +
                    ", lions='" + lions + '\'' +
                    ", missions=" + missions +
                    ", xp='" + xp + '\'' +
                    ", flyouts=" + flyouts +
                    ", deaths='" + deaths + '\'' +
                    '}';
        }

        public Result merge(Result result) {
            if(this.clan == null) {
                this.clan = result.clan;
            }
            if(this.name == null) {
                this.name = result.name;
            }
            if(this.registration == null) {
                this.registration = result.registration;
            }
            if(this.usa == null) {
                this.usa = result.usa;
            }
            if(this.britain == null) {
                this.britain = result.britain;
            }
            if(this.japan == null) {
                this.japan = result.japan;
            }
            if(this.germany == null) {
                this.germany = result.germany;
            }
            if(this.ussr == null) {
                this.ussr = result.ussr;
            }
            if(this.groundTargets == null) {
                this.groundTargets = result.groundTargets;
            }
            if(this.winRatio == null) {
                this.winRatio = result.winRatio;
            }
            if(this.battleTime == null) {
                this.battleTime = result.battleTime;
            }
            if(this.victories == null) {
                this.victories = result.victories;
            }
            if(this.attackerTime == null) {
                this.attackerTime = result.attackerTime;
            }
            if(this.playTime == null) {
                this.playTime = result.playTime;
            }
            if(this.airTargets == null) {
                this.airTargets = result.airTargets;
            }
            if(this.bomberTime == null) {
                this.bomberTime = result.bomberTime;
            }
            if(this.lions == null) {
                this.lions = result.lions;
            }
            if(this.missions == null) {
                this.missions = result.missions;
            }
            if(this.xp == null) {
                this.xp = result.xp;
            }
            if(this.flyouts == null) {
                this.flyouts = result.flyouts;
            }
            if(this.deaths == null) {
                this.deaths = result.deaths;
            }
            return this;
        }
    }
}