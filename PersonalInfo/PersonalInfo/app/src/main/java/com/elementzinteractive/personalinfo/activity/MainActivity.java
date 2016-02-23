package com.elementzinteractive.personalinfo.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.elementzinteractive.personalinfo.R;
import com.elementzinteractive.personalinfo.fragment.DetailFragment;
import com.elementzinteractive.personalinfo.fragment.PersonListFragment;
import com.elementzinteractive.personalinfo.fragment.StaggeredFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TabLayout tabLayout;
    private int tabindex = 0;
    private Bundle bundle;
    private ProgressBar progressBar;
    PersonListFragment personListFragment;
    DetailFragment detailFragment;
    StaggeredFragment staggeredFragment;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setElevation(0);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.toolbar_progress_bar);
        progressBar.setVisibility(View.GONE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("List"));
        tabLayout.addTab(tabLayout.newTab().setText("Detail"));
        tabLayout.addTab(tabLayout.newTab().setText("Grid View"));

        if (savedInstanceState != null) {
            tabindex = savedInstanceState.getInt("tabindex");
        } else {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            try {
                personListFragment = new PersonListFragment();
                detailFragment = new DetailFragment();
                staggeredFragment = new StaggeredFragment();
                if (tabindex == 0)
                    fragmentTransaction.add(R.id.fragment_container, personListFragment, "PERSONLIST");
                else if (tabindex == 1) {
                    fragmentTransaction.add(R.id.fragment_container, detailFragment, "DETAILS");
                } else if (tabindex == 2) {
                    fragmentTransaction.add(R.id.fragment_container, staggeredFragment, "STAGGERED");
                }
                fragmentTransaction.commit();
            } catch (Exception ex) {
                if (ex.getMessage() != null)
                    Log.e("Error", ex.getMessage());
            }
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (tab.getPosition() == 0) {
                    PersonListFragment personListFragment = new PersonListFragment();
                    fragmentTransaction.replace(R.id.fragment_container, personListFragment, "HELLO");
                } else if (tab.getPosition() == 1) {
                    DetailFragment detailFragment = new DetailFragment();
                    if (bundle != null)
                        detailFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_container, detailFragment, "HELLO");
                } else if (tab.getPosition() == 2) {
                    staggeredFragment = new StaggeredFragment();
                    fragmentTransaction.replace(R.id.fragment_container, staggeredFragment, "HELLO");
                }

                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //region OLD CODE
        /*pager= (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout=  (TabLayout) findViewById(R.id.tab_layout);
        //region Tab Layout
        final Bundle page = new Bundle();
        page.putString("url","dsd");
        FragmentManager manager=getSupportFragmentManager();
        //fragments = new Vector<Fragment>();
        //fragments.add(Fragment.instantiate(this, PersonListFragment.class.getName(), page));
        //fragments.add(Fragment.instantiate(this,DetailFragment.class.getName(),page));
        adapter=new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

                try {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                    int id = viewPager.getCurrentItem();
                    Fragment frag = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + id);
                    frag.setArguments(bundle);
                    frag.onResume();
                }catch(Exception ex)
                {
                    Log.e("TEST",ex.getMessage());
                }
                pager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(Tab tab) {

            }
            @Override
            public void onTabReselected(Tab tab) {
            }
        });*/
        Log.w("Activity", "OnCreate");
        //endregion

        getSupportActionBar().setIcon(R.mipmap.ic_user);
        toolbar.setNavigationIcon(null);
    }

    public void setCurrentPagerItem(int index) {
        tabindex = index;
        TabLayout.Tab tab = tabLayout.getTabAt(index);
        tab.select();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
      /*  if (id == R.id.action_save) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("tabindex", tabLayout.getSelectedTabPosition());
        Log.e("Activity", "onSaveInstanceState" + tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tabindex = savedInstanceState.getInt("tabindex");
        TabLayout.Tab tab = tabLayout.getTabAt(tabindex);
        tab.select();
        Log.e("Activity", "onRestoreInstanceState" + tabindex);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
       /* if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle pBundle) {
        bundle = pBundle;
    }

}
