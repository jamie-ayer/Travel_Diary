package example.michaelmuccio.com.travel_diary.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import example.michaelmuccio.com.travel_diary.Adapters.UsersListAdapter;
import example.michaelmuccio.com.travel_diary.Models.Trip;
import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 4/29/16.
 */
public class FeedFragment extends Fragment {

    RecyclerView recyclerView;
    UsersListAdapter usersListAdapter;
    ArrayList<Trip> tripsList;
    private String user;

    public void setUser(String user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_lists_frag, container, false);
        setRetainInstance(true);
        setViews(v);

        //retrieveFirebaseData();
        usersListAdapter = new UsersListAdapter(tripsList);


        return v;
    }

    private void retrieveFirebaseData(){
        // Get a reference to our posts
        Firebase ref = new Firebase("https://glowing-torch-6078.firebaseio.com/users/" + user);
        Query queryRef = ref.orderByChild("trips");

//        queryRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
//                Trip trip = snapshot.getValue(Trip.class);
//                trip.getTitle();
//                trip.getPic();
//            }
//            // ....
//        });
    }

    public void setViews(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.user_list_view);
    }
}
