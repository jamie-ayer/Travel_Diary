package example.michaelmuccio.com.travel_diary.Activities;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import example.michaelmuccio.com.travel_diary.Fragments.AddTripFrag;
import example.michaelmuccio.com.travel_diary.Fragments.CameraFrag;
import example.michaelmuccio.com.travel_diary.Fragments.FeedFragment;
import example.michaelmuccio.com.travel_diary.R;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    AHBottomNavigation bottomNavigation;
    AHBottomNavigationItem item1;
    AHBottomNavigationItem item2;
    AHBottomNavigationItem item3;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavInit();
        navigationSelected();
    }

    public void bottomNavInit() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp);
        item2 = new AHBottomNavigationItem("Camera", R.drawable.ic_camera_alt_black_24dp);
        item3 = new AHBottomNavigationItem("Following", R.drawable.ic_person_black_24dp);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        item1.setColor(R.color.colorPrimary);
        bottomNavigation.setAccentColor(R.color.colorAccent);
        bottomNavigation.setInactiveColor(R.color.colorPrimaryDark);
        bottomNavigation.setColored(true);

    }

    public void navigationSelected() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                // Do something cool here..
                AddTripFrag addTripFrag = new AddTripFrag();
                CameraFrag cameraFrag = new CameraFrag();
                FeedFragment feedFragment =  new FeedFragment();
                fragmentManager =  getSupportFragmentManager();

                switch (position) {
                    case 0:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, feedFragment);
                        fragmentTransaction.commit();
                       // toolbar.setTitle("Feed");
                        break;
                    case 1:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, cameraFrag);
                        fragmentTransaction.commit();
                        //toolbar.setTitle("Add a Trip!");
                        break;
                    case 2:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, addTripFrag);
                        fragmentTransaction.commit();
                       // toolbar.setTitle("Following");
                        break;
                }
            }
        });
    }


}

