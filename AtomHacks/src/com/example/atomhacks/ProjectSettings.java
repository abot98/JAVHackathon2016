package com.example.atomhacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
	Button btn1; 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_settings); 
        
        /*btn1 = (Button)findViewById(R.id.submit); 
        btn1.setOnClickListener( new View.OnClickListener() {
	        	public void onClick(View v) {
	        		Intent toUserInfo = new Intent(ProjectSettings.this, UserInfo.class);
	                startActivity(toUserInfo);
	            }
	        }); */
    }
	
	//Adds and displays new project in User Info when button clicked. 
	public void sendInfo() {
		toUserInfo = new Intent(ProjectSettings.this, UserInfo.class); 
		
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
