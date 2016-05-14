package com.example.atomhacks;

import java.util.HashMap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserInfo extends Activity {
	Intent getInfo; 
	TextView line1, line2, line3; 
	RelativeLayout layout; 
	String message, description, skills; 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        getInfo= getIntent(); 
        
        message= getInfo.getStringExtra(ProjectSettings.PROJECT_NAME);
        description= getInfo.getStringExtra(ProjectSettings.DESCRIPTION); 
        skills= getInfo.getStringExtra(ProjectSettings.SKILL); 
        
        Main.dataRef.child(Main.userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
            	HashMap<String, String> userInfo;
            	try {
            		userInfo = (HashMap<String, String>) snapshot.getValue();
            	} catch (ClassCastException e) {
            		userInfo = new HashMap<String, String>();
            	}
            	if(userInfo == null) userInfo = new HashMap<String, String>();
            	
            	//Fill in the info (first name, last name, etc here)
            	
            }

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}
        });
    }

	
	public void setAllText(String s1, String s2, String s3){
		line1 = new TextVW PROJECT OBJECT IS ADDED TO PROJECTLIST. 
}
