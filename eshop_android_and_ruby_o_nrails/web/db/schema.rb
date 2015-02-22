# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20141228224707) do

  create_table "event_comments", force: true do |t|
    t.integer  "event_id",                null: false
    t.integer  "post_id",                 null: false
    t.integer  "user_id",                 null: false
    t.string   "text",       default: "", null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "event_posts", force: true do |t|
    t.integer  "event_id",                null: false
    t.string   "user_id",                 null: false
    t.string   "text",       default: "", null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "events", force: true do |t|
    t.integer  "user_id",                         null: false
    t.string   "name",               default: "", null: false
    t.datetime "date"
    t.string   "location",           default: "", null: false
    t.string   "description",        default: "", null: false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "image_file_name"
    t.string   "image_content_type"
    t.integer  "image_file_size"
    t.datetime "image_updated_at"
  end

  create_table "invite_events", force: true do |t|
    t.integer  "user1_id"
    t.integer  "user2_id"
    t.integer  "event_id"
    t.string   "status"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "invite_events", ["user1_id", "user2_id"], name: "index_invite_events_on_user1_id_and_user2_id", unique: true, using: :btree

  create_table "notifications", force: true do |t|
    t.string   "assigned_to"
    t.string   "title"
    t.string   "short_desc"
    t.string   "long_desc"
    t.string   "embedded_view_url"
    t.boolean  "pending"
    t.string   "data"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "notifications", ["assigned_to"], name: "index_notifications_on_assigned_to", using: :btree

  create_table "post_likes", force: true do |t|
    t.integer  "post_id",    default: 0, null: false
    t.integer  "user_id",                null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "universities", force: true do |t|
    t.string   "name",               default: "", null: false
    t.integer  "user_id",                         null: false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "image_file_name"
    t.string   "image_content_type"
    t.integer  "image_file_size"
    t.datetime "image_updated_at"
  end

  create_table "user_comments", force: true do |t|
    t.string   "text",       null: false
    t.integer  "post_id",    null: false
    t.integer  "user_id",    null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "user_posts", force: true do |t|
    t.string   "text",       default: ""
    t.integer  "user1_id",                null: false
    t.integer  "user2_id",                null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "user_relationships", force: true do |t|
    t.integer  "follower_id"
    t.integer  "followed_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "user_relationships", ["followed_id"], name: "index_user_relationships_on_followed_id", using: :btree
  add_index "user_relationships", ["follower_id", "followed_id"], name: "index_user_relationships_on_follower_id_and_followed_id", unique: true, using: :btree
  add_index "user_relationships", ["follower_id"], name: "index_user_relationships_on_follower_id", using: :btree

  create_table "users", force: true do |t|
    t.string   "first_name",             default: "",    null: false
    t.string   "last_name",              default: "",    null: false
    t.string   "location",               default: ""
    t.datetime "date_of_birth"
    t.boolean  "is_female",              default: false
    t.string   "email",                  default: "",    null: false
    t.string   "encrypted_password",     default: "",    null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,     null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.string   "provider"
    t.string   "uid"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "avatar_file_name"
    t.string   "avatar_content_type"
    t.integer  "avatar_file_size"
    t.datetime "avatar_updated_at"
    t.string   "token"
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true, using: :btree
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true, using: :btree

end
