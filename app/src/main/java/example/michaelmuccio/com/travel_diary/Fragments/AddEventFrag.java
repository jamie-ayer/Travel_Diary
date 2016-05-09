package example.michaelmuccio.com.travel_diary.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import example.michaelmuccio.com.travel_diary.Models.Trip;
import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 5/8/16.
 */
public class AddEventFrag extends Fragment {
    EditText titleView;
    EditText locationView;
    EditText descriptionview;
    Spinner addTripOrEvent;
    FloatingActionButton fab;
    private String user;
    private static final String TAG = "Add Event Frag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_event_layout, container, false);
        setViews(v);
        fabButtonOnClickListener();

        Log.i(TAG, "User Auth ID: " + user);

        return v;
    }
    
    private void setViews(View v){
        titleView = (EditText) v.findViewById(R.id.add_title);
        locationView = (EditText) v.findViewById(R.id.add_location);
        descriptionview = (EditText) v.findViewById(R.id.add_details);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
    }

    private void fabButtonOnClickListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase ref = new Firebase("https://glowing-torch-6078.firebaseio.com/");
                Firebase userRef = ref.child("users").child(user).child("trip");
                Trip addTrip = new Trip();
                String userInput = titleView.getText().toString();
                addTrip.setTitle(userInput);
                addTrip.setPic(R.drawable.mamas);
                Toast.makeText(getContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
//                Firebase alanRef = usersRef.child("alanisawesome");
//                Map<String, Object> nickname = new HashMap<String, Object>();
//                nickname.put("nickname", "Alan The Machine");
//                alanRef.updateChildren(nickname);

                Map<String, Object> newTrip = new HashMap<>();
                newTrip.put("title", addTrip.getTitle());
                newTrip.put("pic", addTrip.getPic());
                userRef.updateChildren(newTrip);

                ref.setValue("I'm writing data", new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            System.out.println("Data could not be saved. " + firebaseError.getMessage());
                        } else {
                            Toast.makeText(getContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });


    }

    public void setUser(String user) {
        this.user = user;
    }
}
