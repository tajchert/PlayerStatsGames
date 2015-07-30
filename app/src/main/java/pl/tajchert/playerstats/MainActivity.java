package pl.tajchert.playerstats;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.fabric.sdk.android.Fabric;
import pl.tajchert.playerstats.api.Api;
import pl.tajchert.playerstats.api.ApiWarThunder;
import pl.tajchert.playerstats.api.ApiWarThunderBests;
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
    AutoCompleteTextView userNameEdit;

    @InjectView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        setSpinnerGameSelection();
        swipeRefresh.setEnabled(false);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeColors(Color.DKGRAY, Color.GRAY, Color.BLACK);
        getBestWarThunder();

        userNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && event.getAction() != KeyEvent.ACTION_DOWN)
                    return false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    swipeRefresh.setRefreshing(true);
                    if (userNameEdit != null) {
                        userNameEdit.dismissDropDown();
                    }
                    if (spinnerGameSelection.getSelectedItemId() == 0) {
                        searchWarThunder(userNameEdit.getText().toString());
                    } else if (spinnerGameSelection.getSelectedItemId() == 1) {
                        searchWot(userNameEdit.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getSearches());
        userNameEdit.setAdapter(adapter);
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
                    mAdapter = new WtListAdapter(results, MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    //Show no results
                    mAdapter = new SimpleListAdapter();
                    mRecyclerView.setAdapter(mAdapter);
                }
                showResults();
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

    private void getBestWarThunder() {
        Callback<ApiWarThunderBests> apiWarThunderCallback = new Callback<ApiWarThunderBests>() {
            @Override
            public void success(ApiWarThunderBests responseObject, Response response) {
                Log.d(TAG, "success :" + responseObject.results.toString());
                if (responseObject.results != null) {
                    Callback<String> callback = new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {
                            if(s != null) {
                                userNameEdit.setText(s);
                                searchWarThunder(s);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    };
                    mAdapter = new WtBestPlayersCardListAdapter((ArrayList<ApiWarThunderBests.Result>)responseObject.results, MainActivity.this, callback);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    //Show no results
                    mAdapter = new SimpleListAdapter();
                    mRecyclerView.setAdapter(mAdapter);
                }
                showResults();
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
        Api.getWarThunderBest(apiWarThunderCallback);
    }

    private void searchWot(final String username) {
        if(username == null) {
            if (swipeRefresh != null) {
                swipeRefresh.setRefreshing(false);
            }
            return;
        }
        Callback<ApiWotUserList> apiWotUserListCallback = new Callback<ApiWotUserList>() {
            @Override
            public void success(ApiWotUserList responseObject, Response response) {
                if(responseObject!= null
                        && responseObject.dataTop != null
                        && responseObject.dataTop.getResult() != null
                        && responseObject.dataTop.getResult().getUserData(username) != null
                        && responseObject.dataTop.getResult().getUserData(username).size() >= 1
                        && responseObject.dataTop.getResult().getUserData(username).get(0) != null ) {
                    String userId = responseObject.dataTop.getResult().getUserData(username).get(0);
                    Log.d(TAG, "success id: " + userId);
                    if(userId != null) {
                        searchWotStats(username, userId);
                    } else {
                        if (swipeRefresh != null) {
                            swipeRefresh.setRefreshing(false);
                        }
                        //Show no results
                        mAdapter = new SimpleListAdapter();
                        mRecyclerView.setAdapter(mAdapter);
                    }
                } else {
                    if (swipeRefresh != null) {
                        swipeRefresh.setRefreshing(false);
                    }
                    //Show no results
                    mAdapter = new SimpleListAdapter();
                    mRecyclerView.setAdapter(mAdapter);
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
                    mAdapter = new WotListAdapter((ArrayList) responseObject.results, MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    //Show no results
                    mAdapter = new SimpleListAdapter();
                    mRecyclerView.setAdapter(mAdapter);
                }
                showResults();
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

    private void saveSeachResult(String search) {
        if("null".equals(search) || "".equals(search)) {
            return;
        }
        SharedPreferences prefs = getSharedPreferences(Tools.PREFS_KEY, Context.MODE_PRIVATE);
        String[] prevSearches = prefs.getString(Tools.PREFS_SEARCHES_KEY, "").split(";;;");
        String[] newSearches = new String[prevSearches.length + 1];
        newSearches[prevSearches.length] = search;

        boolean isRepeated = false;
        for(int i = 0; i < prevSearches.length; i++) {
            if(!prevSearches[i].equals(search) && !"null".equals(prevSearches[i])) {
                newSearches[i] = prevSearches[i];
            }
            if(prevSearches[i].equals(search)) {
                isRepeated = true;
            }
        }
        if(isRepeated) {
            return;
        }
        String result = "";
        for(String str : newSearches) {
            result += str +";;;";
        }
        result = result.substring(0, result.length() - 3);
        prefs.edit().putString(Tools.PREFS_SEARCHES_KEY, result).apply();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, getSearches());
        userNameEdit.setAdapter(adapter);
    }

    private String[] getSearches() {
        SharedPreferences prefs = getSharedPreferences(Tools.PREFS_KEY, Context.MODE_PRIVATE);
        return prefs.getString(Tools.PREFS_SEARCHES_KEY, "").split(";;;");
    }

    private void setSpinnerGameSelection(){
        ArrayList<String> games = new ArrayList();
        games.add("War Thunder");
        games.add("World Of Tanks");
        ArrayAdapter<String> gamesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, games);
        gamesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGameSelection.setAdapter(gamesAdapter);
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

    private void showResults() {
        saveSeachResult(userNameEdit.getText().toString());
        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(false);
        }
        if(userNameEdit!= null) {
            userNameEdit.dismissDropDown();
        }
        linearSecondLayout.setVisibility(View.GONE);
        dummyView.setVisibility(View.GONE);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userNameEdit.getWindowToken(), 0);
    }

    public void onEvent(AutoCompleteDetect.EventKeyboardClosed eventKeyboardClosed) {
        linearSecondLayout.setVisibility(View.GONE);
        dummyView.setVisibility(View.GONE);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userNameEdit.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onRefresh() {

    }
}