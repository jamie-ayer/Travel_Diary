package example.michaelmuccio.com.travel_diary.Models;

/**
 * Created by michaelmuccio on 5/5/16.
 */
public class User {
    private String facebookId;
    private Friend[] friends;
    private Trip[] trip;
    private String provider;
    private String displayName;

    public Friend[] getFriends() {
        return friends;
    }

    public void setFriends(Friend[] friends) {
        this.friends = friends;
    }

    public Trip[] getTrip() {
        return trip;
    }

    public void setTrip(Trip[] trip) {
        this.trip = trip;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
