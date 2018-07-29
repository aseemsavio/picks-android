package com.aseemsavio.picks;

/**
 * Created by welcome on 20-06-2017.
 */

public class FavModel {

    public String news_title, description, time, author, link, id;

    public FavModel(){

    }

    public FavModel(String news_title, String description, String time, String author, String link, String id){

        this.news_title = news_title;
        this.description = description;
        this.time = time;
        this.author = author;
        this.link = link;
        this.id = id;
    }

}
