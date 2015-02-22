package com.example.unibook;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface PublicApiRoutes {

	@FormUrlEncoded
	@POST("/sessions")
	void login(@Field("session[email]") String email,
		@Field("session[password]") String password,
		Callback<User> callback);
	@FormUrlEncoded
	@POST("/events/add_post/{events_id}/{user_id}")
	void addPost(@Path("events_id") int id, @Path("user_id") int userID, @Field("event_post[text]") String 
			Post , Callback <Post> callback);
	@FormUrlEncoded
	@POST("/events/add_comment/{events_id}/{post_id}/{user_id}")
	void addComment(@Path("events_id") int id, @Path("post_id") int userID, 
			@Path("user_id") int postID, @Field("event_comment[text]") String 
			Comment , Callback <EventComment> callback);
	@FormUrlEncoded
	@POST("/events/like")
	void likePost(@Field("post_like[post_id]") int postId, @Field("post_like[user_id]") int userId
			, Callback <PostLike> callback);
	
	@DELETE("/events/unlike/{post_id}/{user_id}")
	void unlikePost(@Path("post_id") int postId, @Path("user_id") int userId
			, Callback <Response> callback);
	@FormUrlEncoded
	@PUT("/users/{user_id}")
	void updateSettings(@Path("user_id") long userId,@Field("user[first_name]") String firstName, 
			@Field("user[last_name]") String lastName, @Field("user[email]") String email, 
			@Field("user[location]") String location, Callback <Response> callback);
	@FormUrlEncoded
	@POST("/events/create")
	void createEvent(@Field("event[id]") int userId, @Field("event[name]") String name, 
			@Field("event[description]") String desc, @Field("event[location]") String location
			, Callback <Event> callback);
	@GET("/events")
	void getEvents(Callback<List<Event>> callback);
	@GET("/events/friend_event/{user_id}")
	void getMyEvents(@Path("user_id") int userId,Callback<List<Event>> callback);
	@GET("/events/{events_id}")
	void viewEvent(@Path("events_id") int id, Callback<Event> callback);
	@GET("/events/show_post/{event_id}")
	void viewPosts(@Path("event_id") int id, Callback<List<Post>> callback);
	@GET("/users/{user_id}/followers")
	void getFollowers( @Path("user_id") long id, Callback<List<User>> callback);
	@GET("/users/{user_id}/following")
	void getFollowings( @Path("user_id") long id, Callback<List<User>> callback);
	@GET("/users/{user_id}")
	void showUser( @Path("user_id") long id, Callback<User> callback);
	@GET("/events/show_comment/{event_id}/{post_id}")
	void getEventComments(@Path("event_id") int eventId,@Path("post_id") int postId,
			Callback<List<EventComment>> callback);
}
