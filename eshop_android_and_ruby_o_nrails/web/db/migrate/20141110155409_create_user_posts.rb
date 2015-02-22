class CreateUserPosts < ActiveRecord::Migration
  def change
    create_table :user_posts do |t|
      t.string :text,  default: ""
      t.integer :user1_id,  null: false
      t.integer :user2_id,  null: false
      t.timestamps
    end
  end
end
