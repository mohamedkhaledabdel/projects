package com.example.unibook;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.os.Bundle;
import android.util.EventLogTags.Description;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEventActivity extends BasePrivateActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_events);
		setupViews();
	}
	public void setupViews() {
		Button btnView = (Button) findViewById(R.id.btn_create_event_submit);
		btnView.setEnabled(true);
		final EditText name = (EditText) findViewById(R.id.txt_create_event_name);
		final EditText location = (EditText) findViewById(R.id.txt_create_event_location);
		final EditText description = (EditText) findViewById(R.id.txt_create_event_description);
		btnView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startProgress();
			ApiRouter.withoutToken().createEvent((int)getCurrentUser().getId(),
					name.getText().toString(), description.getText().toString(),
					location.getText().toString(), new Callback<Event>(){
				@Override
				public void success(Event event, Response
				rawResponse) {
					event.setDescription(description.getText().toString());
					event.setName(name.getText().toString());
					event.setLocation(location.getText().toString());
					event.setUserId((int)getCurrentUser().getId());
					
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
