package com.example.atomhacks;

import java.util.ArrayList;
import java.util.HashMap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1) public class UserList extends Activity {
	
		Button switchToProjects;
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.user_list);
	        
	        
	      //Retrieve  button
	        switchToProjects = (Button) findViewById(R.id.projects);
	        
	        //button listener
	        switchToProjects.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View v) {
	        		Intent projectIntent = new Intent(UserList.this, Main.class);
	                startActivity(projectIntent);
	            }
	        });
	    
	        final ListView listview = (ListView) findViewById(R.id.listView);
	        
	        //Get users
	        Main.dataRef.child("users").addValueEventListener(new ValueEventListener() {

				@Override
				public void onCancelled(FirebaseError arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onDataChange(DataSnapshot arg0) {
					try {
						HashMap<String, HashMap<Object, Object>> userList = (HashMap<String, HashMap<Object, Object>>) arg0.getValue();
						if(userList == null) {
							createList(new String[]{}, listview);
							return;
						}
						String[] values = new String[userList.size()];
						int i = 0;
						System.out.println(userList);
						for (HashMap<Object, Object> map : userList.values()) {
							values[i] = map.get("Name").toString();
							i++;
						}
						createList(values, listview);
					}
					catch (ClassCastException e) {
						createList(new String[]{}, listview);
					}
				}
	        	
	        });       

	    
	    } 
	    
	    public void createList(String[] values, ListView listview) {
	    	final ArrayList<String> list = new ArrayList<String>();
	        for (int i = 0; i < values.length; ++i) {
	             list.add(values[i]);
	           }
	            
	        final StableArrayAdapter userAdapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list);
	            listview.setAdapter(userAdapter);

	            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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

}
