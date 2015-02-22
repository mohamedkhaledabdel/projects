class InviteEventsController < ApplicationController
 	before_action :set_invite_event, only: [:show, :edit, :update, :destroy]
	
	# GET /invite_events
  	# GET /invite_events.json
	def index
		@invite_events = InviteEvent.all
	end
	
	# GET /invite_events/1
	# GET /invite_events/1.json
	def show
	end

	# GET /invite_events/new
  	def new
    	@invite_event = InviteEvent.new
  	end

  	# GET /invite_events/1/edit
  	def edit
  	end

  	# POST /invite_events
  	# POST /invite_events.json
  	def create
    	@invite_event = InviteEvent.new(invite_event_params)
    	respond_to do |format|
      		if @invite_event.save
        		format.html { redirect_to :back, notice: 'User was successfully invited.' }
        		format.json { redirect_to :back, status: :created }
      		else
        		format.html { render action: 'new' }
        		format.json { render json: @invite_event.errors, status: :unprocessable_entity }
      		end
    	end
  	end

  	# PATCH/PUT /invite_events/1
  	# PATCH/PUT /invite_events/1.json
  	def update
    	respond_to do |format|
      		if @invite_event.update(invite_event_params)
		        format.html { redirect_to @invite_event, notice: 'invite_event was successfully updated.' }
		        format.json { head :no_content }
	      	else
		        format.html { render action: 'edit' }
		        format.json { render json: @invite_event.errors, status: :unprocessable_entity }
	      	end
   	 	end
  	end

  	# DELETE /invite_events/1
  	# DELETE /invite_events/1.json
  	def destroy
    	@invite_event.destroy
    	respond_to do |format|
      		format.html { redirect_to invite_events_url }
      		format.json { head :no_content }
    	end
  	end
	
  private
    # Use callbacks to share common setup or constraints between actions.
    def set_invite_event
      	@invite_event = InviteEvent.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def invite_event_params
      	params.require(:invite_event).permit(:user1_id, :user2_id, :event_id, :status)
    end
end





  
