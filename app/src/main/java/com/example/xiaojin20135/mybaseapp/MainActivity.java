package com.example.xiaojin20135.mybaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.retrofit.helper.RetrofitManager;

public class MainActivity extends ToolBarActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText(R.string.main_page);
//        setToolbarColor(R.color.white);
//        setTitleColor(R.color.black);
        RetrofitManager.RETROFIT_MANAGER.init ("http://www.topscomm.com:6724/TopscommRms/");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        Log.d(TAG,"in initView");
    }

    @Override
    protected void initEvents() {
//        Log.d(TAG,"in initEvents");
    }

    @Override
    protected void loadData() {
//        Log.d(TAG,"in loadData");
    }

    @Override
    public void showProgress () {

    }

    @Override
    public void dismissProgress () {

    }

    @Override
    public void loadDataSuccess (Object tData) {

    }

    @Override
    public void loadError (Throwable throwable) {

    }

    @Override
    public void loadComplete () {

    }

    @Override
    public void onClick (View v) {
        switch (v.getId ()){
            case R.id.sign_in_btn:
                Intent intent = new Intent (MainActivity.this, MyLoginActivity.class);
                startActivity (intent);
                break;
        }

    }
}
