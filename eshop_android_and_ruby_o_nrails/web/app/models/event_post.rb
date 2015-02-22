class EventPost < ActiveRecord::Base
	belongs_to :event, :class_name => 'Event', :foreign_key =>'event_id'
	belongs_to :user, :class_name => 'User', :foreign_key =>'user_id'
	has_many :comments, :class_name => 'EventComment', :dependent => :delete_all

	#Valid.
	validates :user_id, presence: true
	validates :event_id, presence: true
	validates :text, presence: true


	def create_post(event, user, text)
		post = EventPost.create(event_id: event, user_id: user, text: text)
	end

	def view_post(user, event)
		post = EventPost.where(user_id: user, event_id: event)
	end

	def view_user
		user = User.find(self.user_id)
	end
end
