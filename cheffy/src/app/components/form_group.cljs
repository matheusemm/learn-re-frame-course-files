(ns app.components.form-group
  (:require ["@smooth-ui/core-sc" :refer [FormGroup Input Label Textarea]]))

(defn form-group
  [{:keys [id label type values textarea?]}]
  [:> FormGroup
   [:> Label {:html-for :id} label]
   (if textarea?
     [:> Textarea {:control true
                   :rows 6
                   :id id
                   :type type
                   :value (id @values)
                   :on-change #(swap! values assoc id (.. % -target -value))}]
     [:> Input {:control true
                :id id
                :type type
                :value (id @values)
                :on-change #(swap! values assoc id (.. % -target -value))}])])
