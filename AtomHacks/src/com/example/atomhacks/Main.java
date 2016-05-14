package com.example.atomhacks;

import java.util.ArrayList;

import android.os.Build;
import android.os.Bundle;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1) public class Main extends Activity {

	Button switchToUsers, logout, newProject;
	
	public static Firebase dataRef;
	public static String userID = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Firebase.setAndroidContext(this);
        
        dataRef = new Firebase("https://blazing-inferno-7604.firebaseio.com/");
        
        final Intent logIn = new Intent(Main.this, Login.class);
        
        //If user isn't signed in, goes to the login screen
        dataRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData == null) {
                    startActivity(logIn);
                }
            }
        });
        
      //Retrieve  button
        switchToUsers = (Button) findViewById(R.id.users);
        logout = (Button) findViewById(R.id.logoutButton);
        newProject = (Button) findViewById(R.id.newProjectButton);
        
        //button listener
        switchToUsers.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent userIntent = new Intent(Main.this, UserList.class);
                startActivity(userIntent);
            }
        });
        
        //Log out
        logout.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		dataRef.unauth();
            }
        });
        
        //New project
        newProject.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent newProject = new Intent(Main.this, ProjectSettings.class);
        		startActivity(newProject);
            }
        });
    
        final ListView listview = (ListView) findViewById(R.id.listView);
        
        String[] values = new String[] { "Project 1", "Project 2", "Project 3",
        		"Project 4", "Project 5", "Project 6","Project 7", "Project 8", 
        		"Project 9","Project 10", "Project 11", "Project 12","Project 13", 
        		"Project 14", "Project 15" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
             list.add(values[i]);
           }
            
        final StableArrayAdapter projectAdapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list);
            listview.setAdapter(projectAdapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
              public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                      @Override
                      public void run() {
                        list.remove(item);
                        projectAdapter.notifyDataSetChanged();
                        view.setAlpha(1);
                      }
                    });
              }

            });
    } 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
