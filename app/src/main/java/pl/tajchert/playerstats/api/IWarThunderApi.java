package pl.tajchert.playerstats.api;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IWarThunderApi {
    @GET("/81005358-b529-4dc9-bfe8-c960593d0952/_query")
    void getWarThunderUser(@Query("input/webpage/url=http://warthunder.com/en/community/userinfo/?nick") String pageurl, @Query("_user") String user, @Query("_apikey") String apikey, Callback<ApiWarThunder> callback);
    @GET("/d7e1a0cf-3cd4-460b-9664-2ab4cb5c352a/_query?input/webpage/url=https://warthunder.com/pl/community/leaderboard")
    void getWarThunderBestPlayers(@Query("_user") String user, @Query("_apikey") String apikey, Callback<ApiWarThunderBests> callback);

}
