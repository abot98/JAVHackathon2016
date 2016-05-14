package com.example.atomhacks;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity implements OnClickListener{
	
	TextView newAccount;
	Button submitLogin;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
	}
	
	public void init(){
		newAccount = (TextView)findViewById(R.id.newAccount);
		newAccount.setOnClickListener(this);
		
	    submitLogin = (Button)findViewById(R.id.submitLogin);
	    submitLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		
		if(view.getId()== submitLogin.getId()){
			Main.setLogin(true);
			Intent projectList = new Intent(Login.this, Main.class);
			startActivity(projectList);
		}
		
		if(view.getId()== newAccount.getId()){
			Intent signUp = new Intent(Login.this, SignUp.class);
			startActivity(signUp);
		}
		
	}
    
}
