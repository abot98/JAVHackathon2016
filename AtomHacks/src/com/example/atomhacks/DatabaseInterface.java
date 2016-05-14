package com.example.atomhacks;

import java.util.ArrayList;
import java.util.HashMap;

import com.firebase.client.*;

public class DatabaseInterface {
	
	public static Firebase dataRef;
	private HashMap<Integer, String> projectList, userList;  //<ID, Description>
	
	public DatabaseInterface() {
		dataRef = new Firebase("https://blazing-inferno-7604.firebaseio.com/");
		
		//Value listener gets called everytime data changes
		dataRef.addValueEventListener(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot snapshot) {
		        System.out.println(snapshot.getValue());
		    }
		    @Override
		    public void onCancelled(FirebaseError firebaseError) {
		        System.out.println("The read failed: " + firebaseError.getMessage());
		    }
		});
	}
	
	//Get list of project names
	public String[] getProjectNamesList() {
		return (String[]) projectList.values().toArray();
	}
	
	//Get list of user descriptors
}
