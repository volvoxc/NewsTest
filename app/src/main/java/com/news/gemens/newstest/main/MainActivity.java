package com.news.gemens.newstest.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.news.gemens.newstest.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private FrameLayout content;
    private NewsFragment newsFragment;
    private OtherFragment otherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setSupportActionBar(toolbar);
        setupDrawerContent(navigationView);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        switchToNews();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        content = (FrameLayout) findViewById(R.id.content_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        toolbar.setTitle(menuItem.getTitle());
                        switchNavigation(menuItem.getItemId());
                        return true;
                    }
                });
    }

    private void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                switchToNews();
                break;
            case R.id.navigation_item_other:
                switchToOther();
                break;
        }
    }

    private void switchToNews() {
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,newsFragment).commit();
    }

    private void switchToOther() {
        if (otherFragment == null) {
            otherFragment = new OtherFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,otherFragment).commit();
    }

}
