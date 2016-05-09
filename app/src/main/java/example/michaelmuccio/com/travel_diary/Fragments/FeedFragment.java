package example.michaelmuccio.com.travel_diary.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;

import example.michaelmuccio.com.travel_diary.Models.User;
import example.michaelmuccio.com.travel_diary.ViewHolders.FeedViewHolder;
import example.michaelmuccio.com.travel_diary.ViewHolders.UsersListAdapter;
import example.michaelmuccio.com.travel_diary.Models.Trip;
import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 4/29/16.
 */
public class FeedFragment extends Fragment {

    RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Trip, FeedViewHolder> mAdapter;
    private Query mRef;
    UsersListAdapter usersListAdapter;
    ArrayList<Trip> tripsList;
    private String user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_lists_frag, container, false);
        setRetainInstance(true);
        setViews(v);
        initFirebase();
        //Firebase.setAndroidContext(getContext());
        mAdapter = new FirebaseRecyclerAdapter<Trip, FeedViewHolder>(Trip.class, R.layout.trips_by_users, FeedViewHolder.class, mRef) {
            @Override
            public void populateViewHolder(FeedViewHolder holder, Trip trip, int position) {
                holder.setTripTitle(trip.getTitle());
                holder.setTripSummary(trip.getEvents()[position].getDetails());
                holder.setTripIcon(R.drawable.mamas);
            }
        };
        recyclerView.setAdapter(mAdapter);


        return v;
    }

    private void initFirebase(){
        // Get a reference to our posts
        Firebase ref = new Firebase("https://glowing-torch-6078.firebaseio.com/users/" + user);
        mRef = ref.child("trip");

//        mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
//                Trip trip = snapshot.getValue(Trip.class);
//                trip.getTitle();
//                trip.getPic();
//            }
//            // ....
//        });
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setViews(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.user_list_view);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
