package com.example.geotrack;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivityMap extends MapActivity {
	
	private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
	
	
	MapController mapController;
	MapView mapview;
	
	
	/* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_map);/*gain the Google map*/
        mapview = (MapView)findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
	    mapController.setZoom(10);                    /* set the size of the map */
	    
	    final List<Overlay> mapOverlays = mapview.getOverlays();              /*add the item(pup)to show the current location*/
        Drawable drawable = this.getResources().getDrawable(R.drawable.pup);
        final ItemsOverlay itemizedoverlay = new ItemsOverlay(drawable, this);
              
        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE); /*get connect to the GPS*/
        
        LocationListener locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
			
				makeUseOfNewLocation(location);
				Toast.makeText(getApplicationContext(), "New Location", Toast.LENGTH_LONG).show(); /* show "New Location" when load to the 
				                                                                                      map for a very short time */
				
			}
			

			private void makeUseOfNewLocation(Location location) {
				
				
				double lon = (double) (location.getLongitude() * 1E6);
				double lat = (double) (location.getLatitude() * 1E6);
				
				int lontitue = (int)lon;
				int latitute = (int)lat;
				
				
				
				GeoPoint geopoint = new GeoPoint(latitute, lontitue); /*get current location*/
				
		        
		        OverlayItem overlayitem = new OverlayItem(geopoint,"your location", "!");
		        
		        itemizedoverlay.addOverlay(overlayitem);
		        mapOverlays.add(itemizedoverlay);
				
				mapController.animateTo(geopoint);   /* animate the map */
				
				mapview.invalidate();
				
				updatedatabase(location);    /* update current location to SQL database */
				

		       
			}
			/* called when update the locations to database */
		    public void updatedatabase(Location location){
		    	
		    	SQLhelper myDatabase=new SQLhelper(getApplicationContext());
				myDatabase.open();
				myDatabase.insertRow(location.getLongitude(),location.getLatitude());  /* store locations to SQL database */
				myDatabase.close();
		    }
		
		};
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,10,locationListener); /*update the location within 3000ms, and 10m*/
		   
    }
    


	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}