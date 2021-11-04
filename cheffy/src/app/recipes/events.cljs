(ns app.recipes.events
  (:require [app.helpers :as h]
            [app.spec :refer [check-spec-interceptor]]
            [re-frame.core :as rf]
            [day8.re-frame.http-fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [ajax.core :as ajax]
            [clojure.walk :as w]))

(def recipes-interceptors [check-spec-interceptor])

(def recipes-endpoint "https://gist.githubusercontent.com/jacekschae/50ffe6e8851a5dfe35e932682ca32d85/raw/06e8041d0abf86e2c5d809a334cf8f18d3d6303b/recipes.json")

(rf/reg-event-fx
  :get-recipes
  recipes-interceptors
  (fn-traced [{:keys [db]} _]
    {:db (assoc-in db [:loading :recipes] true)
     :http-xhrio {:method :get
                  :uri recipes-endpoint
                  :response-format (ajax/json-response-format {:keyword? true})
                  :on-success [:get-recipes-success]
                  :on-failure [:endpoint-request-error :get-recipes]}}))

(defn keywordize-id
  [coll]
  (->> coll
       (w/keywordize-keys)
       (map (fn [{:keys [id] :as values}]
              [(keyword id)
               (update values :id keyword)]))
       (into {})))

(rf/reg-event-db
  :get-recipes-success
  (fn-traced [db [_ recipes]]
    (-> db
        (assoc-in [:loading :recipes] false)
        (assoc-in [:recipes] (into {} (keywordize-id recipes))))))

(rf/reg-event-db
  :endpoint-request-error
  (fn-traced [db [_ request-type response]]
    (-> db
        (assoc-in [:errors request-type] (get response :status-text))
        (assoc-in [:loading request-type] false))))

(rf/reg-event-db
  :save-recipe
  recipes-interceptors
  (fn-traced [db [_ recipe-id]]
    (let [uid (get-in db [:auth :uid])]
      (-> db
          (update-in [:users uid :saved] conj recipe-id)
          (update-in [:recipes recipe-id :saved-count] inc)))))

(rf/reg-event-db
  :delete-ingredient
  recipes-interceptors
  (fn-traced [db [_ ingredient-id]]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      (-> db
          (update-in [:recipes recipe-id :ingredients] dissoc ingredient-id)
          (h/close-modal)))))

(rf/reg-event-db
  :upsert-ingredient
  recipes-interceptors
  (fn-traced [db [_ {:keys [id name amount measure]}]]
    (let [recipe-id (get-in db [:nav :active-recipe])
          ingredients (get-in db [:recipes recipe-id :ingredients])
          order (or (get-in ingredients [id :order])
                    (inc (count ingredients)))]
      (-> db
          (assoc-in [:recipes recipe-id :ingredients id] {:id id
                                                          :order order
                                                          :name name
                                                          :amount amount
                                                          :measure measure})
          (h/close-modal)))))

(rf/reg-event-db
  :delete-step
  recipes-interceptors
  (fn-traced [db [_ step-id]]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      (-> db
          (update-in [:recipes recipe-id :steps] dissoc step-id)
          (h/close-modal)))))

(rf/reg-event-db
  :upsert-step
  recipes-interceptors
  (fn-traced [db [_ {:keys [id desc]}]]
    (let [recipe-id (get-in db [:nav :active-recipe])
          steps (get-in db [:recipes recipe-id :steps])
          order (or (get-in steps [id :order])
                    (inc (count steps)))]
      (-> db
          (assoc-in [:recipes recipe-id :steps id] {:id id
                                                    :order order
                                                    :desc desc})
          (h/close-modal)))))

(rf/reg-event-fx
  :delete-recipe
  recipes-interceptors
  (fn-traced [{:keys [db]}]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      {:db (update-in db [:recipes] dissoc recipe-id)
       :dispatch-n [[:set-active-page :recipes]
                    [:set-active-nav :recipes]
                    [:close-modal]]
       :navigate-to {:path "/recipes/"}})))

(rf/reg-event-db
  :upsert-recipe
  recipes-interceptors
  (fn-traced [db [_ {:keys [name prep-time]}]]
    (let [recipe-id (get-in db [:nav :active-recipe])
          id (or recipe-id (keyword (str "recipe-" (random-uuid))))
          uid (get-in db [:auth :uid])]
      (-> db
          (update-in [:recipes id] merge {:id id
                                          :name name
                                          :prep-time prep-time
                                          :cook uid
                                          :public? false})
          (h/close-modal)))))

(rf/reg-event-db
  :publish-recipe
  recipes-interceptors
  (fn-traced [db [_ {:keys [price]}]]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      (-> db
          (update-in [:recipes recipe-id] merge {:price price :public? true})
          (h/close-modal)))))

(rf/reg-event-db
  :unpublish-recipe
  recipes-interceptors
  (fn-traced [db _]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      (-> db
          (assoc-in [:recipes recipe-id :public?] false)
          (h/close-modal)))))

(rf/reg-event-db
  :upsert-image
  recipes-interceptors
  (fn-traced [db [_ {:keys [img]}]]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      (-> db
          (assoc-in [:recipes recipe-id :img] img)
          (h/close-modal)))))
