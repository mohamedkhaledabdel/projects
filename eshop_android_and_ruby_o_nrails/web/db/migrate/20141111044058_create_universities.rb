class CreateUniversities < ActiveRecord::Migration
  def change
    create_table :universities do |t|
      t.string :name, default: "", null: false
      t.integer :user_id, null: false
      t.timestamps
    end
  end
end
