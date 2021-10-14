(ns app.nav.views.public
  (:require [app.nav.views.nav-item :refer [nav-item]]
            [app.router :as router]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box]]))

(defn public
  []
  (let [active-page @(rf/subscribe [:active-page])
        nav-items [{:id :recipes
                    :name "Recipes"
                    :href (router/path-for :recipes)
                    :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                   {:id :become-a-chef
                    :name "Chef"
                    :href (router/path-for :become-a-chef)
                    :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                   {:id :sign-up
                    :name "Sign up"
                    :href (router/path-for :sign-up)
                    :dispatch #(rf/dispatch [:set-active-nav :sign-up])}
                   {:id :log-in
                    :name "Log in"
                    :href (router/path-for :log-in)
                    :dispatch #(rf/dispatch [:set-active-nav :log-in])}]]
    [:> Box {:display "flex" :justify-content "flex-end" :py 1}
     (for [item nav-items]
       ^{:key (:id item)}
       [nav-item (assoc item :active-page active-page)])]))
