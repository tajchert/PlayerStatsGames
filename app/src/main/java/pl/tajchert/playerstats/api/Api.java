package pl.tajchert.playerstats.api;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Michal Tajchert on 2015-05-09.
 */
public class Api {
    private static final String TAG = "Api";

    public static void getWarThunderUser(String username, Callback<ApiWarThunder> warThunderCallback){
        //username = "http://warthunder.com/en/community/userinfo/nick=" + username;
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_IMPORTIO)
                .build();
        IWarThunderApi thunderApi = restAdapter.create(IWarThunderApi.class);
        thunderApi.getWarThunderUser(username, ApiConstants.IMPORTIO_API_USER_KEY, ApiConstants.IMPORTIO_API_KEY, warThunderCallback);
    }

    public static void getWotUserList(String username, Callback<ApiWotUserList> wotUserListCallback){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_WOT)
                .build();
        IWotApi wotApi = restAdapter.create(IWotApi.class);
        wotApi.getWotUserId(username, "2015-5-8", wotUserListCallback);
    }

    public static void getWotUserStats(String username, String userId, Callback<ApiWotStats> wotStatsCallback){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_IMPORTIO + "/_query?input/webpage/url="+ ApiConstants.API_URL_WOT_STATS+userId+"-"+ username)
                .build();
        IWotApi wotApi = restAdapter.create(IWotApi.class);
        wotApi.getUserStats(ApiConstants.IMPORTIO_API_USER_KEY, ApiConstants.IMPORTIO_API_KEY_WOT, wotStatsCallback);
    }
}
