package pl.tajchert.playerstats;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        getWarThunderUser("Oddy");
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void getWarThunderUser(String username){
        //username = "http://warthunder.com/en/community/userinfo/nick=" + username;
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_IMPORTIO)
                .build();
        IWarThunderApi articleGetter = restAdapter.create(IWarThunderApi.class);
        articleGetter.getUpWorthyCategory(username, ApiConstants.IMPORTIO_API_USER_KEY, ApiConstants.IMPORTIO_API_KEY, new Callback<ApiWarThunder>() {
            @Override
            public void success(ApiWarThunder articleContent, Response response) {
                Log.d(TAG, "success :" + articleContent.getResults().toString());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "failure :" + retrofitError.getLocalizedMessage());
            }
        });
    }
}