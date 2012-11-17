package com.example.geotrack;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;



public class Listview extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener{

	static ArrayList<String> listContents= new ArrayList<String>();/*create an arraylist to help put the location to fragment */


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        
        SQLhelper myDatabase=new SQLhelper(this); /*aim at database*/
		myDatabase.open();
		Cursor cursor=myDatabase.retrieveRecord(); /*read the locations in database*/
		cursor.moveToFirst();       /*read from the first line*/
		
		int count = cursor.getCount();
		
		/*get the location one by one*/
     	for (int i = 0; i < cursor.getCount(); i++) {
			listContents.add("Lat=" +cursor.getString(0) +"  "+"Lon "+ cursor.getString(1));
			cursor.moveToNext();
		}
		myDatabase.close();
        
        
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
                        }
            HeadlinesFragment firstFragment = new HeadlinesFragment();
            firstFragment.setArguments(getIntent().getExtras());      /*If fragments are not present app creates HeadlinesFragment*/      
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
            /* adds fragment in to the Fragment container*/
            
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_view, menu);
        return true;
    }

	/*Get articlefragment from fragment manager*/
	@Override
	public void onArticleSelected(int position) {
		
		Tablet articleFrag = (Tablet)getSupportFragmentManager().findFragmentById(R.id.article_fragment);
		
		if (articleFrag != null) {
		    articleFrag.updateArticleView(position);
		} else {
			Tablet newFragment = new Tablet();
		    Bundle args = new Bundle();
		    args.putInt(Tablet.ARG_POSITION, position);
		    newFragment.setArguments(args);
		    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		    transaction.replace(R.id.fragment_container, newFragment);
		    transaction.addToBackStack(null);
		    transaction.commit();
		}
		
		}

	
}
