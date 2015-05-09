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
import retrofit.Callback;
import retrofit.RestAdapter;
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
        swipeRefresh.setColorSchemeColors(Color.DKGRAY, Color.GRAY, Color.BLUE);

        userNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getWarThunderUser(userNameEdit.getText().toString());
                    return true;
                }
                return false;
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

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

    private void getWarThunderUser(String username){
        swipeRefresh.setRefreshing(true);
        //username = "http://warthunder.com/en/community/userinfo/nick=" + username;
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.API_URL_IMPORTIO)
                .build();
        IWarThunderApi articleGetter = restAdapter.create(IWarThunderApi.class);
        articleGetter.getUpWorthyCategory(username, ApiConstants.IMPORTIO_API_USER_KEY, ApiConstants.IMPORTIO_API_KEY, new Callback<ApiWarThunder>() {
            @Override
            public void success(ApiWarThunder articleContent, Response response) {
                Log.d(TAG, "success :" + articleContent.getResults().toString());
                if (articleContent.getResults() != null && articleContent.getResults().size() > 0) {
                    ApiWarThunder.Result mainResult = articleContent.getResults().get(0);
                    if (articleContent.getResults().size() >= 2) {
                        mainResult.merge(articleContent.getResults().get(1));
                        mainResult.merge(articleContent.getResults().get(2));
                    }
                    ArrayList<ApiWarThunder.Result> results = new ArrayList<ApiWarThunder.Result>();
                    results.add(mainResult);
                    mAdapter = new UserListAdapter(results);
                    mRecyclerView.setAdapter(mAdapter);
                }
                if(swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "failure :" + retrofitError.getLocalizedMessage());
                if(swipeRefresh != null) {
                    swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onRefresh() {

    }
}