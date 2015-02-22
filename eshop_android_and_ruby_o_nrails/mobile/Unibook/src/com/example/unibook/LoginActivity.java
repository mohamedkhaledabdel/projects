package com.example.unibook;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends BaseActivity {
	private static final String EXTRA_REFERRER = "EXTRA_REFERRER";

	private Class<?> referrer;
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Intent intent = getIntent();
		if (intent != null && intent.hasExtra(EXTRA_REFERRER)) {
			String referrerString = intent.getStringExtra(EXTRA_REFERRER);
			if (referrerString != null) {
				try {
					referrer = Class.forName(referrerString);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				EditText txtEmail = (EditText) findViewById(R.id.txt_email);
				String email = txtEmail.getText().toString();

				EditText txtPassword = (EditText) findViewById(R.id.txt_password);
				String password = txtPassword.getText().toString();

				Log.d(getPackageName(), "Login with '" + email + "' and '" + password + "'");
				
				ApiRouter.withoutToken().login(email, password, new Callback<User>() {
					@Override
					public void success(User user, Response response) {
						setCurrentUser(user);
						stopProgress();
						referrer = ProfileActivity.class;

						if (referrer != null && getCurrentUser().getEmail() != null) {
							Intent intent = new Intent(LoginActivity.this, referrer);
							
							
							startActivity(intent);

							finish();
						}
					}
					@Override
					public void failure(RetrofitError e) {
						displayError(e);
						btnLogin.setEnabled(true);
					}
					});
				
			}
		});
	}

	@Override
	protected boolean isRefreshable() {
		return false;
	}
}