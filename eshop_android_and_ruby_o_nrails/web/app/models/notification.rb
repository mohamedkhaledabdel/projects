class Notifications < ActiveRecord::Base
	validates :title, presence: true
	validates :short_desc, presence: true
	validates :pending, inclusion: [true, false]
	belongs_to :user, foreign_key: 'assigned_to'

 	# Marks a notification as read.
	def mark_read
	    self[:pending] = false
	end

	# Marks a notification as unread
	def mark_unread
	    self[:pending] = true
  	end
end
