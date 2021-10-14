(ns app.recipes.views.request-to-cook
  (:require [app.components.modal :refer [modal]]
            [app.components.form-group :refer [form-group]]
            [app.helpers :as h]
            [reagent.core :as r]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box Button]]
            [clojure.string :as str]))

(defn request-to-cook
  []
  (let [initial-values {:message ""}
        values (r/atom initial-values)
        open-modal (fn [{:keys [modal-name recipe]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values recipe))
        request (fn [{:keys [message]}]
                  (rf/dispatch [:request-message {:message (str/trim message)}])
                  (reset! values initial-values))]
    (fn []
      (let [{:keys [price]} @(rf/subscribe [:recipe])]
        [:> Box
         [:> Button
          {:on-click #(open-modal {:modal-name :request-to-cook :recipe initial-values})}
          "Order for " (h/format-price price)]
         [modal {:modal-name :request-to-cook
                 :header "Order"
                 :body [form-group {:id :message
                                    :label "Event description and number of people..."
                                    :type "text"
                                    :values values}]
                 :footer [:<>
                          [:> Button
                           {:on-click #(rf/dispatch [:close-modal])
                            :variant "light"}
                           "Cancel"]
                          [:> Button {:on-click #(request @values)} "Order"]]}]]))))
