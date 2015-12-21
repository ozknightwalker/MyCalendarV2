package com.meal_planner.oz_fanalis.mealplannerv2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.meal_planner.oz_fanalis.mealplannerv2.Models.Recipe;
import com.meal_planner.oz_fanalis.mealplannerv2.fragment.RecipeSummaryFragment;

public class RecipeComponentsAdapter extends FragmentPagerAdapter {

    private final Recipe recipe;
    public static final String[] RECIPE_COMPONENTS = {
            "Summary",
            "Ingredients",
            "Steps"
    };

    public RecipeComponentsAdapter(FragmentManager fm, Recipe recipe) {
        super(fm);
        this.recipe = recipe;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecipeSummaryFragment.newInstance(this.recipe);
            default:
                return RecipeSummaryFragment.newInstance(this.recipe);
        }
    }

    @Override
    public int getCount() {
        return this.RECIPE_COMPONENTS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.RECIPE_COMPONENTS[position];
    }
}

