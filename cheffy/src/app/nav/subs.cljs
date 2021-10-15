(ns app.nav.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :nav
  (fn [db _]
    (:nav db)))

(rf/reg-sub
  :active-nav
  :<- [:nav]
  (fn [nav _]
    (:active-nav nav)))

(rf/reg-sub
  :active-page
  :<- [:nav]
  (fn [nav _]
    (:active-page nav)))

(rf/reg-sub
  :active-modal
  :<- [:nav]
  (fn [nav _]
    (:active-modal nav)))

(rf/reg-sub
  :active-inbox
  :<- [:nav]
  (fn [nav _]
    (:active-inbox nav)))

(rf/reg-sub
  :active-recipe
  :<- [:nav]
  (fn [nav _]
    (:active-recipe nav)))
