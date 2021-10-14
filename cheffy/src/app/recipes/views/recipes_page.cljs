(ns app.recipes.views.recipes-page
  (:require [app.components.page-nav :refer [page-nav]]
            [app.recipes.views.recipes-list :refer [recipes-list]]
            [app.recipes.views.recipe-editor :refer [recipe-editor]]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Typography]]))

(defn recipes-page
  []
  (let [public @(rf/subscribe [:public])
        drafts @(rf/subscribe [:drafts])
        logged-in? @(rf/subscribe [:logged-in?])]
    [:<>
     [page-nav {:center "Recipes"
                :right (when logged-in? [recipe-editor])}]
     (when (seq drafts)
       [:<>
        [:> Typography {:variant "h4" :py 20 :font-weight 700} "Drafts"]
        [recipes-list drafts]])
     (when logged-in?
       [:> Typography {:variant "h4" :py 20 :font-weight 700} "Public"])
     [recipes-list public]]))
