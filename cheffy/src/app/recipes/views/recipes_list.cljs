(ns app.recipes.views.recipes-list
  (:require [app.recipes.views.recipe-card :refer [recipe-card]]
            ["@smooth-ui/core-sc" :refer [Box]]))

(defn recipes-list
  [items]
  [:> Box {:class "cards"}
   (for [recipe items]
     ^{:key (:id recipe)}
     [recipe-card recipe])])
