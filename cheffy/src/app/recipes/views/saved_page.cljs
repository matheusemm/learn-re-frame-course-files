(ns app.recipes.views.saved-page
  (:require [app.components.page-nav :refer [page-nav]]
            [app.recipes.views.recipes-list :refer [recipes-list]]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box Typography]]
            ["styled-icons/fa-regular/Heart" :refer [Heart]]))

(defn saved-page
  []
  (let [saved @(rf/subscribe [:saved])]
    [:> Box
     [page-nav {:center "Saved"}]
     (if (seq saved)
       [recipes-list saved]
       [:> Box {:text-align "center"}
        [:> Typography {:variant "h5" :py 20}
         "Your saved recipes will appear here"]
        [:> Heart {:size 36}]])]))
