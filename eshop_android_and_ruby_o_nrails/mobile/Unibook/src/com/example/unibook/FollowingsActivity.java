package com.example.unibook;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FollowingsActivity extends BasePrivateActivity {
	private ArrayAdapter<User> adFollowings;
	public final static String EXTRA_MESSAGE = "com.example.unibook.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_followings);
		setUpViews();
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		refreshViews();
	}

	private void setUpViews() {
		ListView lstFollowings = (ListView) findViewById(R.id.lst_followings);
		adFollowings = new ArrayAdapter<User>(this, 0) {
			private LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final User user= getItem(position);

				View view;
				if (convertView == null) {
					view = mInflater.inflate(R.layout.activity_followers, parent, false);
				} else {
					view = convertView;
				}

				TextView txtName = (TextView) view.findViewById(R.id.user_name);
				txtName.setText(user.getFirstName());
				
				Button btn_show = (Button) view.findViewById(R.id.btn_show);
				btn_show.setVisibility(View.VISIBLE);
				btn_show.setEnabled(true);
				btn_show.setOnClickListener(new View.OnClickListener() {
					@Override
				public void onClick(View v) {
				startProgress();
				ApiRouter.withoutToken().showUser(user.getId(), new Callback<User> () {
					@Override
					public void success(User response, Response
					rawResponse) {
						Intent intent = new Intent(FollowingsActivity.this, ShowActivity.class);
						String[] value = {user.getFirstName(), user.getLastName(), user.getEmail(), user.getLocation(), user.getId()  + ""};
						intent.putExtra(EXTRA_MESSAGE, value);
						intent.putExtra("_id", user.getId());
						intent.putExtra("flag", "Followings");
						startActivity(intent);
					}
					@Override
					public void failure(RetrofitError e) {
					displayError(e);
					}
					});
					}
				});


				return view;
			}
		};
		lstFollowings.setAdapter(adFollowings);
	}

	protected void refreshViews() {
		super.refreshViews();
		adFollowings.clear();
		startProgress();
		Intent intent = getIntent();
	    String checkFlag= intent.getStringExtra("flag");
		long x;

		if(checkFlag.equals("Profile")){
			
			x =  getCurrentUser().getId();
		}
		else{
			 x = intent.getLongExtra("_id", 0);
		}
		ApiRouter.withoutToken().getFollowings(x, new Callback<List<User>>() {
		public void success(List<User> users, Response response) {
			adFollowings.addAll(users);
		stopProgress();
		}
		@Override
		public void failure(RetrofitError e) {
			displayError(e);
		}
		});
		}

	
}







