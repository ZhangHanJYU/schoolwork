package com.example.geotrack;

/* This is the final exercise for TIES425 Programming for Mobile Terminals.
 *                                                             ZhangHan
 *                                             zhanghan.z.h@hotmail.com     */

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.location.*;


public class MainActivity extends Activity {
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void Open(View view){
    	 
    	Intent i = new Intent(this, MainActivityMap.class);  /* When push OpenMap button can connect to MapView */
        startActivity(i);
    }
    
    public void listview(View view){
    	
    	Intent i = new Intent(this, Listview.class);  /* When push SeeListview button can connect to the list which store the locations */
        
    	startActivity(i);
    }
	
}
