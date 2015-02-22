Unibook::Application.routes.draw do
  devise_for :users, :controllers => {:omniauth_callbacks => 'omniauth_callbacks' }
  resources :users, :only => [:index, :show]
  resources :users do
    member do
      get :following, :followers
    end
  end

  resources :user_relationships,       only: [:create, :destroy]
  resources :events
  resources :universities
  resources :invite_events
   
  namespace :api, defaults: { format: :json } do

    resources :sessions, only: :create
    get 'sessions/create' => 'sessions#create'
    get 'events/show_comment/:id/:post_id' => 'events#show_comment'
    get 'events/show_post/:id' => 'events#show_post'
    get 'events/show_joined_users/:id' => 'events#show_joined_users'
    get 'events/show_not_joined_users/:id' => 'events#show_not_joined_users'
    get 'events/view_my_events/:user_id' => 'events#view_my_events'    
    post 'events/add_post/:id/:user_id' => 'events#add_post'
    post 'events/like' => 'events#like'
    get 'events/friend_event/:user_id' => 'events#friends_events'
    delete 'events/unlike/:post_id/:user_id' => 'events#unlike'
    post 'events/add_comment/:id/:post_id/:user_id' => 'events#add_comment'
    put 'users/:id' => 'users#update'
    post 'events/create' => 'events#create'
    resources :events
    resources :universities
    resources :users, :only => [:index, :show]
    resources :users do
      member do
        get :following, :followers
      end
    end  
    #post 'events/add_post/:id/:user_id' => 'events#add_post'
    #post 'events/add_comment/:id/:user_id/:post_id' => 'events#add_comment' 
  end



  root 'users#index'
  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  # You can have the root of your site routed with "root"
  # root 'welcome#index'
 
  # Example of regular route:
  #   get 'product/index' => 'event#index'
# get 'universities/index' => 'universities#index'
# get 'universities/show/:id' => 'universities#show'
# post 'universities/delete/:id' => 'universities#delete'

# get 'event/index' => 'event#index'
# get 'event/show/:id' => 'event#show'
# post 'event/delete/:id' => 'event#delete'
  post 'events/add_post/:id/:user_id' => 'events#add_post'
  post 'events/add_comment/:id/:user_id/:post_id' => 'events#add_comment'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
end
