package com.meal_planner.oz_fanalis.mealplannerv2.Models;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.meal_planner.oz_fanalis.mealplannerv2.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by oz-fanalis on 12/11/15.
 */
public class generator implements Serializable {
    ArrayList<Event> events = new ArrayList<Event>();

    public generator() {
    }

    public generator(ArrayList<Event> events) {
        this.events = events;
    }

    public List generateMockData(List<CalendarEvent> eventList, Context context) {
        if (eventList.size() == 0) {
            Ingredient i1 = new Ingredient("URL", "URL", "Egg", "Itlog sa manok");
            Step s1 = new Step(1, "Prepare Ingredients");
            Step s2 = new Step(2, "Mix Ingredients");
            Step s3 = new Step(3, "Cook Ingredients");
            ArrayList<Step> steps = new ArrayList<Step>();
            steps.add(s1);
            steps.add(s2);
            steps.add(s3);

            ArrayList<Ingredient> ingredientsContainer = new ArrayList<Ingredient>();
            ingredientsContainer.add(i1);
            Recipe r1 = new Recipe(1, 2, 5.0, 12, "Egg Sandwich", "heavenly gaiden!", "URL", "URL", ingredientsContainer, steps);
            Recipe r2 = new Recipe(1, 2, 5.0, 12, "Spinach Sandwich", "lorem!", "URL", "URL", ingredientsContainer, steps);
            Calendar start1 = generateRandomHours();
            Calendar start2 = generateRandomHours();
            Calendar start4 = generateRandomHours();

            LinkedList<Recipe> list = new LinkedList<>();
            list.add(r1);
            list.add(r1);
            list.add(r2);
            Event e1 = new Event(r1, start1.getTime(), 2, r1.getName(), getDateNote(start1));
            Event e2 = new Event(r2, start2.getTime(), 3, r2.getName(), getDateNote(start2));

            for (int x = 0; x < 100; x ++) {
                Calendar dummy = generateRandomHours();
                Recipe dummyRecipe = list.get(new Random().nextInt(2));
                Event event = new Event(dummyRecipe, dummy.getTime(), new Random().nextInt(10), dummyRecipe.getName(), getDateNote(dummy));
                events.add(event);
            }
            Log.e("Generate", "Data Generated");
        } else {
            eventList = new ArrayList<>();
            Log.e("Generate", "Naay Data, eventlist done reset");
        }

        for (int x = 0; x < events.size(); x++) {
            Calendar demo = Calendar.getInstance();
            demo.setTime(events.get(x).getSchedule());
            Log.e("generate", events.get(x).getSchedule().toString());
            BaseCalendarEvent base = new BaseCalendarEvent(x,
                    ContextCompat.getColor(context, R.color.color_primary),
                    events.get(x).getLabel(),
                    null,
                    events.get(x).getDescription(),
                    demo.getTimeInMillis(),
                    demo.getTimeInMillis(),
                    0,
                    null);
            eventList.add(base);
        }
        return eventList;
    }

    public Calendar generateRandomHours(){
        Calendar start = Calendar.getInstance();
        start.add(Calendar.DAY_OF_MONTH, new Random().nextInt(60) - 20);
        start.add(Calendar.HOUR_OF_DAY, new Random().nextInt(24) - 12);
        start.add(Calendar.MINUTE, new Random().nextInt(120) - 60);
        return start;
    }
    public String getDateNote(Calendar calendar){
        String note = "PM";
        if (calendar.get(Calendar.AM_PM) == 0) {
            note = "AM";
        }
        return String.format("@ %d:%d %s",calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), note);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

}
