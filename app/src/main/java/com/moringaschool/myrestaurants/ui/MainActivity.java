package com.moringaschool.myrestaurants.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.myrestaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   public static final String TAG = MainActivity.class.getSimpleName();
//    private Button mFindRestaurantsButton;
//    private EditText mLocationEditText;
//    private TextView mAppNameTextView;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;


    @BindView(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
//        mFindRestaurantsButton = (Button) findViewById(R.id.findRestaurantsButton);
//        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        ButterKnife.bind(this);

       // mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // mEditor = mSharedPreferences.edit();

        mFindRestaurantsButton.setOnClickListener(this);
    }


            @Override
            public void onClick(View v) {
              if(v == mFindRestaurantsButton) {
                String location = mLocationEditText.getText().toString();
//
               // Log.d(TAG, location);
                //Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
              //  intent.putExtra("location", location);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();

              }

           }
//            private void addToSharedPreferences(String location) {
//                mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//            }



    }

