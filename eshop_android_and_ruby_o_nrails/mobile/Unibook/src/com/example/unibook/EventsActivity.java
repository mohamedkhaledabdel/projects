package com.example.unibook;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class EventsActivity extends BasePrivateActivity {
	private ArrayAdapter<Event> adpEvents;
	Class <?> referrer;
	public final static String message = "com.example.unibook.MESSAGE";
	int eventID = 2;
	static String [] y; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		Intent intent = getIntent();
		y = new String[4];
		y = intent.getStringArrayExtra(AllEventsActivity.EXTRA_MESSAGE);
		TextView eventName = (TextView) findViewById(R.id.event_name);
		eventName.setText(y[0]);
		TextView description = (TextView) findViewById(R.id.event_description);
		description.setText(y[1]);
		TextView location = (TextView) findViewById(R.id.event_location);
		location.setText(y[2]);
		
		setup();
		
	}
	
	public void viewPosts(View view) {
		referrer = AllPostsActivity.class;
		Intent intent = new Intent(EventsActivity.this, referrer);
		intent.putExtra(message, y[3]);
		startActivity(intent);
	}
	
	public void setup() {
		final EditText textPost = (EditText) findViewById(R.id.event_post_text);
		Button btnBuy = (Button) findViewById(R.id.event_post_button);
		btnBuy.setEnabled(true);
		btnBuy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			startProgress();
			
			ApiRouter.withoutToken().addPost(eventID,(int)getCurrentUser().id, 
					textPost.getText().toString()
					,new Callback<Post>() {
			@Override
			public void success(Post post, Response
			rawResponse) {
			post.setEvent_id(eventID);
			post.setText(textPost.getText().toString());
			post.setUser_id((int) getCurrentUser().id);
			}
			@Override
			public void failure(RetrofitError e) {
			displayError(e);
			}
			});
			}
		});
	}
	
		

	
}
