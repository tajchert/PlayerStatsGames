package pl.tajchert.playerstats.api;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IWotApi {
    //date example 2015-5-8
    @GET("/leaderboard/get_ratings_for_search_user/?timerange=all&order_field=global_rating&page=0&return_per_page=25&group=all")
    void getWotUserId(@Query("nickname") String pageurl, @Query("rating_date") String date, Callback<ApiWotUserList> callback);

    //https://api.import.io/store/data/88818fe3-436f-4242-9d16-8b07992bc7b1/_query?input/webpage/url=http%3A%2F%2Fworldoftanks.eu%2Fcommunity%2Faccounts%2F500098565-primosz_pl%2F&_user=96da392b-9105-49e7-a2e4-d02f5d883baf&_apikey=
    @GET("/")
    void getUserStats( @Query("_user") String user, @Query("_apikey") String apikey, Callback<ApiWotStats> callback);

}
