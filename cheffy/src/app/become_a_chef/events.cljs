(ns app.become-a-chef.events
  (:require [app.spec :refer [check-spec-interceptor]] [re-frame.core :as rf]))

(rf/reg-event-fx
  :agree-to-cook
  [check-spec-interceptor]
  (fn [{:keys [db]} _]
    (let [uid (get-in db [:auth :uid])]
      {:db (assoc-in db [:users uid :role] :chef)
       :dispatch-n [[:close-modal]
                    [:set-active-nav :recipes]
                    [:set-active-page :recipes]]
       :navigate-to {:path "/recipes"}})))
