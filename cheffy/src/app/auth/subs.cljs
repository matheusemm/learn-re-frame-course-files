(ns app.auth.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :uid
  (fn [db _]
    (get-in db [:auth :uid])))

(rf/reg-sub
  :users
  (fn [db _]
    (:users db)))

(rf/reg-sub
  :logged-in?
  :<- [:uid]
  (fn [uid _]
    (boolean uid)))

(rf/reg-sub
  :user-profile
  :<- [:uid]
  :<- [:users]
  (fn [[uid users] _]
    (get-in users [uid :profile])))

(rf/reg-sub
  :user
  :<- [:uid]
  :<- [:users]
  (fn [[uid users] _]
    (get users uid)))

(rf/reg-sub
  :chef?
  :<- [:uid]
  :<- [:users]
  (fn [[uid users] _]
    (= (get-in users [uid :role]) :chef)))

(rf/reg-sub
  :user-image
  :<- [:users]
  (fn [users [_ uid]]
    (get-in users [uid :profile :img])))
