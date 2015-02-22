class UserPost < ActiveRecord::Base
	#Assoc.
	belongs_to :user, :class_name => 'User', :foreign_key =>'user1_id'
	belongs_to :friend, :class_name => 'User', :foreign_key =>'user2_id'
	has_one :user, :class_name => 'User', :foreign_key =>'user1_id'
	has_many :comments ,:class_name => 'UserComment'

	#Valid.
	validates :user1_id, presence: true
	validates :user2_id, presence: true
	validates :text, presence: true


	#Methods to use in controller.....

	#to create a post we have to get the current user, and the profile he is viewing
	def create_post(user, friend, text)
		UserPost.create(text: text,user1_id: user, user2_id: friend)
	end

	#to view a post we have to get the current user, and the profile he is viewing
	def view(user, friend)
		post = UserPost.where(user1_id: user, user2_id: friend)
	end
	
end
