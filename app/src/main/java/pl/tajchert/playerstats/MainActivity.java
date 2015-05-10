package pl.tajchert.playerstats;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.tajchert.playerstats.api.Api;
import pl.tajchert.playerstats.api.ApiWarThunder;
import pl.tajchert.playerstats.api.ApiWotStats;
import pl.tajchert.playerstats.api.ApiWotUserList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "MainActivity";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.secondEdit)
    LinearLayout linearSecondLayout;

    @InjectView(R.id.firstEdit)
    LinearLayout linearFirstLayout;

    @InjectView(R.id.dummyView)
    View dummyView;

    @InjectView(R.id.gameSelection)
    Spinner spinnerGameSelection;

    @InjectView(R.id.inputName)
    EditText userNameEdit;

    @InjectView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        setSpinnerGameSelection();
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeColors(Color.DKGRAY, Color.GRAY, Color.BLACK);

        userNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && event.getAction() != KeyEvent.ACTION_DOWN)
                    return false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    swipeRefresh.setRefreshing(true);
                    if(spinnerGameSelection.getSelectedItemId() == 0) {
                        searchWarThunder(userNameEdit.getText().toString());
                    } else if (spinnerGameSelection.getSelectedItemId() == 1){
                        searchWot(userNameEdit.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void searchWarThunder(String username) {
        Callback<ApiWarThunder> apiWarThunderCallback = new Callback<ApiWarThunder>() {
            @Override
            public void success(ApiWarThunder responseObject, Response response) {
                Log.d(TAG, "success :" + responseObject.getResults().toString());
                if (responseObject.getResults() != null && responseObject.getResults().size() > 0) {
                    ApiWarThunder.Result mainResult = responseObject.getResults().get(0);
                    if (responseObject.getResults().size() >= 2) {
                        mainResult.merge(responseObject.getResults().get(1));
                        mainResult.merge(responseObject.getResults().get(2));
                    }
                    ArrayList<ApiWarThunder.Result> results = new ArrayList<ApiWarThunder.Result>();
                    results.add(mainResult);
                    mAdapter = new UserListAdapter(results);
                    mRecyclerView.setAdapter(mAdapter);
                }
                if (swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                if(retrofitError.isNetworkError()) {
                    //post no Internet event
                }
                Log.d(TAG, "failure :" + retrofitError.getLocalizedMessage());
                if (swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }
        };
        Api.getWarThunderUser(username, apiWarThunderCallback);
    }

    private void searchWot(final String username) {
        Callback<ApiWotUserList> apiWotUserListCallback = new Callback<ApiWotUserList>() {
            @Override
            public void success(ApiWotUserList responseObject, Response response) {
                Log.d(TAG, "success :" + responseObject.dataTop.getResult().toString());
                String userId = responseObject.dataTop.getResult().getUserData(username).get(0);
                Log.d(TAG, "success id: " + userId);
                if(userId != null) {
                    searchWotStats(username, userId);
                } else {
                    if (swipeRefresh != null) {
                        swipeRefresh.setRefreshing(false);
                    }
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "failure :" + retrofitError.getLocalizedMessage());
                if (swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }
        };
        Api.getWotUserList(username, apiWotUserListCallback);
    }

    private void searchWotStats(String username, String userId) {
        Callback<ApiWotStats> apiWotStatsCallback = new Callback<ApiWotStats>() {
            @Override
            public void success(ApiWotStats responseObject, Response response) {
                if(responseObject != null && responseObject.results != null && responseObject.results.size() > 0 && responseObject.results.get(0) != null) {
                    Log.d(TAG, "success :" + responseObject.results.get(0).toString());

                }


                if (swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "failure :" + retrofitError.getLocalizedMessage());
                if (swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }
        };
        Api.getWotUserStats(username, userId, apiWotStatsCallback);
    }


    private void setSpinnerGameSelection(){
        ArrayList<String> games = new ArrayList();
        games.add("War Thunder");
        games.add("World Of Tanks");
        games.add("Starcraft 2");
        ArrayAdapter<String> gamesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, games);
        gamesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGameSelection.setAdapter(gamesAdapter);

        spinnerGameSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemSelected :" + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.firstEdit)
    public void searchClick() {
        linearSecondLayout.setVisibility(View.VISIBLE);
        dummyView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.inputName)
    public void editTextClick() {
        linearSecondLayout.setVisibility(View.VISIBLE);
        dummyView.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.dummyView)
    public void dummyClick() {
        linearSecondLayout.setVisibility(View.GONE);
        dummyView.setVisibility(View.GONE);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userNameEdit.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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



    @Override
    public void onRefresh() {

    }
}