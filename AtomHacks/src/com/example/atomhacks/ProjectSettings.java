package com.example.atomhacks;

import java.util.HashMap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
	Button submitButton; 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_settings); 
        
        submitButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		sendInfo();
            }
        });
    }
	
	//Adds and displays new project in User Info when button clicked. 
	public void sendInfo() { 
		
		projName= (EditText)findViewById(R.id.projectName); 
		projDescription= (EditText)findViewById(R.id.projDescription); 
		skillRequest= (EditText)findViewById(R.id.skillRequest); 
		
		final HashMap<String, Object> projectInfo = new HashMap<String, Object>();
		projectInfo.put("Name", projName.getText().toString());
		projectInfo.put("Owner", Main.userName);
		projectInfo.put("Owner ID", Main.userID);
		projectInfo.put("Description", projDescription.getText().toString());
		projectInfo.put("Skills", skillRequest.getText().toString());
		
		Main.dataRef.child(Main.userID).child(String.valueOf(projectInfo.hashCode())).setValue(projectInfo);
		
		//Get current project list and append new project's name, owner id, and project id
		final HashMap<Object, Object> basicProjectInfo = new HashMap<Object, Object>();
		basicProjectInfo.put("Name", projectInfo.get("Name"));
		basicProjectInfo.put("Owner ID", Main.userID);
		basicProjectInfo.put("Project ID", String.valueOf(projectInfo.hashCode()));
		Main.dataRef.child("projects").addListenerForSingleValueEvent(new ValueEventListener() {
		    public void onDataChange(DataSnapshot snapshot) {
		    	HashMap<String, HashMap<Object, Object>> projectList;
		    	try {
		    		projectList = (HashMap<String, HashMap<Object, Object>>) snapshot.getValue();
		    	} catch (ClassCastException e) {
		    		projectList = new HashMap<String, HashMap<Object, Object>>();
		    	}
		    	if (projectList == null) {
		    		projectList = new HashMap<String, HashMap<Object, Object>>();
		    	}
		    	projectList.put(String.valueOf(projectInfo.hashCode()), basicProjectInfo);
		    	Main.dataRef.child("projects").setValue(projectList);
		    	
		    	Intent switchToMain = new Intent(ProjectSettings.this, Main.class);
		    	startActivity(switchToMain);
		    }
		    public void onCancelled(FirebaseError firebaseError) {
		    }
		});
		
		
		
	}
	
}
