package com.example.atomhacks;

import java.util.ArrayList;
import android.os.Build;
import android.os.Bundle;
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
//import com.google.android.gms.auth.signin.GoogleSignInOptions.Builder;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1) public class Main extends Activity {

	private static boolean loggedIn = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Firebase.setAndroidContext(this);
        
        Intent logIn = new Intent(Main.this, Login.class);
        
        //If user isn't signed in, goes to the login screen
        if (loggedIn == false){
        	startActivity(logIn);
        }
        
        final ListView listview = (ListView) findViewById(R.id.listView);
        
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
             list.add(values[i]);
           }
            
        final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, final View view,
                  int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                      @Override
                      public void run() {
                        list.remove(item);
                        adapter.notifyDataSetChanged();
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
    
    
    public static void setLogin(boolean status){
    	loggedIn = status;
    }
}
