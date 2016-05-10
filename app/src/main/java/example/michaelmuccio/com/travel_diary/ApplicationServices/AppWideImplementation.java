package example.michaelmuccio.com.travel_diary.ApplicationServices;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;
import com.squareup.otto.Bus;

/**
 * Created by michaelmuccio on 5/3/16.
 */
public class AppWideImplementation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        FacebookSdk.sdkInitialize(this);
    }
}
