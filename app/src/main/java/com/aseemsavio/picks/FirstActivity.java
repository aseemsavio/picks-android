package com.aseemsavio.picks;


import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = FirstActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "https://newsapi.org/v1/sources";
    public  String source_id ="";
    public  String source_name ="";
    private ProgressDialog pDialog;
    private List<NewsChannels> movieList = new ArrayList<NewsChannels>();
    private ListView listVieww;
    private CustomListAdapter adapter;
    FirebaseAuth auth;

  private InterstitialAd interstitialAd;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7332689293305822~4194672992");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Date date = new Date();
        String a = android.text.format.DateFormat.format("EEEE", date).toString();

        Log.d("Main", a);

        ImageView i_view = (ImageView) findViewById(R.id.iv);


        i_view.setImageResource(R.drawable.a_general);



        listVieww = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listVieww.setAdapter(adapter);



        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest movieReq1 = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response2) {
                        Log.d(TAG, response2.toString());

                        if(response2.equals(null)){
                            Toast.makeText(FirstActivity.this, "Please check your Internet connection.",
                                    Toast.LENGTH_LONG).show();
                        }
                        hidePDialog();


                        JSONArray response3 = null;
                        try {
                            response3 = response2.getJSONArray("sources");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Parsing json
                        for (int i = 0; i < response3.length(); i++) {
                            try {


                                JSONObject obj = response3.getJSONObject(i);
                                NewsChannels movie = new NewsChannels();
                                movie.setId(obj.getString("id"));
                                movie.setName(obj.getString("name"));
                                movie.setDesc(obj.getString("description"));
                                movie.setCategory(obj.getString("category"));
                                movie.setLanguage(obj.getString("language"));


                                JSONObject urlToLogo = obj.getJSONObject("urlsToLogos");
                                movie.setImg(urlToLogo.getString("small"));



                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq1);


        listVieww.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "Position:" + position);
                Log.d(TAG, "Id:" + id);

                source_id = movieList.get(position).getId();
                source_name = movieList.get(position).getName();


                Log.d(TAG, "The id recieved from the model is " + source_id + "(intent NOT Executed)");

                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(FirstActivity.this).toBundle();

                Intent i = new Intent(FirstActivity.this, SourceExpandedActivity.class);
                i.putExtra("SOURCE_ID", source_id);
                i.putExtra("TITLE", source_name);
                startActivity(i, bundle);
                Log.d(TAG, "The id recieved from the model is " + source_id + "(intent Executed)");


            }
        });




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int resId = item.getItemId();

        if(resId == R.id.power){

            auth = FirebaseAuth.getInstance();
           auth.signOut();

            startActivity(new Intent(FirstActivity.this, LogInActivity.class));
            finish();
            /*
            FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(FirstActivity.this, LogInActivity.class));
                        finish();
                    }
                }
            };

            */
            
        }
        else if(resId == R.id.favo){

            Intent i = new Intent(FirstActivity.this, Favorite.class);
            startActivity(i);
            
        }

        return true;
    }




    }



