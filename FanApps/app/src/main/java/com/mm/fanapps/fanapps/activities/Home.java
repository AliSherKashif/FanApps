package com.mm.fanapps.fanapps.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mm.fanapps.fanapps.FragmentDrawer;
import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.utilities.Config;


import adapter.NavigationDrawerAdapter;

public class Home extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private DrawerLayout drawerLayout;

    private int height =0;
    private int width =0;
    public static int selectedPosition =0;

    private static String TAG = Home.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // height and width resolution of devices
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);


        // display the first navigation drawer view on app launch
        displayView(0);

        // check orientation mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Do some stuff
            OrientationLandScape();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Do some stuff
            OrientationPortrait();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {

        displayView(position);

    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = Config.titles[position];
        selectedPosition = position;

        switch (title) {
            case "Cheats":
                fragment = new CheatsFragment();

                break;
            case "Walkthrough":
                fragment = new WalkthroughFragment();

                break;
            case "Live Streaming":
                fragment = new LivestreamingFragment();

                break;

            case "Recorded Games":
                fragment = new RecordedgamesFragment();

                break;

            case "RSS":
                fragment = new RSSFragment();

                break;

            case "Facebook":
                fragment = new FacebookFragment();

                break;

            case "Twitter":
                fragment = new TwitterFragment();

                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
            //Set the selected drawer row
            FragmentDrawer.adapter = new NavigationDrawerAdapter(this, FragmentDrawer.getData());
            FragmentDrawer.recyclerView.setAdapter(FragmentDrawer.adapter);

        }
    }



    private void OrientationPortrait() {
        // TODO Auto-generated method stub
        //Toast.makeText(getApplicationContext(), "Portrait: ::: Height="+height+"  Width="+ width , Toast.LENGTH_SHORT).show();
       // drawerLayout.closeDrawer(Gravity.LEFT);


    }

    private void OrientationLandScape() {
        // TODO Auto-generated method stub
       // drawerLayout.openDrawer(Gravity.LEFT);
        //width>=800

       // if(width>=720){
            Intent firstIntent=new Intent(getBaseContext(),First.class);
            startActivity(firstIntent);
            finish();
        //}

        //Toast.makeText(getApplicationContext(), "Landscape ::: Height="+height+"  Width="+ width, Toast.LENGTH_SHORT).show();


    }



    // ////////////////////// orientation of screens

    @SuppressWarnings("deprecation")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen for landscape and portrait
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            OrientationLandScape();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            OrientationPortrait();
        }
    }


}
