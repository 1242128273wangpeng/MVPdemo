package com.example.administrator.mvpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.mvpdemo.presenter.UserLoginPresenter;
import com.example.administrator.mvpdemo.view.IUserLoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements IUserLoginView {

    @Bind(R.id.name_et)
    EditText nameEt;
    @Bind(R.id.pswd_et)
    EditText pswdEt;
    @Bind(R.id.login_but)
    Button loginBut;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private UserLoginPresenter userpresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_but)
    public void UserLogin() {
        userpresenter.login();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(MainActivity.this, "tttttttttt", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toActivity() {
        Toast.makeText(MainActivity.this, "login success......", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showfailError() {
        Toast.makeText(MainActivity.this, "login fail......", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getuserName() {
        return nameEt.getText().toString();
    }

    @Override
    public String getuserPassword() {
        return pswdEt.getText().toString();
    }

}
