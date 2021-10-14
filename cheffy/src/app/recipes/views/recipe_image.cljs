(ns app.recipes.views.recipe-image
  (:require [app.components.form-group :refer [form-group]]
            [app.components.modal :refer [modal]]
            [reagent.core :as r]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box Button Modal ModalBody ModalContent ModalDialog ModalFooter Typography]]))

(defn recipe-image
  []
  (let [initial-values {:img ""}
        values (r/atom initial-values)
        author? @(rf/subscribe [:author?])
        save (fn [img]
               (rf/dispatch [:upsert-image img])
               (reset! values initial-values))
        open-modal (fn [{:keys [modal-name recipe]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values recipe))]
    (fn []
      (let [{:keys [img name]} @(rf/subscribe [:recipe])]
        [:<>
         [:> Box
          [:> Box {:class (when author? "editable")
                   :background-image (str "url(" (or img "/img/placeholder.jpg") ")")
                   :background-size "cover"
                   :border-radius 10
                   :min-height "400px"
                   :alt name
                   :on-click (when author? #(open-modal {:modal-name :image-editor
                                                         :recipe {:img img}}))}]]
         (when author?
           [modal {:modal-name :image-editor
                   :header "Image"
                   :body [form-group {:id :img :label "URL" :type "text" :values values}]
                   :footer [:<>
                            [:> Button {:on-click #(rf/dispatch [:close-modal])
                                        :variant "light"}
                             "Cancel"]
                            [:> Button {:on-click #(save @values)} "Save"]]}])]))))
