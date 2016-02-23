package com.elementzinteractive.personalinfo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.elementzinteractive.personalinfo.fragment.DetailFragment;
import com.elementzinteractive.personalinfo.fragment.PersonListFragment;

/**
 * Created by kundan on 10/16/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    Bundle bundle;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new PersonListFragment();
                break;
            case 1:
                frag = new DetailFragment();
                break;
            case 2:
                frag = new DetailFragment();
                break;
        }
        if (bundle != null && frag != null) {
            Log.e("Log", bundle.toString());
            frag.setArguments(bundle);
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = " ";
        switch (position) {
            case 0:
                title = "List";
                break;
            case 1:
                title = "Detail";
                break;
            case 2:
                title = "About";
                break;
        }

        return title;
    }

    public void setBundle(Bundle pBundle) {
        bundle = pBundle;
        notifyDataSetChanged();
    }
}
