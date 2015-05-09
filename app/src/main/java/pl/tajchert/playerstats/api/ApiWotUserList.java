package pl.tajchert.playerstats.api;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class ApiWotUserList {
    private static final String TAG = "ApiWotUserList";
    @SerializedName("data_neighbors")
    public DataTop dataTop;

    public class DataTop {
        private ResultWotList result;

        public ResultWotList getResult() {
            return result;
        }

        public void setResult(ResultWotList result) {
            this.result = result;
        }
    }

    public class ResultWotList {
        private List<String> keys = new ArrayList<String>();
        private List<List<String>> values = new ArrayList<List<String>>();

        public List<String> getKeys() {
            return keys;
        }

        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        public List<List<String>> getValues() {
            return values;
        }

        public void setValues(List<List<String>> values) {
            this.values = values;
        }

        public String getPlayerName() {
            try {
                return values.get(0).get(19);
            } catch (Exception e) {
                Log.e(TAG, "getPlayerName error.");
            }
            return "";
        }

        public List<String> getUserData(String username) {
            if(username == null || values == null) {
                return null;
            }
            for(List<String> userValues: values) {
                if(userValues != null && userValues.size() > 19) {
                    if(username.equals(userValues.get(18))) {
                        return userValues;
                    }
                 }
            }
            return null;
        }

        @Override
        public String toString() {
            return "ResultWotList{" +
                    "keys=" + keys +
                    ", values=" + values +
                    '}';
        }
    }
}
