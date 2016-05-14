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
