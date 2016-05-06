package example.michaelmuccio.com.travel_diary.Models;

/**
 * Created by michaelmuccio on 5/5/16.
 */
public class Trip {
    Event[] events;
    String title;
    int pic;

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
