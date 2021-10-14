(ns app.core
  (:require [app.theme :refer [cheffy-theme]]
            [app.db]
            [app.router :as router]
            ;; --- auth ---
            [app.auth.views.profile :refer [profile]]
            [app.auth.views.sign-up :refer [sign-up]]
            [app.auth.views.log-in :refer [log-in]]
            [app.auth.events]
            [app.auth.subs]
            ;; --- become-a-chef ---
            [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
            [app.become-a-chef.events]
            ;; --- errors ---
            [app.errors.events]
            [app.errors.subs]
            ;; --- inbox ---
            [app.inbox.views.inboxes-page :refer [inboxes-page]]
            [app.inbox.views.inbox-page :refer [inbox-page]]
            [app.inbox.events]
            [app.inbox.subs]
            ;; --- nav ---
            [app.nav.views.nav :refer [nav]]
            [app.nav.events]
            [app.nav.subs]
            ;; --- recipes ---
            [app.recipes.views.recipes-page :refer [recipes-page]]
            [app.recipes.views.recipe-page :refer [recipe-page]]
            [app.recipes.views.saved-page :refer [saved-page]]
            [app.recipes.subs]
            [app.recipes.events]
            [reagent.dom :as rdom]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Button Col Grid Normalize Row ThemeProvider]]))

(defn pages
  [page-name]
  (case page-name
    :profile [profile]
    :sign-up [sign-up]
    :log-in [log-in]
    :become-a-chef [become-a-chef]
    :inboxes [inboxes-page]
    :inbox [inbox-page]
    :recipes [recipes-page]
    :recipe [recipe-page]
    :saved [saved-page]
    [recipes-page]))

(defn app
  []
  (let [active-page @(rf/subscribe [:active-page])]
    [:<>
     [:> Normalize]
     [:> ThemeProvider {:theme cheffy-theme}
      [:> Grid {:fluid false}
       [:> Row
        [:> Col
         [nav]
         [pages active-page]]]]]]))

(defn ^:dev/after-load start
  []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (start))
