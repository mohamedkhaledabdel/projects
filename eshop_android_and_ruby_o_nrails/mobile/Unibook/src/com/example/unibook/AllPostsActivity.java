package com.example.unibook;

import java.util.List;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.R.integer;
import android.app.Activity;
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

public class AllPostsActivity extends BasePrivateActivity {
	private ArrayAdapter<Post> adpPost;
	Class <?> referrer;
	int eventId;
	String [] y;
	static boolean checked = false;
	public final static String EXTRA_MESSAGE = "com.example.unibook.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_posts);
		Intent intent = getIntent();
		y = new String [1];
		y = intent.getStringArrayExtra(EventsActivity.message);
		eventId = 1;
		setUpViews();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		refreshViews();
	}

	private void setUpViews() {
		ListView lstPost = (ListView) findViewById(R.id.lst_posts);
		adpPost = new ArrayAdapter<Post>(this, 0) {
			private LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final Post post = getItem(position);
				final View view;
				if (convertView == null) {
					view = mInflater.inflate(R.layout.view_post, parent, false);
				} else {
					view = convertView;
				}
				TextView txtName = (TextView) view.findViewById(R.id.post_txt_name);
				txtName.setText(post.getText());
				//Picasso.with(ProductsActivity.this).load(product.getImageUrl()).into(imgImage);

				Button btnView = (Button) view.findViewById(R.id.btn_open_post_comments);
				btnView.setEnabled(true);
				eventId = post.getEvent_id();
				btnView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					ApiRouter.withoutToken().getEventComments(eventId, post.getId() ,
							new Callback<List<EventComment>>(){
						@Override
						public void success(List <EventComment> comment, Response
						rawResponse) {
							Intent intent = new Intent(AllPostsActivity.this, AllEventsCommentsActivity.class);
							String[] value = {post.getId() + "", post.getEvent_id() + ""};
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
				// like and unlike
				final CheckBox likeUnlike = (CheckBox) view.findViewById(R.id.checkbox_like_event);
				if(likeUnlike.isChecked() == false) {
				likeUnlike.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					ApiRouter.withoutToken().likePost(post.getId() ,(int) getCurrentUser().getId()
							,new Callback<PostLike>(){
						@Override
						public void success(PostLike like, Response
						rawResponse) {
								like.setPostId(post.getId());
								like.setUserId((int)getCurrentUser().getId());
								checked = true;
								}
						@Override
						public void failure(RetrofitError e) {
						displayError(e);
						}
						});
						}
					});
				}

				if(checked ==  true) {
						likeUnlike.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
							ApiRouter.withoutToken().unlikePost(post.getId() ,(int) getCurrentUser().getId()
									,new Callback<Response>(){
								@Override
								public void success(Response response, Response
								rawResponse) {
									Toast.makeText(AllPostsActivity.this, "You unliked" +
											post.getText(),
											Toast.LENGTH_LONG).show();
										}
								@Override
								public void failure(RetrofitError e) {
								displayError(e);
								}
								});
								}
							});
				}

				return view;
			}
		};
		lstPost.setAdapter(adpPost);
	}
			

	protected void refreshViews() {
		super.refreshViews();
		adpPost.clear();
		startProgress();
		ApiRouter.withoutToken().viewPosts(eventId, new Callback<List<Post>>() {
		public void success(List<Post> posts, Response response) {
		adpPost.addAll(posts);
		stopProgress();
		}
		@Override
		public void failure(RetrofitError e) {
		displayError(e);
		}
		});
		}
	
}
