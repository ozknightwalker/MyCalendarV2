package com.meal_planner.oz_fanalis.mealplannerv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.meal_planner.oz_fanalis.mealplannerv2.Models.generator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CalendarPickerController, Serializable {
    AgendaCalendarView CalendarView;
    generator eventGenerator = new generator();
    Calendar minDate = Calendar.getInstance();
    Calendar maxDate = Calendar.getInstance();
    List<CalendarEvent> eventList = new ArrayList<>();
    Locale my_locale = Locale.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.MONTH, -1);
        maxDate.add(Calendar.MONTH, 12);

        if (getIntent().hasExtra("Event") || getIntent().getExtras() != null) {
            Log.e("onCreate", "na dawat ang intent data");
        }

        eventList = eventGenerator.generateMockData(eventList, this);
        CalendarView.init(eventList, minDate, maxDate, my_locale, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDaySelected(DayItem dayItem) {

    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        if (!event.getTitle().toLowerCase().equals("no events") && event.getInstanceDay() != Calendar.getInstance()) {
            Log.e("onEventSelected", String.format("Selected event: %s", event));
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra("generator", eventGenerator);
            intent.putExtra("Event", event.getId() + "");
            startActivityForResult(intent, 1);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            // Wlay Corresponding Event Details na ma show
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if (data.getExtras() != null) {
                if (data.getBooleanExtra("is_edited", false) == true) {
                    eventGenerator = (generator) data.getSerializableExtra("generator");
                    eventList = eventGenerator.generateMockData(eventList, this);
                    recreate();
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                        this.recreate();
//                    } else {
//                        final Intent intent = data;
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        this.finish();
//                        this.overridePendingTransition(0, 0);
//                        this.startActivity(intent);
//                        this.overridePendingTransition(0, 0);
//                    }
                    CalendarView.init(eventList, minDate, maxDate, my_locale, this);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


//    private void mockList(List<CalendarEvent> eventList) {
//        Calendar startTime1 = Calendar.getInstance();
//        Calendar endTime1 = startTime1;
//        endTime1.add(Calendar.DAY_OF_YEAR, 0);
//        BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
//                ContextCompat.getColor(this, R.color.color_primary), startTime1, endTime1, true);
//        eventList.add(event1);
//
//        Calendar startTime2 = Calendar.getInstance();
//        startTime2.add(Calendar.DATE, 3);
//        Calendar endTime2 = startTime2;
//        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
//                ContextCompat.getColor(this, R.color.color_primary), startTime2, endTime2, true);
//        eventList.add(event2);
//    }
}
