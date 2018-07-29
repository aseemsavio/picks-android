package com.aseemsavio.picks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by welcome on 19-06-2017.
 */

public class ViewPagerAdapter extends PagerAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsModel> newsItems;
    Context context;
    View itemView;
    public String shaPrefText;
    DatabaseReference mDatabaseReference;
    String user;





    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ViewPagerAdapter(Activity activity, List<NewsModel> newsItems){
        this.activity = activity;
        this.newsItems = newsItems;
    }

    @Override
    public int getCount() {
        return newsItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        TextView mAuthor;
        TextView mTitle;
        TextView mDesc;
        TextView mTime;
        NetworkImageView networkImageView;
        final ImageButton fav, sha, mor;
        final int pos = position;



           inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



            itemView = inflater.inflate(R.layout.pager_item, container, false);


            imageLoader = AppController.getInstance().getImageLoader();


        mAuthor = (TextView) itemView.findViewById(R.id.nwAuthor);
        mTitle = (TextView) itemView.findViewById(R.id.nwTitle);
        mDesc = (TextView) itemView.findViewById(R.id.nwDesc);
        mTime = (TextView) itemView.findViewById(R.id.nwTime);
        fav = (ImageButton) itemView.findViewById(R.id.fav);
        sha = (ImageButton) itemView.findViewById(R.id.sha);
        mor = (ImageButton)itemView.findViewById(R.id.mor);


        networkImageView = (NetworkImageView) itemView.findViewById(R.id.nwImage);

        fav.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

        fav.setVisibility(View.GONE);



                String a = newsItems.get(pos).getTitleNews().toString();
                String b = newsItems.get(pos).getDescription().toString();
                String c = newsItems.get(pos).getPublishedAt().toString();
                String d = newsItems.get(pos).getAuthor().toString();
                String e = newsItems.get(pos).getUrlNews().toString();

                String f = newsItems.get(pos).getFBUser();
                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(f);
                String id = mDatabaseReference.push().getKey();

               // user = FirebaseAuth.getInstance().getCurrentUser().toString();

                FavModel favModel = new FavModel(a, b, c, d, e, id);


                mDatabaseReference.child(id).setValue(favModel);

               /*
                Intent i = new Intent(context, Favorite.class);
                i.putExtra("tit", a);
                i.putExtra("des", b);
                i.putExtra("tim", c);
                i.putExtra("aut", d);
                i.putExtra("url", e);
                context.startActivity(i);
*/




             //   Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        });

        sha.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

             /*   View parentRow = (View) v.getParent();
                ViewPager vp = (ViewPager) parentRow.getParent();
                final int position = vp.getCurrentItem();
                */

sha.setVisibility(View.GONE);
                String a = newsItems.get(pos).getTitleNews().toString();
                String e = newsItems.get(pos).getUrlNews().toString();

                String c = a + " " + e +" - Shared from PICKS app. Get it on Google Play.".toString();

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, c);

                v.getContext().startActivity(Intent.createChooser(share, "Share Using"));
            }
        });

        mor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mor.setVisibility(View.GONE);



                String e = newsItems.get(pos).getUrlNews().toString();

                Intent intent=new Intent(v.getContext(),WebViewActivity.class);
                intent.putExtra("SOURCE_URL", e);
                v.getContext().startActivity(intent);

            }
        });



        networkImageView.setImageUrl(newsItems.get(position).getUrlToImageNews(), imageLoader);
        mAuthor.setText(newsItems.get(position).getAuthor());
        Log.d("Aseem Savio",newsItems.get(position).getAuthor());

        mTitle.setText(newsItems.get(position).getTitleNews());
        mDesc.setText(newsItems.get(position).getDescription());
        mTime.setText(newsItems.get(position).getPublishedAt());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }



}
