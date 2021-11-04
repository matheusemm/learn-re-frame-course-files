(ns app.spec
  (:require [re-frame.core :as rf]
            [cljs.spec.alpha :as s]))

(defn check-and-throw
  [a-spec db]
  (when-not (s/valid? a-spec db)
    (throw (ex-info (str "spec check failed: " (s/explain-str a-spec db)) {}))))

(s/def ::uid (s/nilable string?))
(s/def ::auth (s/map-of keyword? ::uid))

(s/def ::errors (s/map-of keyword? string?))

(s/def ::inboxes (s/map-of keyword? map?))

(s/def ::nav (s/map-of keyword? (s/nilable keyword?)))

(s/def ::recipes (s/map-of keyword? map?))

(s/def :user.inbox/id keyword?)
(s/def :user.inbox/updated-at number?)
(s/def :user.inbox/notifications number?)
(s/def :user/inbox (s/keys :req-un [:user.inbox/id :user.inbox/updated-at :user.inbox/notifications]))
(s/def :user/uid string?)
(s/def :user/profile (s/map-of keyword? string?))
(s/def :user/role keyword?)
(s/def :user/saved set?)
(s/def :user/inboxes (s/map-of string? :user/inbox))
(s/def ::user/map (s/keys :req-un [:user/uid :user/profile :user/role :user/saved :user/inboxes]))
(s/def ::users (s/map-of :user/uid :user/map))

(s/def ::db (s/keys :req-un [::auth ::errors ::inboxes ::nav ::recipes ::users]))

(def check-spec-interceptor
  (rf/after (partial check-and-throw ::db)))
