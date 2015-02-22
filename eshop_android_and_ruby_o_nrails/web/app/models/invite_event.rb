class InviteEvent < ActiveRecord::Base
	# Associations
	belongs_to :sender_id, :class_name => 'User', :foreign_key =>'user1_id'
	belongs_to :recipient_id, :class_name => 'User', :foreign_key =>'user2_id'
	# belongs_to :event_id, :class_name => 'Event', :foreign_key =>'event_id'
	# Validations
	validates :user1_id, presence: true
	validates :user2_id, presence: true
	validates :event_id, presence: true
end
