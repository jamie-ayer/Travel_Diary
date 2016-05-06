package example.michaelmuccio.com.travel_diary.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 4/30/16.
 */
public class AddTripFrag extends Fragment {
    EditText titleView;
    EditText locationView;
    EditText descriptionview;
    Spinner addTripOrEvent;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_event_layout, container, false);
        setViews(v);
        spinnerInit(v);
        fabButtunOnClickListener();

        return v;
    }

    private void fabButtunOnClickListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void spinnerInit(View v){
        Spinner spinner = (Spinner) v.findViewById(R.id.trip_or_event_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.trip_or_event_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void setViews(View v){
        titleView = (EditText) v.findViewById(R.id.add_title);
        locationView = (EditText) v.findViewById(R.id.add_location);
        descriptionview = (EditText) v.findViewById(R.id.add_details);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
    }
}
