package example.michaelmuccio.com.travel_diary.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import example.michaelmuccio.com.travel_diary.Fragments.FeedFragment;
import example.michaelmuccio.com.travel_diary.R;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("Bottom Navigation");

        bottomNavInit();
        navigationSelected();

    }

    public void bottomNavInit(){
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Camera", R.drawable.ic_camera_alt_black_24dp);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Contacts", R.drawable.ic_person_black_24dp);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setDefaultBackgroundColor(R.color.colorPrimary);
        bottomNavigation.setAccentColor(R.color.colorAccent);
        bottomNavigation.setInactiveColor(R.color.colorPrimaryDark);

    }

    public void navigationSelected(){
        final FeedFragment fragment = new FeedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("color", Color.parseColor(colors[0]));
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame, fragment, "square")
                .commit();
        //  Enables Reveal effect
        bottomNavigation.setColored(true);

        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...

                fragment.updateColor(Color.parseColor(colors[position]));
            }
        });
    }


}

