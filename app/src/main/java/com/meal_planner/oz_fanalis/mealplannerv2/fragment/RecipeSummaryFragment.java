package com.meal_planner.oz_fanalis.mealplannerv2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meal_planner.oz_fanalis.mealplannerv2.Models.Recipe;
import com.meal_planner.oz_fanalis.mealplannerv2.R;

import org.json.JSONObject;

public class RecipeSummaryFragment extends Fragment {

    public static Fragment newInstance(Recipe recipe) {
        Bundle args = new Bundle();
        args.putSerializable("Recipe", recipe);

        RecipeSummaryFragment fragment = new RecipeSummaryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = this.getArguments();
        final Activity activity = this.getActivity();

        final View summaryView = inflater.inflate(R.layout.recipe_summary, container, false);
        Recipe recipe = (Recipe) args.getSerializable("Recipe");

//        ImageLoader.getInstance().loadImage(recipe.optString(Api.RECIPE_BANNER), new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                ImageView banner = (ImageView) summaryView.findViewById(R.id.recipe_banner);
//                banner.setImageBitmap(loadedImage);
//                Utils.decorateToolbarAndTabs(activity, loadedImage);
//            }
//        });

        double rating = recipe.getRating();
        if (Double.compare(rating, -1.0) == 0) {
            ((TextView) summaryView.findViewById(R.id.recipe_rating)).setText("Rating: No ratings yet");
        } else {
            int reviewCount = recipe.getReview();
            ((TextView) summaryView.findViewById(R.id.recipe_rating)).setText(String.format("Rating: %.2f stars (%d %s)", rating, reviewCount, "review"));
        }

        ((TextView) summaryView.findViewById(R.id.recipe_duration)).setText("Duration: " + ((int) recipe.getTimeToComplete()+0) + " mins");

        int defaultServingSize = recipe.getDefaultServingSize();
        ((TextView) summaryView.findViewById(R.id.recipe_serving_size)).setText("Serving size: " + defaultServingSize + " Person");

        ((TextView) summaryView.findViewById(R.id.recipe_summary)).setText(recipe.getDescription());

        return summaryView;
    }
}