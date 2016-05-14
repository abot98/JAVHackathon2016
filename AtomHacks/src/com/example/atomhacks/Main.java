package com.example.atomhacks;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Build;
import android.os.Bundle;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import android.annotation.SuppressLint;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
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
	public static String userID = "", userName = "";
	
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
        
//      //Get projects
//        Main.dataRef.child("projects").addValueEventListener(new ValueEventListener() {
//
//			@Override
//			public void onCancelled(FirebaseError arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onDataChange(DataSnapshot arg0) {
//				try {
//					HashMap<String, HashMap<Object, Object>> projectList = (HashMap<String, HashMap<Object, Object>>) arg0.getValue();
//					if(projectList == null) {
//						createList(new String[]{}, listview);
//						return;
//					}
//					String[] values = new String[projectList.size()];
//					int i = 0;
//					System.out.println(projectList);
//					for (HashMap<Object, Object> map : projectList.values()) {
//						values[i] = map.get("Name").toString();
//						i++;
//					}
//					createList(values, listview);
//				}
//				catch (ClassCastException e) {
//					createList(new String[]{}, listview);
//				}
//			}
//        	
//        }); 

        
    } 
    
    public void createList(String[] values, ListView listview) {
    	final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
             list.add(values[i]);
           }
            
        final StableArrayAdapter userAdapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list);
            listview.setAdapter(userAdapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			@SuppressLint("NewApi") //IMPORTANTE SUPPRESSED CUZ LAPTOP IS PMSing
			@Override
              public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                      @Override
                      public void run() {
                        list.remove(item);
                        userAdapter.notifyDataSetChanged();
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
