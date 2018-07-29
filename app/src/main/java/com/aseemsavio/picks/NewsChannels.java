package com.aseemsavio.picks;

/**
 * Created by welcome on 17-06-2017.
 */

public class NewsChannels {

    private String id, name, desc, category, imgUrl, language;


    public NewsChannels() {
    }

    public NewsChannels(String id, String name, String desc, String category, String imgUrl, String language) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.imgUrl = imgUrl;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getImg() {
        return imgUrl;
    }

    public void setImg(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}