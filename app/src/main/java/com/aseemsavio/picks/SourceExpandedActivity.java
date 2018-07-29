package com.aseemsavio.picks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SourceExpandedActivity extends AppCompatActivity {


    private String url_part1 = "https://newsapi.org/v1/articles?source=";
    private String url_part2 = "";
    private String url_part3 = "&apiKey=840d7d0ba3c44eceb5ee01f98f34021e";
    private String title_name = "";
    private String url = "";
    public String newsUrl = "";
    private ProgressDialog pDialog;
    private List<NewsModel> newsItems = new ArrayList<NewsModel>();

    ViewPager viewPager;
    PagerAdapter adapter;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_expanded);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7332689293305822~4194672992");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Intent ir = getIntent();
        Bundle b = ir.getExtras();
        if (!b.equals("") ) {
            url_part2 = (String) b.get("SOURCE_ID");
            url = url_part1 + url_part2 + url_part3;
            title_name = (String) b.get("TITLE");
            setTitle(title_name);
        }

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest movieReq = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Source Expanded", response.toString());


                        hidePDialog();


                        JSONArray response1 = null;
                        try {
                            response1 = response.getJSONArray("articles");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Parsing json
                        for (int i = 0; i < response1.length(); i++) {
                            try {


                                JSONObject obj = response1.getJSONObject(i);
                                NewsModel nm = new NewsModel();
                                nm.setTitleNews(obj.getString("title"));
                                nm.setUrlToImageNews(obj.getString("urlToImage"));
                                String z = obj.getString("author");
                                if ((z.equals(""))||(z.equals(null))||(z.equals("null"))) {
                                    nm.setAuthor("Anonymous Publisher");
                                }
                                else if((z.substring(0,4).equals("http"))){
                                    nm.setAuthor("The Internet");
                                }
                                else {
                                    nm.setAuthor(z);
                                }

                                String zzz = obj.getString("publishedAt");
                                if ((zzz.equals(""))||(zzz.equals(null))||(zzz.equals("null"))) {
                                    nm.setPublishedAt("Recently");
                                } else {
                                    String aseem = zzz.substring(0, 10);
                                    String savio = zzz.substring(11, 19);
                                    String aseemsavio = aseem + "    " + savio;
                                    nm.setPublishedAt(aseemsavio);
                                }
                                String aa = obj.getString("description");
                                if ((aa.equals(""))||(aa.equals(null))||(aa.equals("null"))) {
                                    nm.setDescription("More to read on the website. Click on the page icon.");
                                } else {
                                    nm.setDescription(aa);
                                }

                                nm.setUrlNews(obj.getString("url"));

                              //  String savio = FirebaseAuth.getInstance().getCurrentUser().toString();

                                //SharedPreferences prefs = getSharedPreferences("MY_PREF", MODE_PRIVATE);
                                //String rest = prefs.getString("text", null);

                                   // String savio = prefs.getString("email", "aseemsavio").toString();

                                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                String savio = firebaseUser.getUid();
                                nm.setFBUser(savio);




                                newsItems.add(nm);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Source Expanded", "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

        viewPager = (ViewPager) findViewById(R.id.pager);

        adapter = new ViewPagerAdapter(SourceExpandedActivity.this, newsItems);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);




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

    public void intentMethodNews(String urlAseem) {

        Intent i = new Intent(SourceExpandedActivity.this, WebViewActivity.class);
        i.putExtra("URL", urlAseem);
        startActivity(i);

    }

}
