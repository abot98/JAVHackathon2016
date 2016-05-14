package com.example.atomhacks;

import android.os.Bundle;

import com.firebase.client.Firebase;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import com.google.android.gms.auth.signin.GoogleSignInOptions.Builder;

public class Main extends Activity {

	private static boolean loggedIn = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       
        
        Intent logIn = new Intent(Main.this, Login.class);
        
        //If user isn't signed in, goes to the login screen
        if (loggedIn == false){
        	startActivity(logIn);
        }
    } 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public static void setLogin(boolean status){
    	loggedIn = status;
    }
}
