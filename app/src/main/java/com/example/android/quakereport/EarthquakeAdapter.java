package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Thoma on 25.04.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> arrayList){
        super(context, 0, arrayList);

    }



    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Check if the existing view is being reused. If not, inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Get the Earthquake object located at this position in the list
        final Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        magnitudeTextView.setText(String.valueOf(formatMagnitude(currentEarthquake.getMagnitude())));

        TextView locationOffsetTextView =
                (TextView) listItemView.findViewById(R.id.location_offset_text_view);
        locationOffsetTextView.setText(getLocationOffset(currentEarthquake.getLocation()));

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text_view);
        locationTextView.setText(getPrimaryLocation(currentEarthquake.getLocation()));

        // Create a date object and store Unix time milliseconds
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Format the dateObject to an readable String
        String dateToDisplay = formatDate(dateObject);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        dateTextView.setText(dateToDisplay);

        // Format the dateObject to an readable String
        String timeToDisplay = formatTime(dateObject);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        timeTextView.setText(timeToDisplay);


        //Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD MMM yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(dateObject);
    }

    private String getLocationOffset (String location){
        String locationOffset = "";
        if (location.contains("of")) {
            String[] parts = location.split("of");
            locationOffset = locationOffset + parts[0] + "of";
        } else {
            locationOffset = locationOffset + "Near the ";
        }
        return locationOffset;
    }

    private String getPrimaryLocation (String location){
        String primaryLocation = "";
        if (location.contains("of")) {
            String[] parts = location.split("of");
            primaryLocation = parts[1];
        } else {
            primaryLocation = primaryLocation + location;
        }
        return primaryLocation;
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int color;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1: color = R.color.magnitude1;
                break;
            case 2: color = R.color.magnitude2;
                break;
            case 3: color = R.color.magnitude3;
                break;
            case 4: color = R.color.magnitude4;
                break;
            case 5: color = R.color.magnitude5;
                break;
            case 6: color = R.color.magnitude6;
                break;
            case 7: color = R.color.magnitude7;
                break;
            case 8: color = R.color.magnitude8;
                break;
            case 9: color = R.color.magnitude9;
                break;
            default: color = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), color);
    }
}


