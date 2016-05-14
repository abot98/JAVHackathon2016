package com.example.atomhacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSettings extends Activity {
	Intent toProjectSettings;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);
        
    }
	
	//Goes to Project Settings xml when button clicked. 
	public void toProjectSettings(View v){
		toProjectSettings = new Intent(UserSettings.this, ProjectSettings.class);  
		startActivity(toProjectSettings); 	
	}
}
