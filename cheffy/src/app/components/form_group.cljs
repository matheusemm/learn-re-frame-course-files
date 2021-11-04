(ns app.components.form-group
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [ControlFeedback FormGroup Input Label Textarea]]
            [clojure.string :as str]))

(def skip [:__scTheme :theme :control :valid])

(def r-input
  (r/reactify-component
    (fn [props]
      [:input (apply dissoc props skip)])))

(def r-textarea
  (r/reactify-component
    (fn [props]
      [:textarea (apply dissoc props skip)])))

(defn form-group
  [{:keys [id label type values element on-key-down] :or {element Input}}]
  (let [errors @(rf/subscribe [:errors])
        input-error (get errors id)
        valid (if input-error false nil)
        is-empty? (str/blank? (id @values))
        input? (= element Input)
        textarea? (= element Textarea)
        validate (fn []
                   (if is-empty?
                     (rf/dispatch [:has-value? id])
                     (rf/dispatch [:clear-error id])))]
    [:> FormGroup
     [:> Label {:html-for :id} label]
     [:> element {:as (cond
                        input? r-input
                        textarea? r-textarea)
                  :control true
                  :valid valid
                  :rows (when textarea? 6)
                  :id id
                  :type type
                  :value (id @values)
                  :on-change #(swap! values assoc id (.. % -target -value))
                  :on-key-down on-key-down
                  :on-key-up (when-not (str/blank? (id @values)) validate)
                  :on-blur validate}]
     (when input-error
       [:> ControlFeedback {:valid false} input-error])]))
