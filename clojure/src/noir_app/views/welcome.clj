(ns noir-app.views.welcome
  (:require [noir-app.views.common :as common]
            [noir.content.getting-started]
            [immutant.messaging :as msg])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to noir-app"]))

(defpage "/ping" []
         (let [message (str "hello from clojure at " (System/currentTimeMillis))]
           (msg/publish "/queues/clojure" message)
           (common/layout
             [:p (str "Published " message)])))

(defpage "/pong" []
         (let [message (msg/receive "/queues/ruby" :timeout 100)]
           (common/layout
             [:p (str "Received " message)])))
