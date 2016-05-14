package com.example.atomhacks;

import com.firebase.client.*;

public class DatabaseInterface {
	
	private Firebase dataRef;
	
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
	
	//Send a message to the server
	public void sendMessage(String message) {
		dataRef.push().setValue(message);
	}
}
