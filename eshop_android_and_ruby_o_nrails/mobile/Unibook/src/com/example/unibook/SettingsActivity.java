package com.example.unibook;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;

public class SettingsActivity extends BasePrivateActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		setupViews();
	}


	
	public void setupViews() {
		Button btnView = (Button) findViewById(R.id.btn_submit_settings);
		btnView.setEnabled(true);
		final EditText firstName = (EditText) findViewById(R.id.txt_settings_first_name);
		final EditText lastName = (EditText) findViewById(R.id.txt_settings_last_name);
		final EditText location = (EditText) findViewById(R.id.txt_settings_location);
		final EditText email = (EditText) findViewById(R.id.txt_settings_email);
		btnView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			ApiRouter.withoutToken().updateSettings(getCurrentUser().getId(),
					firstName.getText().toString(), lastName.getText().toString(),
					email.getText().toString(), location.getText().toString(), 
					new Callback<Response>(){
				@Override
				public void success(Response response, Response
				rawResponse) {
					getCurrentUser().setEmail(email.getText().toString());
					getCurrentUser().setFirstName(firstName.getText().toString());
					getCurrentUser().setLastName(lastName.getText().toString());
					getCurrentUser().setLocation(location.getText().toString());
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
