package pl.tajchert.playerstats.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiWarThunderBests {

    public List<Result> results = new ArrayList<Result>();
    public String pageUrl;

    public class Result {
        @SerializedName("name/_text")
        public List<String> names = new ArrayList<String>();
        @SerializedName("name")
        public List<String> urls = new ArrayList<String>();
        @SerializedName("victories_ratio")
        public List<String> victoriesRatio = new ArrayList<String>();
        public List<String> victories = new ArrayList<String>();
        @SerializedName("ground_targets")
        public List<String> groundTargets = new ArrayList<String>();
        @SerializedName("air_targets")
        public List<String> airTargets = new ArrayList<String>();
        public List<String> lions = new ArrayList<String>();
        public List<String> deaths = new ArrayList<String>();
        public List<String> flyouts = new ArrayList<String>();
        public List<String> exp = new ArrayList<String>();
        public List<String> battles = new ArrayList<String>();
    }
}