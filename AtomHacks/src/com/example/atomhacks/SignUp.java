package com.example.atomhacks;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends Activity{
	
	//Inputs for user info
	private EditText firstName, lastName, email, password, confirmPassword, description;
	
	//Submit button
	private Button submitButton;
	
	//Constant references
	private static final String USER = "users";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        
        //Retrieve inputs from layout
        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordInput);
        description = (EditText) findViewById(R.id.descriptionInput);
        
        //Retrieve submit button
        submitButton = (Button) findViewById(R.id.submitButton);
        
        //Submit button listener
        submitButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		//If data is filled out properly, create the user
                if(checkData()) {
                	createUser();
                }
            }
        });
    }
	
	//Check to make sure all data is correct (specifically the password)
	public boolean checkData() {
		//Check data, TO-DO implement later
		return true;
	}
	
	//Create the user in firebase
	public void createUser() {
		//Create the user
		Main.dataRef.createUser(email.getText().toString(), password.getText().toString(),
				new Firebase.ValueResultHandler<Map<String, Object>>() {
		    @Override
		    public void onSuccess(Map<String, Object> result) {
		        addUserToDatabase(result.get("uid").toString());
		    }
			@Override
		    public void onError(FirebaseError firebaseError) {
		        System.out.println("Error creating user");
		    }
		});
	}
	
	//Add the user's info to the database records
	private void addUserToDatabase(final String userID) {
			//Create HashMap for user data
			HashMap<String, Object> userData = new HashMap<String, Object>();
			userData.put("firstName", firstName.getText().toString());
			userData.put("lastName", lastName.getText().toString());
			userData.put("email", email.getText().toString());
			userData.put("description", description.getText().toString());
			userData.put("projects", new HashMap<String, String>());
			// Save Map
			Main.dataRef.child(userID).setValue(userData);
			
			//Get current user list and append new user's name and id
			Main.dataRef.child(USER).addListenerForSingleValueEvent(new ValueEventListener() {
			    public void onDataChange(DataSnapshot snapshot) {
			    	HashMap<String, String> userList;
			    	try {
			    		userList = (HashMap<String, String>) snapshot.getValue();
			    	} catch (ClassCastException e) {
			    		userList = new HashMap<String, String>();
			    	}
			    	userList.put(email.getText().toString().replaceAll("\\.", ""), userID);
			    	Main.dataRef.child(USER).setValue(userList);
			    }
			    public void onCancelled(FirebaseError firebaseError) {
			    }
			});
	}
}