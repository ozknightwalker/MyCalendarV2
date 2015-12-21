package com.meal_planner.oz_fanalis.mealplannerv2.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Recipe implements Serializable {

    private long pk;
    private int review;
    private int defaultServingSize;
    private double rating;
    private double timeToComplete;

    private String name;
    private String description;
    private String banner;
    private String icon;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;

    public Recipe(int review, int defaultServingSize, double rating, double timeToComplete, String name, String description, String banner, String icon, ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        this.pk = new Random().nextLong();
        this.review = review;
        this.defaultServingSize = defaultServingSize;
        this.rating = rating;
        this.timeToComplete = timeToComplete;
        this.name = name;
        this.description = description;
        this.banner = banner;
        this.icon = icon;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getDefaultServingSize() {
        return defaultServingSize;
    }

    public void setDefaultServingSize(int defaultServingSize) {
        this.defaultServingSize = defaultServingSize;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(double timeToComplete) {
        this.timeToComplete = timeToComplete;
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
