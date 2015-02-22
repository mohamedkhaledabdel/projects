class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.integer :user_id, null: false
      t.string :name, default: "", null: false
      t.datetime :date
      t.string :location, default: "", null: false
      t.string :description, default: "", null: false
      t.timestamps
    end
  end
end
