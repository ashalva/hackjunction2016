package com.junction.hack.busjunctionchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.junction.hack.busjunctionchallenge.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    ListView routeNumbersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        routeNumbersListView = (ListView) findViewById(R.id.route_numbers);

        MainViewModel mainViewModel = new MainViewModel();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mainViewModel.getBusNumbers());


        // Assign adapter to ListView
        routeNumbersListView.setAdapter(adapter);

        // ListView Item Click Listener
        routeNumbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) routeNumbersListView.getItemAtPosition(position);

                // Show Alert, Start activity later
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });


    }
}
