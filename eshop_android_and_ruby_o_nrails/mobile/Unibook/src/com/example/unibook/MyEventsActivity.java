package com.example.unibook;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyEventsActivity extends BasePrivateActivity {
	private ArrayAdapter<Event> adpMyEvents;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_events);
		setUpViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
		refreshViews();
	}

	private void setUpViews() {
		ListView lstMyEvents = (ListView) findViewById(R.id.lst_my_events);
		adpMyEvents = new ArrayAdapter<Event>(this, 0) {
			private LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final Event event = getItem(position);
				final View view;
				if (convertView == null) {
					view = mInflater.inflate(R.layout.view_my_events, parent, false);
				} else {
					view = convertView;
				}
				TextView txtName = (TextView) view.findViewById(R.id.txt_my_event);
				txtName.setText(event.getName());
				//Picasso.with(ProductsActivity.this).load(product.getImageUrl()).into(imgImage);


				
				// like and unlike
				
		
				

				return view;
			}
		};
		lstMyEvents.setAdapter(adpMyEvents);
	}
	
	protected void refreshViews() {
		super.refreshViews();
		adpMyEvents.clear();
		Intent intent = getIntent();
		Long id = intent.getLongExtra("_id", 0);
		String idd = id+"";
		startProgress();
		ApiRouter.withoutToken().getMyEvents(Integer.parseInt(idd), new Callback<List<Event>>() {
		public void success(List<Event> events, Response response) {
		adpMyEvents.addAll(events);
		stopProgress();
		}
		@Override
		public void failure(RetrofitError e) {
		displayError(e);
		}
		});
		}
	
}
