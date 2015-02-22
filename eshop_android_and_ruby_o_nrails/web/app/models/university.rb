class University < ActiveRecord::Base

	# Image
	has_attached_file :image, :styles => { :medium => "300x300>", :thumb => "100x100#" , :icon => "50x50#"}, :default_url => "/images/:style/missing.png"
    validates_attachment_content_type :image, :content_type => /\Aimage\/.*\Z/
   
    # Validations:
	belongs_to :user, dependent: :destroy
	validates :name, presence: true
	validates :user_id, presence: true

	# Associations:
	has_many :events
	has_many :event_posts
	has_many :event_comments
end
