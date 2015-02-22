class UserComment < ActiveRecord::Base
	#Assoc.
	belongs_to :post, :class_name => 'UserPost', :foreign_key =>'post_id'
	belongs_to :user, :class_name => 'User', :foreign_key =>'user_id'

	#Valid.
	validates :user_id, presence: true
	validates :post_id, presence: true
	validates :text, presence: true
	
	#Some methods to be used in the controller

	# to add a specific comment on a specific post, we need the current user and the post commented on
	def add_comment(user, post, text)
		UserComment.create(text: text,post_id: post,user_id: user)
	end

	# to view a specific comment on a specific post, we need the current user and the post commented on
	def view_comment(user, post)
		comment = UserComment.where(user_id: user, post_id: post)
	end
end
