package com.example.harshpandey.binge_assignment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/*
 * {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link AndroidFlavor} objects.
 * */

public class restaurantsAdapter extends ArrayAdapter<Restaurants> {

    private static final String LOG_TAG = restaurantsAdapter.class.getSimpleName();

    /**
     *
     */
    public restaurantsAdapter(Activity context, ArrayList<Restaurants> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final Restaurants currentRestaurants = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        final TextView nameTextView = (TextView) listItemView.findViewById(R.id.Restaurant_name);

        // set this text on the name TextView
        nameTextView.setText(currentRestaurants.getRestaurantsName());
        Log.d("restaurantAdapter\n\n\n","\nset the test to ="+currentRestaurants.getRestaurantsName());

        LinearLayout ll = (LinearLayout) listItemView.findViewById(R.id.whole_list);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("adapter","passing intent");
                Intent i = new Intent(getContext(),restaurant_menu.class);
                i.putExtra("root",currentRestaurants.getmRestaurantsId());
                i.putExtra("description",currentRestaurants.getRestaurantsDistance());
                i.putExtra("restaurant_name",currentRestaurants.getRestaurantsName());
                v.getContext().startActivity(i);


            }
        });

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.Distance_Restaurant);

        // Get the version number from the current restaurants object and
        // set this text on the number TextView
        numberTextView.setText(currentRestaurants.getRestaurantsDistance());
        Log.d("restaurantAdapter\n\n\n","\nset the test to ="+currentRestaurants.getRestaurantsDistance());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_restaurant);

        // Get the image resource ID from the current restaurants object and
        // set the image to iconView
        int resID = getResId(currentRestaurants.getImageResourceId(), R.drawable.class); // or other resource class
        iconView.setImageResource(resID);

        TextView timingTextView = (TextView) listItemView.findViewById(R.id.Timing_Restaurant);

        timingTextView.setText(currentRestaurants.getmTiming());

        //iconView.setImageResource(currentRestaurants.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}