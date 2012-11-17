package com.example.geotrack;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.content.Intent;

import android.content.SharedPreferences;
import android.location.Location;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;

/*This is special fragment that show user a ListView*/
public class HeadlinesFragment extends ListFragment {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		int layout = Build.VERSION.SDK_INT >= 
				Build.VERSION_CODES.HONEYCOMB ?   
				android.R.layout.simple_list_item_activated_1 : 
				android.R.layout.simple_list_item_1;
		
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Listview.listContents));/*populate the listview*/
  }
	

	/*This is interface for MainActivity to get access in 
      HeadlineFragment's on click events*/
	OnHeadlineSelectedListener mCallback;
     public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
     }
    
     /*This is for container to get list positions*/
     @Override
     public void onAttach(Activity activity) {
             super.onAttach(activity);
     try {
                 mCallback = (OnHeadlineSelectedListener) activity;
             } catch (ClassCastException e) {
                 throw new ClassCastException(activity.toString()
                         + " must implement OnHeadlineSelectedListener");
             }
     
         }
     
     /*sends on cliks to interface*/
     @Override
     public void onListItemClick(ListView l, View v, int position, long id) 
     {
             mCallback.onArticleSelected(position);
             getListView().setItemChecked(position, true);
     }
    
     }
