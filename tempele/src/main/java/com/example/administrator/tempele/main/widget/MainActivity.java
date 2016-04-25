package com.example.administrator.tempele.main.widget;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.administrator.tempele.stock.widget.SearchFragment;
import com.example.administrator.tempele.stock.widget.StockCatgFragment;
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
    ActionBar actionBar;
    Menu mymenu;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainPresenter mainPresenter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenterImpl(this);
        setSupportActionBar(customtoolbar);
       actionBar = getSupportActionBar();
        if (actionBar != null) {
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
        this.mymenu = menu;
        // 不能放在OnCreate里面，因为当时没有赋值mymenu
        mainPresenter.switchNavigation(R.id.navigation_item_stocks);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(MainActivity.this, "Toast--onOptionsItemSelected", Toast.LENGTH_SHORT).show();
            SearchFragment fragmentsear = SearchFragment.newInstance("","");
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragmentsear).commit();
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
      return true;
    }


    @Override
    public void DrawerSwitch(int type) {
        FragmentTransaction ft = null;
        Fragment fargment = null;
        switch (type) {
            case R.id.navigation_item_stocks:
                mymenu.findItem(R.id.action_search).setVisible(true);
                fargment = new StockCatgFragment().newInstance();
                customtoolbar.setBackgroundColor(0xffff4040);
                customtoolbar.setTitle("股票");
                break;
            case R.id.navigation_item_news:
                mymenu.findItem(R.id.action_search).setVisible(false);
                fargment = new NewsFragment().newInstance();
                customtoolbar.setBackgroundColor(0xffffddaa);
                customtoolbar.setTitle("资讯");
                break;
            case R.id.navigation_item_weather:
                mymenu.findItem(R.id.action_search).setVisible(true);
                fargment = new WeatherFragment().newInstance();
                customtoolbar.setTitle("天气");
                break;
            case R.id.navigation_item_about:
                mymenu.findItem(R.id.action_search).setVisible(true);
                fargment = new AboutFragment().newInstance();
                customtoolbar.setTitle("关于");
                break;
            default:
                fargment = new StockFragment().newInstance();
                mymenu.findItem(R.id.action_search).setVisible(true);
                customtoolbar.setTitle("股票");
                break;
        }
         ft =  getSupportFragmentManager().beginTransaction();
         ft.replace(R.id.frame_content,fargment);
        // 提交的都是一个Fragment，所以不可作为add栈中的标记，以后再考虑什么做标记可能是agrs
        System.out.println("fargment.getId()"+fargment.getId());
         ft.addToBackStack(String.valueOf(fargment.getId()));
         ft.commit();
    }
}