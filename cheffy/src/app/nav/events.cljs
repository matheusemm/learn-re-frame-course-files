(ns app.nav.events
  (:require [app.router :as router]
            [re-frame.core :as rf]))

(rf/reg-fx
  :navigate-to
  (fn [{:keys [path]}]
    (router/set-token! path)))

(rf/reg-event-db
  :set-active-nav
  (fn [db [_ active-nav]]
    (assoc-in db [:nav :active-nav] active-nav)))

(rf/reg-event-db
  :set-active-page
  (fn [db [_ active-page]]
    (assoc-in db [:nav :active-page] active-page)))

(rf/reg-event-db
  :close-modal
  (fn [db _]
    (assoc-in db [:nav :active-modal] nil)))

(rf/reg-event-db
  :open-modal
  (fn [db [_ modal-name]]
    (assoc-in db [:nav :active-modal] modal-name)))

(rf/reg-event-db
  :route-changed
  (fn [db [_ {:keys [handler route-params]}]]
    (-> db
        (assoc-in [:nav :active-page] handler)
        (assoc-in [:nav :active-recipe] (keyword (:recipe-id route-params)))
        (assoc-in [:nav :active-inbox] (keyword (:inbox-id route-params))))))
