(ns app.inbox.subs
  (:require [re-frame.core :as rf]))

(defn reverse-cmp
  [a b]
  (compare b a))

(rf/reg-sub
  :user-inboxes
  (fn [db _]
    (let [uid (get-in db [:auth :uid])]
      (sort-by :updated-at reverse-cmp (get-in db [:users uid :inboxes])))))

(rf/reg-sub
  :inbox-messages
  (fn [db _]
    (let [inbox-id (get-in db [:nav :active-inbox])
          messages (get-in db [:inboxes inbox-id :messages])]
      (sort-by :created-at reverse-cmp messages))))

(rf/reg-sub
  :conversation-with
  (fn [db _]
    (let [uid (get-in db [:auth :uid])
          inbox-id (get-in db [:nav :active-inbox])
          participants (get-in db [:inboxes inbox-id :participants])]
      (first (disj participants uid)))))
