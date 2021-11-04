(ns app.errors.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :errors
  (fn [db _]
    (:errors db)))
