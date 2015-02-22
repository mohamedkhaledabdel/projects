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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AllEventsCommentsActivity extends BasePrivateActivity{
	private ArrayAdapter<EventComment> adpComments;
	Class <?> referrer;
	int postID = 0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_events_comments);
		setUpViews();
	}
	@Override
	protected void onResume() {
		super.onResume();
		refreshViews();
	}
	
	private void setUpViews() {
		ListView lstEventsComments = (ListView) findViewById(R.id.lst_events_comments);
		adpComments = new ArrayAdapter<EventComment>(this, 0) {
			private LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final EventComment comment= getItem(position);

				final View view;
				if (convertView == null) {
					view = mInflater.inflate(R.layout.view_comment, parent, false);
				} else {
					view = convertView;
				}
				TextView txtName = (TextView) view.findViewById(R.id.comment_txt_name);
				txtName.setText(comment.getText());
				final EditText com = (EditText) findViewById(R.id.txt_event_comment);

				//Picasso.with(ProductsActivity.this).load(product.getImageUrl()).into(imgImage);

				Button btnView = (Button) findViewById(R.id.btn_add_comment);
				btnView.setEnabled(true);
				btnView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					startProgress();
					ApiRouter.withoutToken().addComment(2, 5, (int)getCurrentUser().id, 
							com.getText().toString(), new Callback<EventComment> () {
						@Override
						public void success(EventComment comment, Response
						rawResponse) {
							comment.setEventID(2);
							comment.setPostID(1);
							comment.setText(com.getText().toString());
							comment.setUserID((int)getCurrentUser().id);
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
		
		lstEventsComments.setAdapter(adpComments);

	}
	protected void refreshViews() {
		super.refreshViews();
		adpComments.clear();
		startProgress();
		ApiRouter.withoutToken().getEventComments(2, 5, new Callback<List<EventComment>>() {
		public void success(List<EventComment> comments, Response response) {
		adpComments.addAll(comments);
		stopProgress();
		}
		@Override
		public void failure(RetrofitError e) {
		displayError(e);
		}
		});
		}
		
}
