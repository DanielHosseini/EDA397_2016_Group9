package com.group9.eda397;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.group9.eda397.ui.activities.BaseActivity;
import com.group9.eda397.ui.activities.SettingsActivity;
import com.group9.eda397.ui.fragments.ChooseTimeFragment;
import com.group9.eda397.ui.fragments.GithubCommitsFragment;
import com.group9.eda397.ui.fragments.PlanningGameFragment;
import com.group9.eda397.ui.fragments.TravisBuildsFragment;
import com.group9.eda397.ui.fragments.WelcomeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.fragment_container) FrameLayout fragmentContainer;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, WelcomeFragment.newInstance());
            ft.commit();
        }

        SharedPreferences sharedPreferences = this.getSharedPreferences(SettingsActivity.SHARED_PREF_NAME_DEFAULT, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean pendingChanges = false;
        if (!sharedPreferences.contains(SettingsActivity.SHARED_PREF_KEY_REPOSITORY)) {
            pendingChanges = true;
            editor.putString(SettingsActivity.SHARED_PREF_KEY_REPOSITORY, SettingsActivity.DEFAULT_REPOSITORY);
        }
        if (!sharedPreferences.contains(SettingsActivity.SHARED_PREF_KEY_USERNAME)) {
            pendingChanges = true;
            editor.putString(SettingsActivity.SHARED_PREF_KEY_USERNAME, SettingsActivity.DEFAULT_USERNAME);
        }
        if (pendingChanges) {
            editor.commit();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
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

    @Subscribe
    public void onFinishedTimerEvent(final ChooseTimeFragment.FinishedTimerEvent event) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Pair programming timer is finished");
        builder.setSmallIcon(R.drawable.timer);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        builder.setSound(alarmSound);

        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0, builder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            startActivity(SettingsActivity.getStartingIntent(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = WelcomeFragment.newInstance();
        } else if (id == R.id.nav_timer) {
            fragment = ChooseTimeFragment.newInstance("timer");
        } else if (id == R.id.nav_travis) {
            fragment = TravisBuildsFragment.newInstance();
        } else if (id == R.id.nav_planning_game) {
            fragment = PlanningGameFragment.newInstance();
        } else if (id == R.id.nav_github) {
            fragment = GithubCommitsFragment.newInstance();
        }


        // Set the fragment as the fragment container
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
