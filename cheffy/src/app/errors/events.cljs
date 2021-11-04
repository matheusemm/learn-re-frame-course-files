(ns app.errors.events
  (:require [app.spec :refer [check-spec-interceptor]]
            [re-frame.core :as rf]))

(def errors-interceptors [check-spec-interceptor])

(rf/reg-event-db
  :has-value?
  errors-interceptors
  (fn [db [_ id]]
    (assoc-in db [:errors id] "Can't be blank")))

(rf/reg-event-db
  :clear-error
  errors-interceptors
  (fn [db [_ id]]
    (update-in db [:errors] dissoc id)))
