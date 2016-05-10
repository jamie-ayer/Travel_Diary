package example.michaelmuccio.com.travel_diary.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 5/9/16.
 */
public class TripDetailsFrag extends Fragment {
    private RecyclerView recyclerView;
    private ImageView imageView;
    private FloatingActionButton fab;
    private String ref;
    private static final String TAG_TRIP_DETAILS = "Trip Deatils: ";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trip_details_frag, container, false);
        setRetainInstance(true);
        setViews(v);
        getRef();

        return v;
    }

    private void setViews(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.trip_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        imageView = (ImageView) v.findViewById(R.id.imageView);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
    }

    public void getRef() {
        Bundle bundle = getArguments();
        bundle.getBundle("Feed Frag");
        Log.i(TAG_TRIP_DETAILS, "" + bundle);
    }
}
