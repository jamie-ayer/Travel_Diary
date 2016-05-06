package example.michaelmuccio.com.travel_diary.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.michaelmuccio.com.travel_diary.Adapters.UsersListAdapter;
import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 4/29/16.
 */
public class FeedFragment extends Fragment {

    RecyclerView recyclerView;
    UsersListAdapter usersListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_lists_frag, container, false);
        setRetainInstance(true);
        setViews(v);

        usersListAdapter = new UsersListAdapter();


        return v;
    }

    public void setViews(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.user_list_view);
    }
}
