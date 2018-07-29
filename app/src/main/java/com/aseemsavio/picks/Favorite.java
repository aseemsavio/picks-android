package com.aseemsavio.picks;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Favorite extends AppCompatActivity {

   FirebaseListAdapter mAdapter;
    String mTitle, mDesc, mAutho, mLin, mTim;
    DatabaseReference ref;
    AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7332689293305822~4194672992");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String ass = currentUser.getUid();

        ref = FirebaseDatabase.getInstance().getReference().child(ass);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot msgSnapshot: dataSnapshot.getChildren()){
                    FavReadModel msg = msgSnapshot.getValue(FavReadModel.class);
                    // Log.i("Chat", chat.getName()+": "+chat.getText());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Chat", "The read failed: " );

            }
        });


        ListView favView = (ListView) findViewById(R.id.list);




        mAdapter = new FirebaseListAdapter<FavReadModel>(this, FavReadModel.class, R.layout.fav_item, ref) {
            @Override
            protected void populateView(View v, final FavReadModel favReadModel, int position) {

               // final int pos = position;


                ((TextView)v.findViewById(R.id.fav_aut)).setText(favReadModel.getmAut());
                ((TextView)v.findViewById(R.id.fav_tit)).setText(favReadModel.getmTit());
                ((TextView)v.findViewById(R.id.fav_des)).setText(favReadModel.getmDes());
                ((TextView)v.findViewById(R.id.fav_tim)).setText(favReadModel.getmTim());

                ((ImageButton)v.findViewById(R.id.more)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String a =  favReadModel.getmUrl();

                        Intent intent=new Intent(Favorite.this,WebViewActivity.class);
                        intent.putExtra("SOURCE_URL", a);
                        startActivity(intent);
                    }
                });

                ((ImageButton)v.findViewById(R.id.delete)).setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(Favorite.this)
                                .setTitle("Delete")
                                .setMessage("Do you really want to delete this feed?")
                                .setIcon(android.R.drawable.ic_delete)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {

                                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                        String ass = currentUser.getUid();

                                        String a =  favReadModel.getId();

                                        Log.d("Aseem Savio", "Id received!!!!!!!");
                                        Log.d("Aseem Savio", a);

                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(ass).child(a);
                                        reference.setValue(null);

                                    }})
                                .setNegativeButton(android.R.string.no, null).show();

                    }
                });

            }
        };


        favView.setAdapter(mAdapter);


        }




    }

