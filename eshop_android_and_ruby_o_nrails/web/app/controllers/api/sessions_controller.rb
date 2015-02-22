class Api::SessionsController < Api::BaseController
	
	def create
		respond_with @user = User.authenticate(*session_params.values_at(:email,
		:password))
	end

	protected
	def session_params
		params.require(:session).permit(:email, :password)
	end
end