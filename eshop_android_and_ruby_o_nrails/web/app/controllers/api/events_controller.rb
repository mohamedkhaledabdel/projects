class Api::EventsController < Api::BaseController
	#before_action :authenticate_user!
	#Get
	def index
		respond_with @allEvents = Event.all
	end 
	
	# GET /events/1
	# GET /events/1.json
	def show
	   @postAdd = EventPost.new
    respond_with @event = Event.find(params[:id])
    @post = EventPost.where(event_id: params[:id])  
    #@comment = EventComment.where(event_id: params[:id], post_id: params[:post_id])
    @commentAdd = EventComment.new
    @invited = InviteEvent.where(event_id: params[:id])
    @going = InviteEvent.where(event_id: params[:id], status: 'going')
    @users = User.all
	end

  def show_post
    respond_with @post = EventPost.where(event_id: params[:id])
  end

  def show_comment
    respond_with @comment = EventComment.where(event_id: params[:id], post_id: params[:post_id])
  end
	# GET /events/new
  	def new
    	@event = Event.new
  	end

  	# GET /events/1/edit
  	def edit
  	end

  	# POST /events
  	# POST /events.json
  	def create
    	@event = Event.new(event_params)
      @event.user_id = current_user.id
    	respond_to do |format|
      		if @event.save
        		format.html { redirect_to @event, notice: 'Event was successfully created.' }
        		format.json { render action: 'show', status: :created, location: @event }
      		else
        		format.html { render action: 'new' }
        		format.json { render json: @event.errors, status: :unprocessable_entity }
      		end
    	end
  	end

  	# PATCH/PUT /events/1
  	# PATCH/PUT /events/1.json
  	def update
    	respond_to do |format|
      		if @event.update(event_params)
		        format.html { redirect_to @event, notice: 'Event was successfully updated.' }
		        format.json { head :no_content }
	      	else
		        format.html { render action: 'edit' }
		        format.json { render json: @event.errors, status: :unprocessable_entity }
	      	end
   	 	end
  	end

  	# DELETE /events/1
  	# DELETE /events/1.json
  	def destroy
    	@event.destroy
    	respond_to do |format|
      		format.html { redirect_to events_url }
      		format.json { head :no_content }
    	end
  	end

  	def add_post
    		respond_with @postAdd = EventPost.create(event_id: params[:id],
    			user_id: params[:user_id],text: params[:event_post][:text]), location: nil		
    		#redirect_to :action => "show", :id => params[:id]
	  end

	  def add_comment
    		respond_with @commentAdd = EventComment.create(event_id: params[:id], 
    			post_id: params[:post_id], user_id: params[:user_id],
    			text: params[:event_comment][:text]), location: nil
    		#redirect_to :action => "show", :id => params[:id], :post_id => params[:post_id]
	 end

  def show_joined_users
    @joinedUsers = Event.where(id: params[:id])
    @joinedUsers.each do |user|
      respond_with @joinedUser = User.find_by(user.user_id)
    end  
  end

  def show_not_joined_users
    @users = User.all
    @users.each do |user|
      if(Event.find_by(id: params[:id], user_id: user.id) == nil)
        respond_with @unJoinedUser = User.find(user.id)
      end
    end
  end

  def like
    respond_with @like = PostLike.create(post_id: params[:post_like][:post_id],
      user_id: params[:post_like][:user_id]), location: nil
  end

  def unlike
    @unlike = PostLike.find_by(post_id: params[:post_id],
      user_id: params[:user_id])
    respond_with @unlike.destroy
  end
  def create
      respond_with @event = Event.create(user_id: params[:event][:id], name: params[:event][:name], 
          description: params[:event][:description], 
          location: params[:event][:location]), location: nil
    end

  def view_my_events
    respond_with @event = Event.where(user_id: params[:user_id])
  end  

  def friends_events
    respond_with @event = Event.where(user_id: params[:user_id])
  end  
	
  private
    # Use callbacks to share common setup or constraints between actions.
    def set_event
      	@event = Event.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def event_params
      	params.require(:event).permit(:location, :name, :date, :description, :image)
    end
end	