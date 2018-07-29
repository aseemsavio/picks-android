package com.aseemsavio.picks;

/**
 * Created by welcome on 21-06-2017.
 */

public class FavReadModel {

    public String author,  news_title, description, time, link, id;

    public FavReadModel(){

    }

    public FavReadModel(String author, String description, String link, String news_title, String time, String id){

        this.author = author;
        this.description = description;
        this.link = link;
        this.news_title = news_title;
        this.time = time;
        this.id = id;
    }

    public String getmAut(){
        return author;
    }

    public void setmAut(String author){
        this.author = author;
    }

    public String getmDes(){
        return description;
    }

    public void setmDes(String description){
        this.description = description;
    }

    public String getmUrl(){
        return link;
    }

    public void setmUrl(String link){
        this.link = link;
    }

    public String getmTit(){
        return news_title;
    }

    public void setmTit(String news_title){
        this.news_title = news_title;
    }

    public String getmTim(){
        return time;
    }

    public void setmTim(String time){
        this.time = time;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }


}
