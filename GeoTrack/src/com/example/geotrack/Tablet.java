package com.example.geotrack;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tablet extends Fragment{
	
	final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
         if (savedInstanceState != null) {
        	
        	 mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);};
        	
         }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      return inflater.inflate(R.layout.activity_tablet, container, false);
    }
	
	/*get values from intent*/
	@Override
    public void onStart() {
        super.onStart();
    Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition);
        }
    }
	/*This is used when article updated*/
	public void updateArticleView(int position) {
		TextView article = (TextView) getActivity().findViewById(R.id.tablet);
		
		mCurrentPosition = position;
		}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	outState.putInt(ARG_POSITION, mCurrentPosition);
	}

}
