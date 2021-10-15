(ns app.recipes.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :recipes
  (fn [db _]
    (:recipes db)))

(rf/reg-sub
  :drafts
  :<- [:uid]
  :<- [:recipes]
  (fn [[uid recipes] _]
    (filter #(and (not (:public? %))
                  (= (:cook %) uid))
            (vals recipes))))

(rf/reg-sub
  :public
  :<- [:recipes]
  (fn [recipes _]
    (filter :public? (vals recipes))))

(rf/reg-sub
  :saved
  :<- [:user]
  :<- [:recipes]
  (fn [[user recipes] _]
    (let [saved (:saved user)]
      (filter #(saved (:id %)) (vals recipes)))))

(rf/reg-sub
  :recipe
  :<- [:recipes]
  :<- [:active-recipe]
  (fn [[recipes active-recipe] _]
    (get recipes active-recipe)))

(rf/reg-sub
  :ingredients
  :<- [:recipe]
  (fn [recipe _]
    (let [ingredients (:ingredients recipe)]
      (->> ingredients vals (sort-by :order)))))

(rf/reg-sub
  :steps
  :<- [:recipe]
  (fn [recipe _]
    (let [steps (:steps recipe)]
      (->> steps vals (sort-by :order)))))

(rf/reg-sub
  :author?
  :<- [:uid]
  :<- [:recipe]
  (fn [[uid recipe] _]
    (= uid (:cook recipe))))
