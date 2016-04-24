package com.example.administrator.tempele.main.widget;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.tempele.R;
import com.example.administrator.tempele.about.widget.AboutFragment;
import com.example.administrator.tempele.stock.widget.StockFragment;
import com.example.administrator.tempele.main.presenter.MainPresenter;
import com.example.administrator.tempele.main.presenter.MainPresenterImpl;
import com.example.administrator.tempele.main.view.MainView;
import com.example.administrator.tempele.news.widget.NewsFragment;
import com.example.administrator.tempele.weather.widget.WeatherFragment;

import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.customtoolbar)
    Toolbar customtoolbar;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @Bind(R.id.nagivation_view)
    NavigationView nagivationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainPresenter mainPresenter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenterImpl(this);
        setSupportActionBar(customtoolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
               // Toast.makeText(getApplicationContext(), "被打开了", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Toast.makeText(getApplicationContext(), "被关闭了", Toast.LENGTH_LONG).show();
            }
        };
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setUpDrawerContent(nagivationView);
        mainPresenter.switchNavigation(R.id.navigation_item_stocks);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        actionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        } else if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpDrawerContent(NavigationView navigationview) {
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mainPresenter.switchNavigation(item.getItemId());
                item.setChecked(true);
                drawerlayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    System.out.println("onMenuOpened...unable to set icons for overflow menu");
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }


    @Override
    public void DrawerSwitch(int type) {
        Fragment fargment = null;
        switch (type) {
            case R.id.navigation_item_stocks:
                fargment = new StockFragment().newInstance();
                customtoolbar.setTitle("股票");
                break;
            case R.id.navigation_item_news:
                fargment = new NewsFragment().newInstance();
                customtoolbar.setTitle("资讯");
                break;
            case R.id.navigation_item_weather:
                fargment = new WeatherFragment().newInstance();
                customtoolbar.setTitle("天气");
                break;
            case R.id.navigation_item_about:
                fargment = new AboutFragment().newInstance();
                customtoolbar.setTitle("关于");
                break;
            default:
                fargment = new StockFragment().newInstance();
                customtoolbar.setTitle("股票");
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,fargment).commit();
    }
}
