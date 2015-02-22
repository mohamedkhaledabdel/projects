class CreateEventPosts < ActiveRecord::Migration
  def change
    create_table :event_posts do |t|
      t.integer :event_id, null: false
      t.string :user_id, null: false
      t.string :text, default: "", null: false

      t.timestamps
    end
  end
end
