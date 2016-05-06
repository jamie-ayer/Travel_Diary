package example.michaelmuccio.com.travel_diary.BuildFireBaseManager;

import android.content.Context;

import java.util.ArrayList;

import example.michaelmuccio.com.travel_diary.Models.Event;
import example.michaelmuccio.com.travel_diary.Models.Friend;
import example.michaelmuccio.com.travel_diary.Models.Trip;
import example.michaelmuccio.com.travel_diary.Models.Users;
import example.michaelmuccio.com.travel_diary.R;

/**
 * Created by michaelmuccio on 5/6/16.
 */
public class BuildFireBase {

    public static Users getUser(String fbId){
        Users users = new Users();
        users.setFacebookId(fbId);
        users.setDisplayName("Me");
        users.setProvider("Provider");
        users.setFriends(getFriends());
        users.setTrips(getTrips());

        return users;
    }

    private static Event[] getEventsObjects() {
        Event[] events = new Event[1];

        Event mamasFishHouse = new Event();
        mamasFishHouse.setTitle("Mama's Fish House");
        mamasFishHouse.setDetails("Went out with the family to Mama's Fish house for our \"show stopper\" " +
                "dinner. It was everything we asked for and more because the service was great and the food " +
                "was even better!");
        mamasFishHouse.setLocation("799 Poho Pl, Paia, HI 96779");
        mamasFishHouse.setPic(R.drawable.mamas);

        events[0] = mamasFishHouse;

        return events;
    }

    private static Friend[] getFriends(){
        Friend[] friends = new Friend[1];

        Friend friend = new Friend();
        friend.setDisplayName("Jamey Hollis");

        friends[0] = friend;
        return friends;
    }

    public static Trip[] getTrips(){
        Trip[] trips = new Trip[1];

        Trip trip = new Trip();
        trip.setPic(R.drawable.mamas);
        trip.setEvents(getEventsObjects());
        trip.setTitle("Maui Hawaii Trip");

        trips[0] = trip;
        return trips;
    }
}
