class EventComment < ActiveRecord::Base
belongs_to :user, :class_name => 'User', :foreign_key =>'user_id'
belongs_to :post, :class_name => 'EventPost', :foreign_key =>'post_id'

	#Valid.
	validates :user_id, presence: true
	validates :event_id, presence: true
	validates :post_id, presence: true
	validates :text, presence: true
	
	def create_comment(event, post, user, text)
		comment = EventComment.create(event_id: event, post_id: post, user_id: user, text: text)
	end

	def view_comment(event, post, user)
		comment = EventComment.where(event_id: event, post_id: post, user_id: user)
	end

	def view_user
		user = User.find(self.user_id)
	end

	def view_comment
		comment = EventComment.where(self.post_id)
	end
end

