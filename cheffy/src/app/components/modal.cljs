(ns app.components.modal
  (:require [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box Button Modal ModalBody ModalContent ModalDialog ModalFooter Typography]]))

(defn modal
  [{:keys [modal-name header body footer]}]
  (let [active-modal @(rf/subscribe [:active-modal])]
    [:> Modal {:opened (= active-modal modal-name)
               :on-close #(rf/dispatch [:close-modal])}
     [:> ModalDialog
      [:> ModalContent {:border-radius 10}
       [:> Typography {:p 15 :variant "h4"} header]
       [:> ModalBody body]
       [:> ModalFooter {:background-color "#F0F4F8"
                        :border-radius "0 0 10px 10px"}
        footer]]]]))
