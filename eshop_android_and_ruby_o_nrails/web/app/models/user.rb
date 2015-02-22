class User < ActiveRecord::Base
    # Include default devise modules. Others available are:
    # :confirmable, :lockable, :timeoutable and :omniauthable
    devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
    devise :omniauthable, :omniauth_providers => [:facebook]

    # Avatar
    has_attached_file :avatar, :styles => { :medium => "300x300>", :thumb => "100x100#" , :icon => "50x50#"}, 
        :url  => "/assets/users/:id/:style/:basename.:extension",
        :default_url => "/images/:style/missing.png"
    validates_attachment_content_type :avatar, :content_type => /\Aimage\/.*\Z/

    public
    # Validations: 
    validates :first_name, presence: true, length: { maximum: 256 }
    validates :last_name, presence: true, length: { maximum: 256 }
    
    # Associations:
    after_initialize  :cap_names
    after_create :cap_names
    has_many :active_relationships, class_name:  "UserRelationship",
                                  foreign_key: "follower_id",
                                  dependent:   :destroy
    has_many :passive_relationships, class_name:  "UserRelationship",
                                   foreign_key: "followed_id",
                                   dependent:   :destroy                              
    has_many :following, through: :active_relationships, source: :followed
    has_many :followers, through: :passive_relationships, source: :follower
    has_many :active_invitations, :class_name => "InviteEvent", 
                                :foreign_key => 'sender_id' ,
                                dependent:   :destroy 
    has_many :passive_invitations, :class_name => "InviteEvent", 
                                :foreign_key => 'recipient_id',
                                dependent:   :destroy
    has_many :events_invited, through: :passive_invitations, source: :event_id
    has_many :events
    has_many :user_comments
    has_many :user_posts
    has_many :event_comments
    has_many :event_posts 

    # has_many :notifications , class_name:  "Notifications",
    #                               foreign_key: 'assigned_to',
    #                               dependent:   :destroy
     
    # Callbacks:                                                          
    before_create -> { self.token = SecureRandom.hex }, unless: :token?
    
    # Capitalize all the names
    def cap_names
        self[:first_name].capitalize if self[:first_name] != nil
        self[:last_name].capitalize if self[:last_name] != nil
    end

    # Authenticate from facebook
	def self.find_for_facebook_oauth(auth, signed_in_resource=nil)
	    user = User.where(:provider => auth.provider, :uid => auth.uid).first
	    if user
	        return user
	    else
	        registered_user = User.where(:email => auth.info.email).first
	        if registered_user
	            return registered_user
	        else
	            user = User.create!(first_name:auth.extra.raw_info.first_name,
                    avatar:process_uri(auth.info.image),
                    last_name:auth.extra.raw_info.last_name,
                    location:auth.info.location,
                    is_female:auth.extra.raw_info.gender.to_sym,
                    date_of_birth:auth.extra.raw_info.birthday,
                    provider:auth.provider,
                    uid:auth.uid,
                    email:auth.info.email,
                    password:Devise.friendly_token[0,20])
	        end    
	  	end
  	end

    def self.process_uri(uri)
       uri = uri + "?type=large" 
       avatar_url = URI.parse(uri)
       avatar_url.scheme = 'https'
       avatar_url.to_s
    end
    
    def self.authenticate(email, password)
        if email.present? && password.present?
            if user = User.find_by(email: email)
                if user.valid_password?(password)
                    user
                end
            end 
        end 
    end
    
    # Follows a user
    def follow(other_user)
        active_relationships.create(followed_id: other_user.id)
    end

    # Unfollows a user.
    def unfollow(other_user)
        active_relationships.find_by(followed_id: other_user.id).destroy
    end

    # Returns true if the current user is following the other user.
    def following?(other_user)
        following.include?(other_user)
    end

    # Invite a user
    def invite(other_user, event_id)
        active_invitations.create(recipient_id: other_user.id, event_id: event_id, status: 'pending')
    end


    def join(other_user, event_id)
        passive_invitations.find_by(recipient_id: other_user.id, event_id: event_id, status: 'pending').update(status: 'going')
    end

    def events_invited(event_id)
        passive_invitations.find_by(event_id: event_id)
    end

    # Uninvite a user.
    def uninvite(other_user)
        active_relationships.find_by(recipient_id: other_user.id, event_id: event_id).destroy
    end

    # Returns true if the current user is already invited to event.
    def invited?(other_user)
        events_invited.include?(recipient_id: other_user.id, event_id: event_id)
    end  

    # Retrieves Pending notifications
    def pending_notifications
        Notification.where(assigned_to: self.id, pending: true )
    end        

end
