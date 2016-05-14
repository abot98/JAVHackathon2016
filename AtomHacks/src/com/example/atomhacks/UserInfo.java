package com.example.atomhacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserInfo extends Activity {
	Intent getInfo; 
	TextView line1, line2, line3; 
	String message, description, skills; 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        getInfo= getIntent(); 
        
        message= getInfo.getStringExtra(ProjectSettings.PROJECT_NAME);
        description= getInfo.getStringExtra(ProjectSettings.DESCRIPTION); 
        skills= getInfo.getStringExtra(ProjectSettings.SKILL); 
        
        setAllText(message, description, skills); 
    }
	
	public void setAllText(String s1, String s2, String s3){
		line1 = new TextView(this); 
		line2 = new TextView(this); 
		line3 = new TextView(this); 
		
		line1.setText(s1); 
		line2.setText(s2); 
		line3.setText(s3); 
	}
}
