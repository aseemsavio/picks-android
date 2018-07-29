package com.aseemsavio.picks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by welcome on 17-06-2017.
 */

public class CustomListAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsChannels> channelItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<NewsChannels> channelItems) {
        this.activity = activity;
        this.channelItems = channelItems;
    }

    @Override
    public int getCount() {
        return channelItems.size();
    }

    @Override
    public Object getItem(int location) {
        return channelItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.news_list_item, null);

     /*   if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);

                */
        ImageView thumbNail = (ImageView) convertView.findViewById(R.id.icon_view);
        TextView text = (TextView) convertView.findViewById(R.id.txt);
        TextView language = (TextView) convertView.findViewById(R.id.lang);

        // getting movie data for the row
        NewsChannels m = channelItems.get(position);

        // thumbnail image

        String savio = m.getCategory().toString();
        if(savio.equals("general")) {
            thumbNail.setImageResource(R.drawable.ic_gesture_white_24px);
        }
        else if(savio.equals("technology")){
            thumbNail.setImageResource(R.drawable.ic_polymer_white_24px);
        }
        else if(savio.equals("sport")){
            thumbNail.setImageResource(R.drawable.ic_rowing_white_24px);
        }
        else if(savio.equals("business")){
            thumbNail.setImageResource(R.drawable.ic_business_center_white_24px);
        }
        else if(savio.equals("entertainment")){
            thumbNail.setImageResource(R.drawable.ic_movie_white_24px);
        }
        else if(savio.equals("gaming")){
            thumbNail.setImageResource(R.drawable.ic_games_white_24px);
        }
        else if(savio.equals("music")){
            thumbNail.setImageResource(R.drawable.ic_equalizer_white_24px);
        }
        else if(savio.equals("science-and-nature")){
            thumbNail.setImageResource(R.drawable.ic_polymer_white_24px);
        }
        else if(savio.equals("politics")){
            thumbNail.setImageResource(R.drawable.ic_question_answer_white_24px);
        }

        String ass = m.getLanguage().toString();
        if(ass.equals("en")){
            language.setText("English");
        }
        else if(ass.equals("de")) {
            language.setText("German");
        }
        else if(ass.equals("fr")){
            language.setText("French");
        }

        text.setText(m.getName());

         return convertView;
    }


}
