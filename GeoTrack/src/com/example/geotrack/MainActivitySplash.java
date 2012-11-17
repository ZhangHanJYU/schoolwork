package com.example.geotrack;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;

public class MainActivitySplash extends Activity {

	protected Dialog mSplashDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        MyStateSaver data = (MyStateSaver) getLastNonConfigurationInstance();
        if (data != null) {
            /* Show splash screen if still loading*/
            if (data.showSplashScreen) {
                showSplashScreen();
            }
            setContentView(R.layout.activity_list_view);        
     
            /* show splash if load to listview will need a long time */
        } else {
            showSplashScreen();
            setContentView(R.layout.activity_list_view);
        }
    }
    
    @Override
    public Object onRetainNonConfigurationInstance() {
        MyStateSaver data = new MyStateSaver();
        
     
        if (mSplashDialog != null) {
            data.showSplashScreen = true;
            removeSplashScreen();
        }
        return data;
    }
     
    /*Removes the Dialog that displays the splash screen*/
    protected void removeSplashScreen() {
        if (mSplashDialog != null) {
            mSplashDialog.dismiss();
            mSplashDialog = null;
        }
    }
    

    private void showSplashScreen() {
    	mSplashDialog = new Dialog(this, R.style.SplashScreen);
        mSplashDialog.setContentView(R.layout.activity_main_activity_splash);
        mSplashDialog.setCancelable(false);
        mSplashDialog.show();
         
        /*Set Runnable to remove splash screen just in case*/
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            removeSplashScreen();
          }
        }, 3000);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_activity_splash, menu);
        return true;
    }
}
    
    class MyStateSaver {
        public boolean showSplashScreen = false;
        
    }
