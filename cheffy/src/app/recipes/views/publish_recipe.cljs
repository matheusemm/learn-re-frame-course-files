(ns app.recipes.views.publish-recipe
  (:require [app.components.modal :refer [modal]]
            [app.components.form-group :refer [form-group]]
            [app.helpers :refer [format-price]]
            [reagent.core :as r]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box Button]]))

(defn publish-recipe
  []
  (let [initial-values {:price ""}
        values (r/atom initial-values)
        open-modal (fn [{:keys [modal-name recipe]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values recipe))
        publish (fn [{:keys [price]}]
                  (rf/dispatch [:publish-recipe {:price (js/parseInt price)}])
                  (reset! values initial-values))]
    (fn []
      (let [{:keys [price public?]} @(rf/subscribe [:recipe])]
        [:> Box
         (cond
           public?
           [:> Button
            {:on-click #(open-modal {:modal-name :publish-recipe})}
            (format-price price)]

           (not public?)
           [:> Button
            {:on-click #(open-modal {:modal-name :publish-recipe
                                     :recipe {:price price}})}
            "Publish"])
         [modal {:modal-name :publish-recipe
                 :header "Recipe"
                 :body [form-group {:id :price :label "Price (in cents)" :type "number" :values values}]
                 :footer [:<>
                          (when public?
                            [:a {:href "#"
                                 :on-click #(rf/dispatch [:unpublish-recipe])}
                             "Unpublish"])
                          [:> Button
                           {:on-click #(rf/dispatch [:close-modal])
                            :variant "light"}
                           "Cancel"]
                          [:> Button {:on-click #(publish @values)} "Publish"]]}]]))))
