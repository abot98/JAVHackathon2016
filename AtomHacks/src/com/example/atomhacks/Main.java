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

	private EditText inputText;
	private Button sendBtn;
	
	private static boolean loggedIn = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Establishes firebase (server)
        Firebase.setAndroidContext(this);
        
        //Database class for server comms
        final DatabaseInterface databaseInterface = new DatabaseInterface();
        
        inputText = (EditText) findViewById(R.id.editText1);
        sendBtn = (Button) findViewById(R.id.button1);
        //Sends simple info to server
        sendBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					databaseInterface.sendMessage(inputText.getText().toString());	
					inputText.setText("");
			}
		});
        
        Intent logIn = new Intent(Main.this, Login.class);
        
        //If user isn't signed in, goes to the login screen
        if (loggedIn = false){
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
