package com.example.unibook;

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



public class AllEventsActivity extends BasePrivateActivity {
	private ArrayAdapter<Event> adpEvents;
	Class <?> referrer;
	public final static String EXTRA_MESSAGE = "com.example.unibook.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_events);
		setUpViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
		refreshViews();
	}

	private void setUpViews() {
		ListView lstEvents = (ListView) findViewById(R.id.lst_events);
		adpEvents = new ArrayAdapter<Event>(this, 0) {
			private LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final Event event= getItem(position);

				final View view;
				if (convertView == null) {
					view = mInflater.inflate(R.layout.view_event, parent, false);
				} else {
					view = convertView;
				}
				TextView txtName = (TextView) view.findViewById(R.id.txt_name);
				txtName.setText(event.getName());
				
				//Picasso.with(ProductsActivity.this).load(product.getImageUrl()).into(imgImage);

				Button btnView = (Button) view.findViewById(R.id.btn_open_event);
				btnView.setEnabled(true);
				btnView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					startProgress();
					ApiRouter.withoutToken().viewEvent(event.getId(), new Callback<Event> () {
						@Override
						public void success(Event response, Response
						rawResponse) {
							Intent intent = new Intent(AllEventsActivity.this, EventsActivity.class);
							String[] value = {event.getName(), event.getDescription(), event.getLocation(), 
									event.getId() + ""};
							intent.putExtra(EXTRA_MESSAGE, value);
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
		
		lstEvents.setAdapter(adpEvents);

	}
			

	protected void refreshViews() {
		super.refreshViews();
		adpEvents.clear();
		startProgress();
		ApiRouter.withoutToken().getEvents(new Callback<List<Event>>() {
		public void success(List<Event> events, Response response) {
		adpEvents.addAll(events);
		stopProgress();
		}
		@Override
		public void failure(RetrofitError e) {
		displayError(e);
		}
		});
		}
	
	
}
