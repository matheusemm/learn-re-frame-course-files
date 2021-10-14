(ns app.recipes.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :drafts
  (fn [db _]
    (let [recipes (vals (get-in db [:recipes]))
          uid (get-in db [:auth :uid])]
      (filter #(and (not (:public? %))
                    (= (:cook %) uid))
              recipes))))

(rf/reg-sub
  :public
  (fn [db _]
    (let [recipes (vals (get-in db [:recipes]))]
      (filter :public? recipes))))

(rf/reg-sub
  :saved
  (fn [db _]
    (let [uid (get-in db [:auth :uid])
          saved (get-in db [:users uid :saved])
          recipes (vals (get-in db [:recipes]))]
      (filter #(saved (:id %)) recipes))))

(rf/reg-sub
  :recipe
  (fn [db _]
    (let [active-recipe (get-in db [:nav :active-recipe])]
      (get-in db [:recipes active-recipe]))))

(rf/reg-sub
  :ingredients
  (fn [db _]
    (let [active-recipe (get-in db [:nav :active-recipe])
          ingredients (get-in db [:recipes active-recipe :ingredients])]
      (->> ingredients vals (sort-by :order)))))

(rf/reg-sub
  :steps
  (fn [db _]
    (let [active-recipe (get-in db [:nav :active-recipe])
          steps (get-in db [:recipes active-recipe :steps])]
      (->> steps vals (sort-by :order)))))

(rf/reg-sub
  :author?
  (fn [db _]
    (let [uid (get-in db [:auth :uid])
          active-recipe (get-in db [:nav :active-recipe])
          recipe (get-in db [:recipes active-recipe])]
      (= uid (:cook recipe)))))

(rf/reg-sub
  :chef?
  (fn [db _]
    (let [uid (get-in db [:auth :uid])]
      (= (get-in db [:users uid :role]) :chef))))
