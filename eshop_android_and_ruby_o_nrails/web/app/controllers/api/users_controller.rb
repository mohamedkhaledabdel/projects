class Api::UsersController < Api::BaseController

	def index
    	respond_with @users = User.all
  	end

	def show
	    respond_with @user = User.find(params[:id])
  	end

  	def following
	    @user  = User.find(params[:id])
	    respond_with @users = @user.following
    end

    def followers
	    @user  = User.find(params[:id])
	    respond_with @users = @user.followers
    end
	
	def update
  		@user = User.find_by(id: params[:id])
		respond_with @updated = @user.update(first_name: params[:user][:first_name], 
			last_name: params[:user][:last_name], 
			email: params[:user][:email], 
			location: params[:user][:location]), location: nil		
	end
end	