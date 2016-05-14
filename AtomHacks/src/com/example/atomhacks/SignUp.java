<<<<<<< HEAD
package com.example.atomhacks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder; 

public class SignUp extends Activity{
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        findViewByID(R.id.signInButton).setOnClickListener(this); 
        
        //Prompts users for email to log in. 
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail().build(); 
        
        //Builds google API so that sign in api can be accessed.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .enableAutoManage(this , this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }
	
	//Details what happens when Sign-in button is pressed. 
	public void onClick(View v){
		switch(v.getId()){
		case R.id.signInButton:
			signIn(); 
			break; 
		}
	}
	
	//Sign in method that includes intent that goes to projects page. 
	public void signIn(){
		
	}
}
=======
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
	
	//Firebase reference
	private Firebase dataRef;
	
	//Constant references
	private static final String USER = "users";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        
        //Set up data reference for Firebase
        dataRef = new Firebase("https://blazing-inferno-7604.firebaseio.com/");
        
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
		dataRef.createUser(email.getText().toString(), password.getText().toString(),
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
			dataRef.child(userID).setValue(userData);
			
			//Get current user list and append new user's name and id
			dataRef.child(USER).addListenerForSingleValueEvent(new ValueEventListener() {
			    public void onDataChange(DataSnapshot snapshot) {
			    	HashMap<String, String> userList;
			    	try {
			    		userList = (HashMap<String, String>) snapshot.getValue();
			    	} catch (ClassCastException e) {
			    		userList = new HashMap<String, String>();
			    	}
			    	userList.put(email.getText().toString().replaceAll("\\.", ""), userID);
			    	dataRef.child(USER).setValue(userList);
			    }
			    public void onCancelled(FirebaseError firebaseError) {
			    }
			});
	}
}
>>>>>>> 14d4bd7c7ce22a9fdd441f9a0028d110316ead8d
