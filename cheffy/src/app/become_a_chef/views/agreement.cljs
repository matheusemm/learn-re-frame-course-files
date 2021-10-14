(ns app.become-a-chef.views.agreement
  (:require [app.router :as router]
            [app.components.modal :refer [modal]]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box Button]]))

(defn agreement
  []
  (let [logged-in? @(rf/subscribe [:logged-in?])]
    (if logged-in?
      [:> Box
       [:> Button
        {:on-click #(rf/dispatch [:open-modal :agreement])}
        "Get started"]
       [modal {:modal-name :agreement
               :header "Become a Chef"
               :body "I agree to cook"
               :footer [:<>
                        [:> Button
                         {:variant "light"
                          :on-click #(rf/dispatch [:close-modal])}
                         "Cancel"]
                        [:> Button
                         {:on-click #(rf/dispatch [:agree-to-cook])}
                         "Agree"]]}]]
      [:> Button
       {:as "a"
        :color "white"
        :href (router/path-for :sign-up)
        :on-click #(rf/dispatch [:set-active-nav :sign-up])}
       "Sign up"])))
