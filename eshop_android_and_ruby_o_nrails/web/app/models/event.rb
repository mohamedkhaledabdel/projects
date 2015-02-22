class Event < ActiveRecord::Base
	belongs_to :admin, :class_name => 'User', :foreign_key =>'user_id'
	
	# Image
	has_attached_file :image, :styles => { :medium => "300x300>", :thumb => "100x100#" , :icon => "50x50#"}, 
	:url  => "/assets/events/:id/:style/:basename.:extension",
	:default_url => "/images/:style/missing.png"
    validates_attachment_content_type :image, :content_type => /\Aimage\/.*\Z/
    
	# Associations:
	# has_many :invitation, class_name:  "InviteEvent",
	# 					foreign_key: "event_id",
	# 					dependent:   :destroy	
	has_many :users_going, through: :users, 
						source: :recipient_id, 
						:conditions => "status = 'going'",
						:order => :created_at		
	has_many :users_invited, through: :users, 
						source: :recipient_id, 
						:conditions => "status = 'pending'",
						:order => :created_at
	# Validations:
	validates :user_id, presence: true
	validates :name, presence: true
	validates :description, presence: true
	#validates :image, presence: true

	#Methods to be used in the controller

	#Method used to create an event ** We need to add the date attribute **
	def create_event(user, name, location, description)
		event = Event.create(user_id: user, name: name, location: location, description: description)
	end

	#Method used to view and event
	def view_event(user, event) 
	end
end
