(ns app.inbox.subs
  (:require [re-frame.core :as rf]))

(defn reverse-cmp
  [a b]
  (compare b a))

(rf/reg-sub
  :inboxes
  (fn [db _]
    (:inboxes db)))

(rf/reg-sub
  :user-inboxes
  :<- [:user]
  (fn [user _]
    (sort-by :updated-at reverse-cmp (:inboxes user))))

(rf/reg-sub
  :inbox-messages
  :<- [:inboxes]
  :<- [:active-inbox]
  (fn [[inboxes active-inbox] _]
    (let [messages (get-in inboxes [active-inbox :messages])]
      (sort-by :created-at reverse-cmp messages))))

(rf/reg-sub
  :conversation-with
  :<- [:uid]
  :<- [:inboxes]
  :<- [:active-inbox]
  (fn [[uid inboxes active-inbox] _]
    (let [participants (get-in inboxes [active-inbox :participants])]
      (first (disj participants uid)))))
