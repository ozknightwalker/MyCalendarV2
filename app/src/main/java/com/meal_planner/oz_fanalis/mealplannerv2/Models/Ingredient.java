package com.meal_planner.oz_fanalis.mealplannerv2.Models;

import java.io.Serializable;
import java.util.Random;

public class Ingredient implements Serializable {
    private long pk;
    private String banner;
    private String icon;
    private String name;
    private String description;

    public Ingredient(String banner, String icon, String name, String description) {
        this.pk = new Random().nextLong();
        this.banner = banner;
        this.icon = icon;
        this.name = name;
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }
}
