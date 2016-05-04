package example.michaelmuccio.com.travel_diary;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by michaelmuccio on 5/3/16.
 */
public class TravelDiaryFireBaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
