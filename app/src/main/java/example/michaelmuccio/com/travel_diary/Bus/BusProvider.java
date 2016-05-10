package example.michaelmuccio.com.travel_diary.Bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by michaelmuccio on 5/9/16.
 */
public class BusProvider {
    private static final Bus bus = new Bus(ThreadEnforcer.ANY);

    public BusProvider() {
    }

    public static Bus getBusInstance() {
        return bus;
    }
}
