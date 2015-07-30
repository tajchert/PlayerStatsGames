package pl.tajchert.playerstats.api;

import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class Api {
    private static final String TAG = "Api";

    public static void getWarThunderUser(String username, Callback<ApiWarThunder> warThunderCallback){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_IMPORTIO)
                .build();
        IWarThunderApi thunderApi = restAdapter.create(IWarThunderApi.class);
        thunderApi.getWarThunderUser(username, ApiConstants.IMPORTIO_API_USER_KEY, ApiConstants.IMPORTIO_API_KEY, warThunderCallback);
    }

    public static void getWarThunderBest(Callback<ApiWarThunderBests> warThunderCallback){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_IMPORTIO)
                .build();
        IWarThunderApi thunderApi = restAdapter.create(IWarThunderApi.class);
        thunderApi.getWarThunderBestPlayers(ApiConstants.IMPORTIO_API_USER_KEY, ApiConstants.IMPORTIO_API_KEY, warThunderCallback);
    }

    public static void getWotUserList(String username, Callback<ApiWotUserList> wotUserListCallback){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_WOT)
                .build();
        IWotApi wotApi = restAdapter.create(IWotApi.class);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -5);
        String date = df.format("yyyy-M-d", calendar.getTime()).toString();
        if(date == null || date.length() == 0) {
            date = "2015-5-8";
        }
        wotApi.getWotUserId(username, date, wotUserListCallback);
    }

    public static void getWotUserStats(String username, String userId, Callback<ApiWotStats> wotStatsCallback){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_IMPORTIO + "/88818fe3-436f-4242-9d16-8b07992bc7b1/_query?input/webpage/url="+ ApiConstants.API_URL_WOT_STATS+userId+"-"+ username)
                .build();
        IWotApi wotApi = restAdapter.create(IWotApi.class);
        wotApi.getUserStats(ApiConstants.IMPORTIO_API_USER_KEY, ApiConstants.IMPORTIO_API_KEY_WOT, wotStatsCallback);
    }
}
