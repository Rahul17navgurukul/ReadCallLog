package com.rahulkashyap.calldetailsfromrecentcalllog;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    // -- Constants -- //
    private final static int NUM_ITEMS = 1;
    // Fragment titles
    private final static String FIRST_POSITION_TITLE = "Recents";
    private final static String SECOND_POSITION_TITLE = "Contacts";
    private final static String THIRD_POSITION_TITLE = "Excel";

    /**
     * Constructor
     *
     * @param fragmentManager
     */
    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * Returns the amount of pages
     *
     * @return
     */
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    /**
     * Returns an item by its position
     *
     * @param position
     * @return Fragment
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RecentsFragment();
            default:
                return null;
        }
    }

    /**
     * Returns the pages title by his position
     *
     * @param position
     * @return String
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return FIRST_POSITION_TITLE;
            default:
                return null;
        }
    }
}
