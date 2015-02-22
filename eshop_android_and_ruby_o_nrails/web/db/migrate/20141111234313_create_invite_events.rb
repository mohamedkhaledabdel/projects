class CreateInviteEvents < ActiveRecord::Migration
  def change
    create_table :invite_events do |t|
    	t.integer :user1_id
    	t.integer :user2_id
    	t.integer :event_id
    	t.string :status
      	t.timestamps
    end
    add_index(:invite_events, [:user1_id, :user2_id], unique:  true)
  end
end
