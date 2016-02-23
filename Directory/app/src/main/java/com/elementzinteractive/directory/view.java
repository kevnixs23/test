package com.elementzinteractive.directory;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class view extends AppCompatActivity{
    public static final int SERVICES_ALL = 0;
    public static final int SERVICES_FEATURED = 1;
    public static final int SERVICES_NEW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        final String value  =iin.getExtras().toString();
        final String sName  =iin.getStringExtra("name");
        final String sPhone  = iin.getStringExtra("phone");
        final String sEmail  = iin.getStringExtra("email");

        TextView tvName = (TextView)findViewById(R.id.textView);
        tvName.setText(sName);
        TextView tvPhone = (TextView)findViewById(R.id.textView2);
        tvPhone.setText(sPhone);
        TextView tvEmail = (TextView)findViewById(R.id.textView3);
        tvEmail.setText(sEmail);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Toast.makeText(view.this, "" + sName + "\n" + sPhone + "\n" + sEmail, Toast.LENGTH_SHORT).show();

            }
        });

        Button btn = (Button)findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


      /*  fragment_one newFragment = new fragment_one();
        Bundle args = new Bundle();
        //args.putInt(fragment_one.ARG_POSITION, position);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_place, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void selectFrag(View view) {


    }

}
