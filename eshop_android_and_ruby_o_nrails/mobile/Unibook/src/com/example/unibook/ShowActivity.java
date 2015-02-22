package com.example.unibook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShowActivity extends BasePrivateActivity {
	public final static String EXTRA_MESSAGE = "com.example.unibook.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_show);
		Intent intent = getIntent();
	    String checkFlag= intent.getStringExtra("flag");
		String[] y = new String[4];
		if(checkFlag.equals("Followings")){
			y = intent.getStringArrayExtra(FollowingsActivity.EXTRA_MESSAGE);
		}
		else {
			y = intent.getStringArrayExtra(FollowersActivity.EXTRA_MESSAGE);
		}

		TextView first_name = (TextView) findViewById(R.id.txt_event_comment);
		first_name.setText(y[0]);
		
		TextView last_name = (TextView) findViewById(R.id.textView2);
		last_name.setText(y[1]);
		
		TextView email = (TextView) findViewById(R.id.textView3);
		email.setText(y[2]);
		
		TextView location = (TextView) findViewById(R.id.textView4);
		location.setText(y[3]);
	}
	
	public void followers(View view) {
		Intent intent = getIntent();
		Long id = intent.getLongExtra("_id", 0);
		intent = new Intent(ShowActivity.this, FollowersActivity.class);
		intent.putExtra("_id", id);
		startActivity(intent);
	}
	
	public void followings(View view) {
		Intent intent = getIntent();
		Long id = intent.getLongExtra("_id", 0);
		intent = new Intent(ShowActivity.this, FollowingsActivity.class);
		intent.putExtra("_id", id);
		startActivity(intent);
	}
	public void viewFriendEvents(View view) {
		Intent intent = getIntent();
		Long id = intent.getLongExtra("_id", 0);
		intent = new Intent(ShowActivity.this, MyEventsActivity.class);
		intent.putExtra("_id", id);
		startActivity(intent);
	}
	
}
