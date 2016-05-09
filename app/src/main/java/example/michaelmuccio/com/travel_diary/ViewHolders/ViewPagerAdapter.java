package example.michaelmuccio.com.travel_diary.ViewHolders;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import example.michaelmuccio.com.travel_diary.Fragments.AddTripFrag;
import example.michaelmuccio.com.travel_diary.Fragments.ContactsFrag;
import example.michaelmuccio.com.travel_diary.Fragments.FeedFragment;

/**
 * Created by michaelmuccio on 5/7/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AddTripFrag tab1 = new AddTripFrag();
                return tab1;
            case 1:
                ContactsFrag tab2 = new ContactsFrag();
                return tab2;
            case 2:
                FeedFragment tab3 = new FeedFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
