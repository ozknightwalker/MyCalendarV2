package com.meal_planner.oz_fanalis.mealplannerv2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.meal_planner.oz_fanalis.mealplannerv2.Models.Event;
import com.meal_planner.oz_fanalis.mealplannerv2.Models.Recipe;
import com.meal_planner.oz_fanalis.mealplannerv2.Models.generator;
import com.meal_planner.oz_fanalis.mealplannerv2.adapters.RecipeComponentsAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    generator eventGenerator = new generator();
    private View mCoordinatorLayout;
    private TabLayout mTabLayout;
    private Recipe recipe;
    private RecipeComponentsAdapter mAdapter;
    private Event event;
    int event_id;
    private Boolean is_changed = false;

    private EditText PlannedDate;
    private EditText PlannedTime;
    private Spinner mySpinner;

    private Calendar dummyCalendar  = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Meal Details");
        mCoordinatorLayout = findViewById(R.id.coordinator_layout);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            this.event_id= Integer.parseInt(getIntent().getExtras().get("Event").toString());
            this.eventGenerator = (generator) getIntent().getSerializableExtra("generator");
            this.event = this.eventGenerator.getEvents().get(event_id);
            this.recipe = event.getRecipe();
            this.recipe.setDefaultServingSize(event.getOverideServingSize());
            this.dummyCalendar.setTime(event.getSchedule());
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new RecipeComponentsAdapter(getSupportFragmentManager(),recipe);
        viewPager.setAdapter(mAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(viewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public void finish(){
        Intent i = new Intent();
        eventGenerator.getEvents().add(event);
        i.putExtra("is_edited", this.is_changed);
        i.putExtra("generator", this.eventGenerator);
        setResult(RESULT_OK, i);
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return true;
            case R.id.action_edit:
                showEditEvent();
                return true;
            case R.id.action_delete:
                deleteEvent();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteEvent(){
        new Builder(this)
            .setMessage(R.string.dialog_message)
            .setTitle("Remove From Calendar")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    eventGenerator.getEvents().remove(event);
                    Log.e("deleteEvent", "Deleted Planned Event");
                    dialog.dismiss();
                    finish();
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Log.e("deleteEvent", "Event was not deleted, change of mind");
                    dialog.dismiss();
                }
            })
            .create()
            .show();
    }

    private void showEditEvent(){
        View layout = getLayoutInflater().inflate(R.layout.dialog_edit_event, null);


        PlannedDate = (EditText) layout.findViewById(R.id.date_planned_field);
        PlannedTime = (EditText) layout.findViewById(R.id.time_planned_field);
        mySpinner = (Spinner) layout.findViewById(R.id.PaxSpinner);

        PlannedDate.setText(String.format("%d / %d / %d", event.getScheduledMonth(), event.getScheduledDay(), event.getScheduledYear()));
        PlannedTime.setText(String.format("%d:%d", event.getScheduledHour(), event.getScheduledMinute()));


        PlannedDate.setFocusableInTouchMode(false);
        PlannedDate.setInputType(InputType.TYPE_NULL);
        PlannedDate.setOnClickListener(this);
        mySpinner.setSelection(event.getOverideServingSize() - 1);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                event.setOverideServingSize(position + 1);
                is_changed = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        PlannedTime.setFocusableInTouchMode(false);
        PlannedTime.setInputType(InputType.TYPE_NULL);
        PlannedTime.setOnClickListener(this);

        new Builder(this)
                .setTitle("Edit Planned Meal")
                .setView(layout)
                .setPositiveButton("Save", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Saving Meal", Toast.LENGTH_LONG).show();
                        event.setSchedule(dummyCalendar.getTime());
                        Log.e("OnClick", event.getSchedule().toString());
                        is_changed = true;
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == PlannedDate.getId())
        {
            DatePickerDialog dialog = new DatePickerDialog(this, this, event.getScheduledYear(), event.getScheduledMonth(), event.getScheduledDay());
            dialog.show();
        } else if (v.getId() == PlannedTime.getId()) {
            TimePickerDialog dialog = new TimePickerDialog(this, this, event.getScheduledHour(), event.getScheduledMinute(), false);
            dialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        PlannedDate.setText(String.format("%d / %d /%d", monthOfYear, dayOfMonth, year));
        dummyCalendar.set(year, monthOfYear, dayOfMonth, dummyCalendar.get(Calendar.HOUR_OF_DAY), dummyCalendar.get(Calendar.MINUTE));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        PlannedTime.setText(String.format("%d:%d", hourOfDay, minute));
        dummyCalendar.set(dummyCalendar.get(Calendar.YEAR), dummyCalendar.get(Calendar.MONTH), dummyCalendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}
