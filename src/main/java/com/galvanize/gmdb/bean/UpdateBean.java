package com.galvanize.gmdb.bean;

import lombok.Data;
import org.springframework.validation.annotation.Validated;


@Validated
public class UpdateBean {

    private String title;
    private String rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
