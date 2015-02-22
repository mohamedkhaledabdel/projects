class CreateUserComments < ActiveRecord::Migration
  def change
    create_table :user_comments do |t|
      t.string :text, null: false
      t.integer :post_id, null: false
      t.integer :user_id , null: false
      t.timestamps
    end
  end
end
