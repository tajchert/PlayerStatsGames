package pl.tajchert.playerstats.api;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IWotApi {
    @GET("/leaderboard/get_ratings_for_search_user/?timerange=all&order_field=global_rating&page=0&return_per_page=25&group=all")
    void getWotUserId(@Query("nickname") String pageurl, @Query("rating_date") String date, Callback<ApiWotUserList> callback);

   @GET("/")
    void getUserStats( @Query("_user") String user, @Query("_apikey") String apikey, Callback<ApiWotStats> callback);
}
