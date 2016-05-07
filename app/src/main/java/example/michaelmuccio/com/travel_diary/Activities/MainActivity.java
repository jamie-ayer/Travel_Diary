package example.michaelmuccio.com.travel_diary.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.firebase.client.Firebase;

import example.michaelmuccio.com.travel_diary.BuildFireBaseManager.BuildFireBase;
import example.michaelmuccio.com.travel_diary.Fragments.AddTripFrag;
import example.michaelmuccio.com.travel_diary.Fragments.ContactsFrag;
import example.michaelmuccio.com.travel_diary.Fragments.FeedFragment;
import example.michaelmuccio.com.travel_diary.Models.Users;
import example.michaelmuccio.com.travel_diary.R;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    AHBottomNavigation bottomNavigation;
    AHBottomNavigationItem item1;
    AHBottomNavigationItem item2;
    AHBottomNavigationItem item3;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    AddTripFrag addTripFrag = new AddTripFrag();
    ContactsFrag contactsFrag = new ContactsFrag();
    FeedFragment feedFragment =  new FeedFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavInit();
        navigationSelected();
        //firebaseSetup();

        Intent intent = getIntent();
        String uId = intent.getStringExtra(LogInActivity.USER_ID_TAG);

        AddTripFrag articleFrag = (AddTripFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_container);
        articleFrag.setUser(uId);

        FeedFragment feedFragment = (FeedFragment)
                getSupportFragmentManager().findFragmentById(R.id.frag_container);
        feedFragment.setUser(uId);

        ContactsFrag contactsFrag = (ContactsFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_container);
        contactsFrag.setUser(uId);

    }



    public void firebaseSetup(){
        Intent intent = getIntent();
        String uId = intent.getStringExtra("user");
        Firebase ref = new Firebase("https://glowing-torch-6078.firebaseio.com/");
        Firebase mikeRef = ref.child("users").child(uId).child("trip");
        mikeRef.setValue(BuildFireBase.getTrips());

    }

    public void bottomNavInit() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp);
        item2 = new AHBottomNavigationItem("Add Trip", R.drawable.ic_adjust_black_24dp);
        item3 = new AHBottomNavigationItem("Following", R.drawable.ic_person_black_24dp);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setCurrentItem(1);

        item1.setColor(R.color.colorPrimary);
        item2.setColor(R.color.colorPrimary);
        item3.setColor(R.color.colorPrimary);
        bottomNavigation.setAccentColor(R.color.colorAccent);
        bottomNavigation.setInactiveColor(R.color.colorPrimaryDark);
        bottomNavigation.setColored(true);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_container,feedFragment);
        fragmentTransaction.commit();
    }

    public void navigationSelected() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                // Do something cool here..
                fragmentManager =  getSupportFragmentManager();

                switch (position) {
                    case 0:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, feedFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                       // toolbar.setTitle("Feed");
                        break;
                    case 1:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, addTripFrag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        //toolbar.setTitle("Add a Trip!");
                        break;
                    case 2:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, contactsFrag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                       // toolbar.setTitle("Following");
                        break;
                }
            }
        });
    }


}

