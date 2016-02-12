package com.mm.fanapps.fanapps.activities;

import android.app.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.TabletFragments.DrawerFragment;
import com.mm.fanapps.fanapps.activities.TabletFragments.TabletCheatsFragment;
import com.mm.fanapps.fanapps.activities.youtube.domain.Library;
import com.mm.fanapps.fanapps.activities.youtube.task.GetYouTubeUserVideosTask;
import com.mm.fanapps.fanapps.activities.youtube.widget.VideosListView;


/**
 * Created by Ali Sher on 1/25/2016.
 */
public class First extends Activity {

    public static LinearLayout drawerLL, fragmentLL, parentFragment;
    private int height =0;
    private int width =0;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablet_horizontal_activity);


        // height and width resolution of devices
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        drawerLL = (LinearLayout) findViewById(R.id.drawer_fragment_LL);
        fragmentLL = (LinearLayout) findViewById(R.id.other_fragment_LL);
        parentFragment = (LinearLayout) findViewById(R.id.fragments_LL);




    }

    private void OrientationPortrait() {
        // TODO Auto-generated method stub
        Intent homeIntent=new Intent(getBaseContext(),Home.class);
        startActivity(homeIntent);


    }

    private void OrientationLandScape() {
        // TODO Auto-generated method stub



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
