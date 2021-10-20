package com.moringaschool.myrestaurants.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.adapters.RestaurantListAdapter;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.models.YelpBusinessesSearchResponse;
import com.moringaschool.myrestaurants.network.YelpApi;
import com.moringaschool.myrestaurants.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListActivity extends AppCompatActivity {
    private static final String TAG = RestaurantListActivity.class.getSimpleName();
//    private SharedPreferences mSharedPreferences;
//    private String mRecentAddress;

    private RestaurantListAdapter mAdapter;
    public List<Business> restaurants;
//    @BindView(R.id.locationTextView) TextView mLocationTextView;
//    @BindView(R.id.listView) ListView mListView;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;


//    private TextView mLocationTextView;
//    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

//        mListView = (ListView) findViewById(R.id.listView);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

//        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants,cuisines);
//        mListView.setAdapter(adapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String restaurant = ((TextView)view).getText().toString();
//                   // Log.v("RestaurantsActivity", "In the onItemClickListener!");
//             //   String restaurant = ((TextView)view).getText().toString();
//                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_LONG).show();
//            }
//        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
       // mLocationTextView.setText("Here are all the restaurants near: " + location);
       // String location = mRecentAddress;

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");

        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, @NonNull Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    restaurants = response.body().getBusinesses();
                    mAdapter = new RestaurantListAdapter(RestaurantListActivity.this, restaurants);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestaurantListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showRestaurants();
                } else {
                    //showUnsuccessfulMessage();
                }
            }
            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
                hideProgressBar();
                //showFailureMessage();
            }
        });
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        //Log.d("Shared Pref Location", mRecentAddress);
          //String location = intent.getStringExtra("location");
       //String  location = mRecentAddress;

    }

//                    List<Business> restaurantsList = response.body().getBusinesses();
//                    String[] restaurants = new String[restaurantsList.size()];
//                    String[] categories = new String[restaurantsList.size()];
//
//                    for (int i = 0; i < restaurants.length; i++){
//                        restaurants[i] = restaurantsList.get(i).getName();
//                    }
//
//                    for (int i = 0; i < categories.length; i++) {
//                        Category category = restaurantsList.get(i).getCategories().get(0);
//                        categories[i] = category.getTitle();
//                    }
//
//                    ArrayAdapter adapter = new MyRestaurantsArrayAdapter(RestaurantsActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
//                    mListView.setAdapter(adapter);



//    private void showFailureMessage() {
//        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showUnsuccessfulMessage() {
//        mErrorTextView.setText("Something went wrong. Please try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);


    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}