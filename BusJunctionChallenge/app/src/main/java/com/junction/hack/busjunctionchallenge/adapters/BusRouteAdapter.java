package com.junction.hack.busjunctionchallenge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.junction.hack.busjunctionchallenge.R;
import com.junction.hack.busjunctionchallenge.models.BusRoute;

import java.util.List;

public class BusRouteAdapter extends ArrayAdapter<BusRoute> {

    public BusRouteAdapter(Context context, List<BusRoute> busRoutes) {
        super(context, 0, busRoutes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BusRoute busRoute = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bus_route_list_item, parent, false);
        }
        // Lookup view for data population
        TextView routeNumber = (TextView) convertView.findViewById(R.id.routeNumber);
        TextView currentBusStopName = (TextView) convertView.findViewById(R.id.bus_current_station);
        TextView arrivalTime = (TextView) convertView.findViewById(R.id.bus_arrival_time);
        TextView timeTillArrival = (TextView) convertView.findViewById(R.id.bus_arrive_in);


        // Populate the data into the template view using the data object
        routeNumber.setText(busRoute.getBusNumber());
        currentBusStopName.setText(busRoute.getCurrentBusStopName());
        arrivalTime.setText(busRoute.getArrivalTime());
        timeTillArrival.setText(busRoute.getTimeTillArrival());
        // Return the completed view to render on screen
        return convertView;
    }
}