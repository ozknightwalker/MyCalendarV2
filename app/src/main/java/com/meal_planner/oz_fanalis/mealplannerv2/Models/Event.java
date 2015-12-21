package com.meal_planner.oz_fanalis.mealplannerv2.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Event implements Serializable {
    private long pk;
    private Recipe recipe;
    private Date schedule;
    private int overideServingSize;
    private String label;
    private String description;

    public Event(Recipe recipe, Date schedule, int overideServingSize, String label, String description) {
        this.pk = new Random().nextLong();
        this.recipe = recipe;
        this.schedule = schedule;
        this.overideServingSize = overideServingSize;
        this.label = label;
        this.description = description;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public int getScheduledDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.schedule);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getScheduledMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.schedule);
        return calendar.get(Calendar.MONTH);
    }

    public int getScheduledYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.schedule);
        return calendar.get(Calendar.YEAR);
    }

    public int getScheduledHour(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.schedule);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getScheduledMinute(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.schedule);
        return calendar.get(Calendar.MINUTE);
    }

    public int getOverideServingSize() {
        return overideServingSize;
    }

    public void setOverideServingSize(int overideServingSize) {
        this.overideServingSize = overideServingSize;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Event{" +
                "pk=" + pk +
                ", recipe=" + recipe +
                ", schedule=" + schedule +
                ", overideServingSize=" + overideServingSize +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
