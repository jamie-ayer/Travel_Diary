package example.michaelmuccio.com.travel_diary.Models;

/**
 * Created by michaelmuccio on 5/5/16.
 */
public class Trip {
    private Event[] events;
    private String title;
    private String description;
    private String location;
    private String ref;
    private int pic;

//    public Trip(String ref) {
//        this.ref = ref;
//    }

    public String getDescription() {
        return description;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
