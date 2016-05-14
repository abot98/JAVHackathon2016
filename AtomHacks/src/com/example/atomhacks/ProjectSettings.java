package com.example.atomhacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ProjectSettings extends Activity {
	//Variables
	String name, description, skills; 
	public static final String PROJECT_NAME = "Project Name"; 
	public static final String DESCRIPTION = "Project Description"; 
	public static final String SKILL = "Skills Requested"; 
	//Objects
	Intent toUserInfo; 
	EditText projName; 
	EditText projDescription;
	EditText skillRequest;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_settings); 
    }
	
	//Adds and displays new project in User Info when button clicked. 
	public void sendInfo() {
		toUserInfo = new Intent(this, UserInfo.class); 
		projName= (EditText)findViewById(R.id.projectName); 
		projDescription= (EditText)findViewById(R.id.projDescription); 
		skillRequest= (EditText)findViewById(R.id.skillRequest); 
		
		name = projName.getText().toString(); 
		description= projDescription.getText().toString();
		skills= skillRequest.getText().toString(); 
		
		toUserInfo.putExtra(PROJECT_NAME, name);
		toUserInfo.putExtra(DESCRIPTION,  description); 
		toUserInfo.putExtra(SKILL,  skills); 
		startActivity(toUserInfo); 
	}
	
}
