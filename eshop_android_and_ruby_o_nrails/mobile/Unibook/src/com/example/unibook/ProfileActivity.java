package com.example.unibook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends BasePrivateActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_profile);
		
		
		TextView first_name = (TextView) findViewById(R.id.txt_event_comment);
		first_name.setText(getCurrentUser().getFirstName());
		
		TextView last_name = (TextView) findViewById(R.id.textView2);
		last_name.setText(getCurrentUser().getLastName());
		
		TextView email = (TextView) findViewById(R.id.textView3);
		email.setText(getCurrentUser().getEmail());
		
		TextView location = (TextView) findViewById(R.id.textView4);
		location.setText(getCurrentUser().getLocation());
	}
	
	public void followers(View view) {
		Intent intent = new Intent(this, FollowersActivity.class);
		intent.putExtra("flag", "Profile");
		startActivity(intent);
	}
	
	public void followings(View view) {
		Intent intent = new Intent(this, FollowingsActivity.class);
		intent.putExtra("flag", "Profile");
		startActivity(intent);
	}
	
	public void events(View view) {
		Intent intent = new Intent(this, AllEventsActivity.class);
		startActivity(intent);
	}
	
	public void settings(View view) {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
	
	public void createEvent(View view) {
		Intent intent = new Intent(this, CreateEventActivity.class);
		startActivity(intent);
	}
	
	public void viewMyEvents(View view) {
		Intent intent = new Intent(this, MyEventsActivity.class);
		startActivity(intent);
	}
	
	public void post(View view) {
	
		 
	}
}
