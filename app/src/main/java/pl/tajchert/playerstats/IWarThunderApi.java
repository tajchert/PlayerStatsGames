package pl.tajchert.playerstats;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IWarThunderApi {
    @GET("/81005358-b529-4dc9-bfe8-c960593d0952/_query")
    void getUpWorthyCategory(@Query("input/webpage/url=http://warthunder.com/en/community/userinfo/?nick") String pageurl, @Query("_user") String user, @Query("_apikey") String apikey, Callback<ApiWarThunder> callback);

}
