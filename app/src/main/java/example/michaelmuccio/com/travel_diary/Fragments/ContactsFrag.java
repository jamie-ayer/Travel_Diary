package example.michaelmuccio.com.travel_diary.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.michaelmuccio.com.travel_diary.Adapters.ContactsAdapter;
import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 5/4/16.
 */
public class ContactsFrag extends Fragment {
    RecyclerView recyclerView;
    ContactsAdapter contactsAdapter;
    String user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_lists_frag, container, false);
        setViews(v);
        contactsAdapter = new ContactsAdapter();

        return v;
    }

    public void setViews(View v){

        recyclerView = (RecyclerView) v.findViewById(R.id.user_list_view);
    }

    public void setUser(String user) {
        this.user = user;
    }
}
