(ns app.components.form-group
  (:require [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [ControlFeedback FormGroup Input Label Textarea]]
            [clojure.string :as str]))

(defn form-group
  [{:keys [id label type values textarea? on-key-down]}]
  (let [errors @(rf/subscribe [:errors])
        input-error (get errors id)
        valid (if input-error false nil)
        is-empty? (str/blank? (id @values))
        validate (fn []
                   (if is-empty?
                     (rf/dispatch [:has-value? id])
                     (rf/dispatch [:clear-error id])))]
    [:> FormGroup
     [:> Label {:html-for :id} label]
     (if textarea?
       [:> Textarea {:control true
                     :valid valid
                     :rows 6
                     :id id
                     :type type
                     :value (id @values)
                     :on-change #(swap! values assoc id (.. % -target -value))
                     :on-blur validate}]
       [:> Input {:control true
                  :valid valid
                  :id id
                  :type type
                  :value (id @values)
                  :on-change #(swap! values assoc id (.. % -target -value))
                  :on-key-down on-key-down
                  :on-blur validate}])
     (when input-error
       [:> ControlFeedback {:valid false} input-error])]))
