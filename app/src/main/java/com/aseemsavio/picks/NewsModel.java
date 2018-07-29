package com.aseemsavio.picks;

/**
 * Created by welcome on 19-06-2017.
 */

public class NewsModel {

    private String author, title, description, url, urlToImage, publishedAt, user;

    public NewsModel(){

    }

    public NewsModel(String author, String title, String description, String url, String urlToImage, String publishedAt, String user){

        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.user = user;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitleNews() {
        return title;
    }

    public void setTitleNews(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlNews() {
        return url;
    }

    public void setUrlNews(String url) {
        this.url = url;
    }
    public String getUrlToImageNews() {
        return urlToImage;
    }

    public void setUrlToImageNews(String urlToImage) {
        this.urlToImage = urlToImage;
    }
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getFBUser(){
        return user;
    }
    public void setFBUser(String user){
        this.user = user;
    }



}
