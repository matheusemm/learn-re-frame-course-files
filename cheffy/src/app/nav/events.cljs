(ns app.nav.events
  (:require [app.router :as router]
            [re-frame.core :as rf]))

(def nav-interceptors [(rf/path :nav)])

(rf/reg-fx
  :navigate-to
  (fn [{:keys [path]}]
    (router/set-token! path)))

(rf/reg-event-db
  :set-active-nav
  nav-interceptors
  (fn [nav [_ active-nav]]
    (assoc nav :active-nav active-nav)))

(rf/reg-event-db
  :set-active-page
  nav-interceptors
  (fn [nav [_ active-page]]
    (assoc nav :active-page active-page)))

(rf/reg-event-db
  :close-modal
  nav-interceptors
  (fn [nav _]
    (assoc nav :active-modal nil)))

(rf/reg-event-db
  :open-modal
  nav-interceptors
  (fn [nav [_ modal-name]]
    (assoc nav :active-modal modal-name)))

(rf/reg-event-fx
  :route-changed
  nav-interceptors
  (fn [{nav :db} [_ {:keys [handler route-params]}]]
    (let [nav (assoc nav :active-page handler)]
      (case handler
        :recipes
        {:db nav
         :dispatch [:get-recipes]}

        :recipe
        {:db (assoc nav :active-recipe (keyword (:recipe-id route-params)))
         :dispatch [:get-recipes]}

        :inbox
        {:db (assoc nav :active-inbox (keyword (:inbox-id route-params)))}

        {:db (dissoc nav :active-recipe :active-inbox)}))))
