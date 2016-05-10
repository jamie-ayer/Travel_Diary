package example.michaelmuccio.com.travel_diary.Interfaces;

import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import example.michaelmuccio.com.travel_diary.Models.Trip;

/**
 * Created by michaelmuccio on 5/9/16.
 */
public class Delagates {
    private Bus mbus;
    private Context context;

    public Delagates(Bus mbus, Context context) {
        this.mbus = mbus;
        this.context = context;
    }

    @Subscribe
    public void getTripId(Trip trip) {
        //trip.setRef();
    }

    @Subscribe void getTripDetails(){

    }

}
