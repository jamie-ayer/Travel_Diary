package example.michaelmuccio.com.travel_diary.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.firebase.client.Firebase;

import example.michaelmuccio.com.travel_diary.BuildFireBaseManager.BuildFireBase;
import example.michaelmuccio.com.travel_diary.Fragments.AddTripFrag;
import example.michaelmuccio.com.travel_diary.Fragments.ContactsFrag;
import example.michaelmuccio.com.travel_diary.Fragments.FeedFragment;
import example.michaelmuccio.com.travel_diary.R;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private String TAG = "Main Activity: ";
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
        initViews(savedInstanceState);
        //TODO setRetainInstance(true);
        setSupportActionBar(toolbar);
        bottomNavSetup();
        getLogInIntent();
        navBarListener();
        //firebaseSetup();
    }

    private void initViews(Bundle savedInstanceState){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        if (findViewById(R.id.frag_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
        }
    }

    private void getLogInIntent(){
        Intent intent = getIntent();
        String uId = intent.getStringExtra(LogInActivity.USER_ID_TAG);
        Log.i(TAG, uId);
        feedFragment.setUser(uId);
        contactsFrag.setUser(uId);
        addTripFrag.setUser(uId);
    }

    public void firebaseSetup(){
        Intent intent = getIntent();
        String uId = intent.getStringExtra("user");
        Firebase ref = new Firebase("https://glowing-torch-6078.firebaseio.com/");
        Firebase mikeRef = ref.child("users").child(uId);
        mikeRef.setValue(BuildFireBase.getTrips());

    }

    public void bottomNavSetup() {
        item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp);
        item2 = new AHBottomNavigationItem("Add Trip", R.drawable.ic_adjust_black_24dp);
        item3 = new AHBottomNavigationItem("Following", R.drawable.ic_person_black_24dp);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        item1.setColor(R.color.colorPrimary);
        item2.setColor(R.color.colorPrimary);
        item3.setColor(R.color.colorPrimary);
        bottomNavigation.setAccentColor(R.color.colorAccent);
        bottomNavigation.setInactiveColor(R.color.colorPrimaryDark);
        bottomNavigation.setColored(true);
    }

    public void navBarListener() {
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
                        toolbar.setTitle("Feed");
                        break;
                    case 1:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, addTripFrag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle("Add a Trip!");
                        break;
                    case 2:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag_container, contactsFrag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle("Following");
                        break;
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, "Searching for " + query, Toast.LENGTH_SHORT).show();
        } else {
            LogInActivity logInActivity = new LogInActivity();
            logInActivity.logout();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        handleIntent(getIntent());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.sign_out:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

