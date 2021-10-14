(ns app.inbox.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
  :clear-notifications
  (fn [db [_ uid-inbox]]
    (let [uid (get-in db [:auth :uid])]
      (assoc-in db [:users uid :inboxes uid-inbox :notifications] 0))))

(rf/reg-event-db
  :send-notification
  (fn [db [_ {:keys [notify inbox-id]}]]
    (let [uid (get-in db [:auth :uid])
          notifications-count (get-in db [:users notify :inboxes uid :notifications])]
      (-> db
          (assoc-in [:users notify :inboxes uid] {:id inbox-id
                                                  :notifications (inc notifications-count)
                                                  :updated-at (.now js/Date)})
          (assoc-in [:users uid :inboxes notify] {:id inbox-id
                                                  :notifications 0
                                                  :updated-at (.now js/Date)})))))

(rf/reg-event-fx
  :insert-message
  (fn [{:keys [db]} [_ {:keys [message]}]]
    (let [uid (get-in db [:auth :uid])
          inbox-id (get-in db [:nav :active-inbox])
          participants (get-in db [:inboxes inbox-id :participants])
          conversation-with (first (disj participants uid))]
      {:db (update-in db [:inboxes inbox-id :messages] conj {:message message
                                                             :author uid
                                                             :created-at (.now js/Date)})
       :dispatch [:send-notification {:notify conversation-with
                                      :inbox-id inbox-id}]})))

(rf/reg-event-fx
  :request-message
  (fn [{:keys [db]} [_ {:keys [message]}]]
    (let [uid (get-in db [:auth :uid])
          recipe-id (get-in db [:nav :active-recipe])
          cook (get-in db [:recipes recipe-id :cook])
          existing-inbox-id (get-in db [:users uid :inboxes cook :id])
          new-inbox-id (keyword (str "inbox-" (random-uuid)))
          message {:message message
                   :author uid
                   :created-at (.now js/Date)}]
      {:db (if existing-inbox-id
             (update-in db [:inboxes existing-inbox-id :messages] conj message)
             (assoc-in db [:inboxes new-inbox-id] {:messages [message]
                                                   :participants #{uid cook}}))
       :dispatch-n [[:close-modal]
                    [:send-notification {:notify cook :inbox-id (or existing-inbox-id new-inbox-id)}]]})))
