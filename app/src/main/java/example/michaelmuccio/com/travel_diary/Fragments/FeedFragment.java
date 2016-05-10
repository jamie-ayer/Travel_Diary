package example.michaelmuccio.com.travel_diary.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import example.michaelmuccio.com.travel_diary.Activities.MainActivity;
import example.michaelmuccio.com.travel_diary.Bus.BusProvider;
import example.michaelmuccio.com.travel_diary.ClickListener.RecyclerTouchListener;
import example.michaelmuccio.com.travel_diary.Interfaces.ClickListener;
import example.michaelmuccio.com.travel_diary.Models.User;
import example.michaelmuccio.com.travel_diary.ViewHolders.FeedViewHolder;
import example.michaelmuccio.com.travel_diary.ViewHolders.UsersListAdapter;
import example.michaelmuccio.com.travel_diary.Models.Trip;
import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 4/29/16.
 */
public class FeedFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Trip, FeedViewHolder> mAdapter;
    //private OnTripSelectedListener mlistener;
    private Query mRef;
    private String user;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private String tripId;
    private Bus mBus = BusProvider.getBusInstance();
    public static final String TAG_FEED_FRAG = "Feed Frag";

//    public interface OnTripSelectedListener {
//        public void onTripSelected(String selectedTrip);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//           mlistener = (OnTripSelectedListener) getActivity();
//        } catch (ClassCastException e) {
//            throw new ClassCastException(getActivity().toString() + " must implement OnTripSelectedListener");
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_lists_frag, container, false);
        setRetainInstance(true);
        setViews(v);
        initFirebase();
        mAdapter = new FirebaseRecyclerAdapter<Trip, FeedViewHolder>(Trip.class, R.layout.trips_by_users, FeedViewHolder.class, mRef) {
            @Override
            public void populateViewHolder(FeedViewHolder holder, Trip trip, final int position) {
                holder.setTripTitle(trip.getTitle());
                holder.setTripSummary(trip.getDescription());
                holder.setTripIcon(R.drawable.mamas);
            }
        };
        recyclerView.setAdapter(mAdapter);
        recyclerViewListener();
        return v;
    }

    private void recyclerViewListener(){

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                tripId = mAdapter.getRef(position).getKey();
                TripDetailsFrag ldf = new TripDetailsFrag();
                Bundle args = new Bundle();
                args.putString(TAG_FEED_FRAG, tripId);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.frag_container, ldf)
                        .addToBackStack(null).commit();

                Toast.makeText(getContext(), "You clicked on: " + tripId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void initFirebase(){
        // Get a reference to our posts
        Firebase ref = new Firebase("https://glowing-torch-6078.firebaseio.com/users/" + user);
        mRef = ref.child("trip");
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
