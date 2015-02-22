package com.example.unibook;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.content.Intent;
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

public class FollowersActivity extends BasePrivateActivity {
	private ArrayAdapter<User> adFollowers;
	public final static String EXTRA_MESSAGE = "com.example.unibook.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_followers);
		setUpViews();
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		refreshViews();
	}

	private void setUpViews() {
		ListView lstFollowers = (ListView) findViewById(R.id.lst_followers);
		adFollowers = new ArrayAdapter<User>(this, 0) {
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
						Intent intent = new Intent(FollowersActivity.this, ShowActivity.class);
						String[] value = {user.getFirstName(), user.getLastName(), user.getEmail(), user.getLocation(), user.getId()  + ""};
						intent.putExtra(EXTRA_MESSAGE, value);
						intent.putExtra("_id", user.getId());
						intent.putExtra("flag", "Followers");
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
		lstFollowers.setAdapter(adFollowers);
	}

	protected void refreshViews() {
		super.refreshViews();
		adFollowers.clear();
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
		ApiRouter.withoutToken().getFollowers(x, new Callback<List<User>>() {
		public void success(List<User> users, Response response) {
			adFollowers.addAll(users);
		stopProgress();
		}
		@Override
		public void failure(RetrofitError e) {
		displayError(e);
		}
		});
		}

	
}
