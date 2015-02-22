class CreateEventComments < ActiveRecord::Migration
  def change
    create_table :event_comments do |t|
      t.integer :event_id, null: false
      t.integer :post_id, null: false
      t.integer :user_id, null: false
      t.string :text, default: "", null: false

      t.timestamps
    end
  end
end
